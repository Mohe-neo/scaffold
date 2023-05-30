package com.idiots.scaffold.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.idiots.scaffold.entity.SysInfo;
import com.idiots.scaffold.lang.PageResp;
import com.idiots.scaffold.mapper.SysInfoMapper;
import com.idiots.scaffold.service.ISysInfoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author idiots-devil
 * @since 2023-05-28
 */
@Service
public class SysInfoServiceImpl extends ServiceImpl<SysInfoMapper, SysInfo> implements ISysInfoService {
    @Override
    public void insertSysInfo(SysInfo info) {
        if (!this.save(info)) {
            throw new IllegalStateException("添加日志失败！");
        }
    }

    @Override
    public PageResp<SysInfo> findAllSysInfo(Page<SysInfo> page, String startTime, String endTime, String username) {
        IPage<SysInfo> iPage = baseMapper.selectPageByInfoDto(page, startTime, endTime, username);
        return PageResp.<SysInfo>builder()
                .total(iPage.getTotal())
                .pageNum(iPage.getCurrent())
                .pageSize(iPage.getSize())
                .list(iPage.getRecords())
                .build();
    }
}
