package com.idiots.scaffold.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.idiots.scaffold.entity.SysAccount;
import com.idiots.scaffold.lang.PageResp;
import com.idiots.scaffold.model.dto.PasswordDto;
import com.idiots.scaffold.model.dto.SysAccountDto;
import com.idiots.scaffold.model.vo.SysAccountVo;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author idiots-devil
 * @since 2023-05-28
 */
public interface ISysAccountService extends IService<SysAccount> {
    /**
     * 根据用户名查询用户信息
     *
     * @param username 用户民
     * @return account
     */
    SysAccount getAccountByUsername(String username);

    /**
     * 查询用户
     *
     * @param username 用户名
     * @param page     分页对象
     * @return list
     */
    PageResp<SysAccountVo> findAccountByUsername(Page<SysAccount> page, String username);

    /**
     * 添加用户
     *
     * @param accountDto 用户信息
     * @return 用户ID
     */
    Long insertAccount(SysAccountDto accountDto);

    /**
     * 根据用户ID修改用户信息
     *
     * @param accountId  用户ID
     * @param accountDto 用户信息
     * @return true|false
     */
    Boolean updateAccountByAccountId(Long accountId, SysAccountDto accountDto);

    /**
     * 根据用户ID修改密码
     *
     * @param accountId
     * @param password
     * @return
     */
    Boolean updateAccountPasswordByAccountId(Long accountId, PasswordDto password);

    /**
     * 根据用户ID删除用户信息
     *
     * @param accountId 用户ID
     * @return true|false
     */
    Boolean deleteAccountByAccountId(Long accountId);

    PageResp<SysAccountVo> findAllAccount(Page<SysAccount> page, String username);
}
