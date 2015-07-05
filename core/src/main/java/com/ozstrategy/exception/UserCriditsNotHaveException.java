package com.ozstrategy.exception;

/**
 * Created by lihao1 on 7/1/15.
 */
public class UserCriditsNotHaveException extends Exception {
    public UserCriditsNotHaveException(){}

    public UserCriditsNotHaveException(String s) {
        super(s);
    }

    public UserCriditsNotHaveException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
