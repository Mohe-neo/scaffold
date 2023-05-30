package com.idiots.scaffold.aspect;

import com.idiots.scaffold.model.pojo.LogInfo;
import com.idiots.scaffold.service.OperationLogService;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * @author idiots-devil
 * @since 2023-05-30
 */
@Slf4j
@Aspect
@Component
public class OperationLogAspect {
    @Pointcut("execution(* com.idiots.scaffold.controller.*.*(..))")
    public void pointCut() {
    }

    @Resource
    private OperationLogService operationLogService;

    private LogInfo info;

    @Before(value = "pointCut()")
    public void doBefore(JoinPoint joinPoint) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String uri = attributes != null ? attributes.getRequest().getRequestURI() : "无法获取到操作URI";
        String method = attributes != null ? attributes.getRequest().getMethod() : "无法获取到操作Method";
        String remoteAddr = attributes != null ? attributes.getRequest().getRemoteAddr() : "无法获取到操作路由IP";
        String remoteHost = attributes != null ? attributes.getRequest().getRemoteHost() : "无法获取到操作路由Host";

        info = LogInfo.builder()
                .id(UUID.randomUUID().toString())
                .method(method)
                .username(username)
                .uri(uri)
                .host(remoteHost)
                .addr(remoteAddr)
                .build();
    }

    @Async
    @AfterReturning(value = "pointCut()")
    public void doAfterReturning(){
        info.setResult("success");
        operationLogService.insertLogInfo(info);
    }

    @Async
    @AfterThrowing(value = "pointCut()", throwing = "ex", argNames = "ex")
    public void doThrowing(Throwable ex) {
        info.setResult("failure");
        info.setDesc(ex.getMessage());
        log.info("运行出现异常,err info:{}", ex.getMessage());
        operationLogService.insertLogInfo(info);
    }
}
