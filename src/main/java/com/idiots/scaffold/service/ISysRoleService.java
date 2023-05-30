package com.idiots.scaffold.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.idiots.scaffold.entity.SysRole;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author idiots-devil
 * @since 2023-05-28
 */
public interface ISysRoleService extends IService<SysRole> {
    /**
     * 查询所有角色信息
     *
     * @return roles
     */
    List<SysRole> findAllRoles();

    /**
     * 根据用户ID查询角色编码信息
     *
     * @param accountId 用户ID
     * @return 角色编码信息
     */
    List<String> findRoleCodeByAccount(Long accountId);

}
