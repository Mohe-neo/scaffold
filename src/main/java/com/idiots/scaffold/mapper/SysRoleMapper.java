package com.idiots.scaffold.mapper;

import com.idiots.scaffold.entity.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface SysRoleMapper extends BaseMapper<SysRole> {
    /**
     * 根据用户ID查询角色编码信息
     *
     * @param accountId 用户ID
     * @return 角色编码信息
     */
    List<String> findRoleCodeByAccount(@Param("account_id") Long accountId);
}
