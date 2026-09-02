package com.zhangcanfu.myjava.app.exception.model;

public class OrderValidationException extends RuntimeException {

    public OrderValidationException(String message) {
        super(message);
    }

    public OrderValidationException(String message, Throwable cause) {
        super(message, cause);
    }
}
