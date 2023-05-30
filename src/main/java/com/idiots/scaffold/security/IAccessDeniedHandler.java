package com.idiots.scaffold.security;

import cn.hutool.json.JSONUtil;
import com.idiots.scaffold.lang.RespCode;
import com.idiots.scaffold.lang.Result;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author idiots-devil
 * @since 2023-05-28
 * 自定义返回结果：没有权限访问时
 */
@Component
public class IAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException e) throws IOException {
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Cache-Control", "no-cache");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getWriter().println(JSONUtil.parse(Result.failure(RespCode.FORBIDDEN)));
        response.getWriter().flush();
    }
}
