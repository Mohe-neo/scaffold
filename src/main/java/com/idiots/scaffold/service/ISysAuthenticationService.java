package com.idiots.scaffold.service;

import com.idiots.scaffold.model.dto.LInfo;
import com.idiots.scaffold.model.vo.LResult;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author idiots-devil
 * @since 2023-05-29
 */
public interface ISysAuthenticationService extends UserDetailsService {
    /**
     * 用户登录请求
     * @param info 登录输入信息
     * @return 登录成功的响应
     */
    LResult login(LInfo info);

    /**
     * 退出认证
     * @param response 响应体
     * @return 响应消息
     */
    String logout(HttpServletResponse response);

    void verify(HttpServletRequest request, HttpServletResponse response, Long id);
}
