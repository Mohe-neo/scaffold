package com.idiots.scaffold.filter;

import cn.hutool.core.util.StrUtil;
import com.idiots.scaffold.exception.IAuthenticationException;
import com.idiots.scaffold.fastmap.IFastMap;
import com.idiots.scaffold.lang.Constant;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author idiots-devil
 * @since 2023-05-29
 */
@Slf4j
@Component
public class ImageCodeValidateFilter extends OncePerRequestFilter {
    @Resource
    private IFastMap<String, String> fastMap;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        // 非 POST 方式的表单提交请求不校验图形验证码
        if ((Constant.V1 + "/login").equals(request.getRequestURI()) && "POST".equals(request.getMethod())) {
            try {
                // 校验图形验证码合法性
                // 获取用户传入的图形验证码值
                String requestCode = request.getParameter("code");
                log.info("request code:{}", requestCode);
                String key = request.getParameter("key");
                String saveCode = fastMap.get(key);
                log.info("save code:{}", saveCode);

                // 随手清除验证码，无论是失败，还是成功。客户端应在登录失败时刷新验证码
                fastMap.remove(key);

                // 校验出错，抛出异常
                if (StrUtil.isBlank(requestCode)) {
                    throw new IAuthenticationException("验证码的值不能为空");
                }

                if (StrUtil.isBlank(saveCode)) {
                    throw new IAuthenticationException("验证码过期，刷新重试!");
                }

                if (!requestCode.equalsIgnoreCase(saveCode)) {
                    throw new IAuthenticationException("验证码输入错误");
                }
            } catch (AuthenticationException ex) {
                // 手动捕获图形验证码校验过程抛出的异常，将其传给失败处理器进行处理
                log.info(ex.getMessage());
                ex.printStackTrace();
                return;
            }
        }

        // 放行请求，进入下一个过滤器
        chain.doFilter(request, response);
    }
}

