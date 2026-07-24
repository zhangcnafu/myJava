package com.zhangcanfu.myjava.app.reflect.model;

public enum OrderStatus {

    CREATED("已创建"),
    PAID("已支付"),
    DELIVERED("已送达"),
    CANCELED("已取消");

    private final String description;

    OrderStatus(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
