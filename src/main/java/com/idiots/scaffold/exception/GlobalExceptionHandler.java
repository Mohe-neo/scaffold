package com.idiots.scaffold.exception;

import com.idiots.scaffold.lang.RespCode;
import com.idiots.scaffold.lang.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author idiots-devil
 * @since 2023-05-28
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthenticationException.class)
    public Result doException(AuthenticationException ex) {
        log.info("认证失败异常:{}", ex.getMessage());
        return Result.failure(RespCode.UNAUTHORIZED);
    }

    @ResponseStatus(HttpStatus.FORBIDDEN)
    @ExceptionHandler(AccessDeniedException.class)
    public Result doException(AccessDeniedException ex) {
        log.info("权限不足:{}", ex.getMessage());
        return Result.failure(RespCode.FORBIDDEN);
    }

    @ExceptionHandler(IllegalStateException.class)
    public Result doIllegalStateException(IllegalStateException ex) {
        log.info("运行服务出现的IllegalStateException异常:{}", ex.getMessage());
        return Result.failure(ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public Result doIllegalStateException(IllegalArgumentException ex) {
        log.info("运行服务出现的IllegalArgumentException异常:{}", ex.getMessage());
        return Result.failure(ex.getMessage());
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result doException(HttpMessageNotReadableException ex) {
        log.info("服务出现的异常:{}", ex.getMessage());
        return Result.failure("Http 消息不可读异常");
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Result doException(Exception ex) {
        log.info("服务出现的异常:{}", ex.getMessage());
        ex.printStackTrace();
        return Result.failure(RespCode.INTERNAL_SERVER_ERROR);
    }
}
