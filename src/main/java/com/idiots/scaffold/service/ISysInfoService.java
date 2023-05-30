package com.idiots.scaffold.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.idiots.scaffold.entity.SysInfo;
import com.idiots.scaffold.lang.PageResp;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author idiots-devil
 * @since 2023-05-28
 */
public interface ISysInfoService extends IService<SysInfo> {
    /**
     * 添加系统操作日志
     *
     * @param info 日志信息
     */
    void insertSysInfo(SysInfo info);

    /**
     * 查询所有的日志
     *
     * @param page 分页对象
     * @return list
     */
    PageResp<SysInfo> findAllSysInfo(Page<SysInfo> page, String startTime, String endTime, String username);
}
