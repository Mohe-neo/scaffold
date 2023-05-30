package com.idiots.scaffold.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @author idiots-devil
 * @since 2023-05-30
 */
public class IAuthenticationException extends AuthenticationException {
    public IAuthenticationException(String msg) {
        super(msg);
    }
}
