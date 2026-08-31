package com.zhangcanfu.myjava.app.exception.model;

public class OrderLoadException extends Exception {

    public OrderLoadException(String message) {
        super(message);
    }

    public OrderLoadException(String message, Throwable cause) {
        super(message, cause);
    }
}
