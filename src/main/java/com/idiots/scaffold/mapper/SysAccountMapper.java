package com.idiots.scaffold.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.idiots.scaffold.entity.SysAccount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author idiots-devil
 * @since 2023-05-28
 */
@Mapper
public interface SysAccountMapper extends BaseMapper<SysAccount> {
    /**
     * 模糊查询
     *
     * @param page
     * @param username
     * @return
     */
    IPage<SysAccount> selectPageByUsername(Page<SysAccount> page, @Param("username") String username);

    /**
     * 修改用户信息
     * @param account 用户
     * @return
     */
    Integer updateAccountById(@Param("account") SysAccount account);

}
