package com.idiots.scaffold.controller;

import com.idiots.scaffold.lang.Constant;
import com.idiots.scaffold.service.OperationLogService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author idiots-devil
 * @since 2023-05-28
 */
@RestController
@RequestMapping(Constant.V1 + "/es")
public class OperationLogController {
    @Resource
    private OperationLogService operationLogService;

    @PostMapping("")
    public boolean createdIndex() {
        return operationLogService.createdIndex();
    }

    @DeleteMapping("")
    public boolean deletedIndex() {
        return operationLogService.deletedIndex();
    }
}
