package com.idiots.scaffold.service;

import com.idiots.scaffold.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author idiots-devil
 * @since 2023-05-28
 */
public interface ISysMenuService extends IService<SysMenu> {

    /**
     * 根据用户名查询权限列表
     *
     * @param accountId 用户名
     * @return 权限列表
     */
    List<String> findMenuByUsername(Long accountId);


    /**
     * 根据角色编码查询权限列表信息
     * @param codes 角色编码列表
     * @return 权限列表
     */
    List<String> findSysMenuUrlByRoleCode(List<String> codes);
}
