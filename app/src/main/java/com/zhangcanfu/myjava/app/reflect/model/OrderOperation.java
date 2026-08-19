package com.zhangcanfu.myjava.app.reflect.model;

public interface OrderOperation {

    default String getOperationName() {
        return "订单操作";
    }
}
