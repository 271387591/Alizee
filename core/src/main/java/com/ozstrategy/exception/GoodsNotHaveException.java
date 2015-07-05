package com.ozstrategy.exception;

/**
 * Created by lihao1 on 7/1/15.
 */
public class GoodsNotHaveException extends Exception {
    public GoodsNotHaveException(){}

    public GoodsNotHaveException(String s) {
        super(s);
    }

    public GoodsNotHaveException(String s, Throwable throwable) {
        super(s, throwable);
    }
}
