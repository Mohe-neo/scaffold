package com.idiots.scaffold.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.idiots.scaffold.entity.SysAccount;
import com.idiots.scaffold.entity.SysAccountRole;
import com.idiots.scaffold.lang.PageResp;
import com.idiots.scaffold.mapper.SysAccountMapper;
import com.idiots.scaffold.model.dto.PasswordDto;
import com.idiots.scaffold.model.dto.SysAccountDto;
import com.idiots.scaffold.model.vo.SysAccountVo;
import com.idiots.scaffold.service.ISysAccountRoleService;
import com.idiots.scaffold.service.ISysAccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author idiots-devil
 * @since 2023-05-28
 */
@Slf4j
@Service
public class SysAccountServiceImpl extends ServiceImpl<SysAccountMapper, SysAccount> implements ISysAccountService {
    @Resource
    private PasswordEncoder passwordEncoder;

    //@Value("${spring.security.default.password}")
    private String defaultPassword;

    @Override
    public SysAccount getAccountByUsername(String username) {
        SysAccount account = this.getOne(new QueryWrapper<SysAccount>().lambda().eq(SysAccount::getUsername, username));
        Assert.notNull(account, String.format("用户【%s】不存在，请重试!", username));
        return account;
    }

    @Override
    public PageResp<SysAccountVo> findAccountByUsername(Page<SysAccount> page, String username) {
        IPage<SysAccount> accounts = baseMapper.selectPageByUsername(page, username);
        List<SysAccountVo> accountDtos = accounts.getRecords().stream().map(SysAccountVo::new).collect(Collectors.toList());
        return PageResp.<SysAccountVo>builder()
                .list(accountDtos)
                .total(accounts.getTotal())
                .pageNum(accounts.getCurrent())
                .pageSize(accounts.getSize())
                .build();
    }

    @Resource
    private ISysAccountRoleService accountRoleService;

    @Override
    public Long insertAccount(SysAccountDto accountDto) {
        SysAccount account = BeanUtil.copyProperties(accountDto, SysAccount.class);
        account.setPassword(passwordEncoder.encode(defaultPassword));
        account.setCreatedTime(LocalDateTime.now());
        account.setUpdatedTime(LocalDateTime.now());
        account.setLoginTime(LocalDateTime.now());
        account.setEnabled(1);
        account.setLocked(1);

        if (!this.save(account)) {
            throw new IllegalStateException("添加用户信息失败！");
        }
        boolean result = accountRoleService.save(SysAccountRole.builder().accountId(account.getId()).roleId(accountDto.getRoleId()).build());
        if (!result) {
            throw new IllegalStateException("添加用户关联信息失败！");
        }
        return account.getId();
    }

    @Override
    public Boolean updateAccountByAccountId(Long accountId, SysAccountDto accountDto) {
        SysAccount account = this.getById(accountId);
        if (account.getType() == 1) {
            throw new IllegalStateException("该用户为系统类型用户，不允许修改");
        }
        if (StrUtil.isNotBlank(accountDto.getRemark())) {
            account.setRemark(accountDto.getRemark());
            Integer count = baseMapper.updateAccountById(account);
            if (count==0){
                throw new IllegalStateException("修改用户的备注信息失败！");
            }
        }
        if (accountDto.getRoleId()!=null){
            SysAccountRole accountRole = SysAccountRole.builder().accountId(accountId).roleId(accountDto.getRoleId()).build();
            boolean update = accountRoleService.update(accountRole, new QueryWrapper<SysAccountRole>().lambda().eq(SysAccountRole::getAccountId, accountId));
            if (!update) {
                throw new IllegalStateException("修改用户的角色信息失败！");
            }
        }

        return true;
    }

    @Override
    public Boolean updateAccountPasswordByAccountId(Long accountId, PasswordDto password) {
        SysAccount account = baseMapper.selectById(accountId);
        if (account.getType() == 1) {
            throw new IllegalStateException("该用户为系统类型用户，不允许修改密码");
        }
        if (!StrUtil.equals(password.getNewPassword(), password.getConfirmPassword())) {
            throw new IllegalStateException("新密码和确认密码不匹配");
        }
        // marches(): 参数1:没有加密的密码；参数2:加密后的密码
        if (!passwordEncoder.matches(password.getOldPassword(), account.getPassword())) {
            throw new IllegalStateException("旧密码和原始密码不匹配!");
        }

        if (passwordEncoder.matches(password.getNewPassword(), account.getPassword())) {
            throw new IllegalStateException("新密码不能和原始密码相同");
        }
        account.setPassword(passwordEncoder.encode(password.getNewPassword()));

        return baseMapper.updateById(account) > 0;
    }

    @Override
    public Boolean deleteAccountByAccountId(Long accountId) {
        SysAccount account = this.getById(accountId);
        if (account.getType() == 1) {
            throw new IllegalStateException("该用户为系统类型用户，不允许删除");
        }

        if (!this.removeById(accountId)) {
            throw new IllegalStateException("删除失败！");
        }
        return true;
    }

    @Override
    public PageResp<SysAccountVo> findAllAccount(Page<SysAccount> page, String username) {
        IPage<SysAccount> accounts = baseMapper.selectPageByUsername(page, username);
        List<SysAccountVo> accountVos = accounts.getRecords().stream().map(SysAccountVo::new).collect(Collectors.toList());
        return PageResp.<SysAccountVo>builder()
                .list(accountVos)
                .pageNum(accounts.getCurrent())
                .pageSize(accounts.getSize())
                .total(accounts.getTotal())
                .build();
    }
}
