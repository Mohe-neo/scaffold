package com.idiots.scaffold.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.idiots.scaffold.entity.SysInfo;
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
public interface SysInfoMapper extends BaseMapper<SysInfo> {
    /**
     * 分页查询
     * @param page
     * @param startTime
     * @param endTime
     * @param username
     * @return
     */
    IPage<SysInfo> selectPageByInfoDto(@Param("page") Page<SysInfo> page, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("username") String username);
}
