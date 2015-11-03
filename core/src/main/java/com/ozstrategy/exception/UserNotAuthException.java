package com.ozstrategy.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * Created by lihao1 on 8/25/15.
 */
public class UserNotAuthException extends AuthenticationException {

    public UserNotAuthException(String msg, Throwable t) {
        super(msg, t);
    }

    public UserNotAuthException(String msg) {
        super(msg);
    }

    public UserNotAuthException(String msg, Object extraInformation) {
        super(msg, extraInformation);
    }
}
