package com.idiots.scaffold.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.idiots.scaffold.lang.Constant;
import com.idiots.scaffold.lang.PageResp;
import com.idiots.scaffold.model.vo.SysAccountVo;
import com.idiots.scaffold.service.ISysAccountService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author idiots-devil
 * @since 2023-05-28
 */
@RestController
@RequestMapping(Constant.V1+"/sys-account")
public class SysAccountController {
    @Resource
    private ISysAccountService accountService;

    @GetMapping("")
    @Operation(summary = "查询指定用户名的账户")
    @PreAuthorize("hasAuthority('sys:admin:p')")
    public PageResp<SysAccountVo> findAllAccount(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                                 @RequestParam(value = "pageSize", required = false, defaultValue = "20") Integer pageSize,
                                                 @RequestParam(value = "username", required = false, defaultValue = "") String username){
        return accountService.findAllAccount(Page.of(pageNum, pageSize), username);
    }
}
