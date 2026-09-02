package com.zhangcanfu.myjava.app.exception.model;

public class ConfigurationError extends Error {

    public ConfigurationError(String message) {
        super(message);
    }

    public ConfigurationError(String message, Throwable cause) {
        super(message, cause);
    }
}
