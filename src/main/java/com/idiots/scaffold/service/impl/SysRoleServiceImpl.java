package com.idiots.scaffold.service.impl;

import com.idiots.scaffold.entity.SysRole;
import com.idiots.scaffold.mapper.SysRoleMapper;
import com.idiots.scaffold.service.ISysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author idiots-devil
 * @since 2023-05-28
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {

    @Override
    public List<SysRole> findAllRoles() {
        return baseMapper.selectList(null);
    }

    @Override
    public List<String> findRoleCodeByAccount(Long accountId) {
        return baseMapper.findRoleCodeByAccount(accountId);
    }
}
