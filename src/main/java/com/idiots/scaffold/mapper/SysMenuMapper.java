package com.idiots.scaffold.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.idiots.scaffold.entity.SysMenu;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author idiots-devil
 * @since 2023-05-28
 */
@Mapper
public interface SysMenuMapper extends BaseMapper<SysMenu> {
    /**
     * 根据用户名查询权限列表
     *
     * @param accountId 用户名
     * @return 权限列表
     */
    List<String> findMenuByUsername(@Param("account_id") Long accountId);

    /**
     * 根据用户名查询可访问列表
     * @param codes 角色码
     * @return 可访问列表
     */
    List<String> findSysMenuUrlByRoleCode(@Param("codes") List<String> codes);
}
