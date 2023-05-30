package com.idiots.scaffold.service.impl;

import com.idiots.scaffold.entity.SysMenu;
import com.idiots.scaffold.mapper.SysMenuMapper;
import com.idiots.scaffold.service.ISysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author idiots-devil
 * @since 2023-05-28
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements ISysMenuService {

    @Override
    public List<String> findMenuByUsername(Long accountId) {
        return baseMapper.findMenuByUsername(accountId);
    }

    @Override
    public List<String> findSysMenuUrlByRoleCode(List<String> codes) {
        return baseMapper.findSysMenuUrlByRoleCode(codes);
    }
}
