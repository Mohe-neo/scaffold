package com.idiots.scaffold.controller;

import com.idiots.scaffold.lang.Constant;
import com.idiots.scaffold.model.dto.LInfo;
import com.idiots.scaffold.model.vo.LResult;
import com.idiots.scaffold.service.ISysAuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author idiots-devil
 * @since 2023-05-29
 */
@RestController
@RequestMapping(Constant.V1)
@Tag(name = "用户认证", description = "用户操作平台时的认证及退出认证")
public class SysAuthenticationController {
    @Resource
    private ISysAuthenticationService authenticationService;

    @PostMapping("/login")
    @Operation(summary = "用户请求认证")
    public LResult login(@RequestParam String code, @RequestParam String key, @RequestBody LInfo info) {
        return authenticationService.login(info);
    }

    @PostMapping("/logout")
    @Operation(summary = "用户退出认证")
    public String logout(HttpServletResponse response) {
        return authenticationService.logout(response);
    }

    @GetMapping({"/verify"})
    @Operation(summary = "获取验证码")
    public void verify(HttpServletRequest request, HttpServletResponse response) {
        authenticationService.verify(request, response, 1L);
    }
}

