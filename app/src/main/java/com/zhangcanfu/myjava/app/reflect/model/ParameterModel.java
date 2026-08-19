package com.zhangcanfu.myjava.app.reflect.model;

import java.math.BigDecimal;

public class ParameterModel {

    private final String orderNo;
    private final int total;
    private final String operator;

    public ParameterModel(
            @OrderTag("orderNo") String orderNo,
            @OrderTag("total") int total,
            @OrderTag("operator") String operator
    ) {
        this.orderNo = orderNo;
        this.total = total;
        this.operator = operator;
    }

    public String describe(
            @OrderTag("notes") String notes,
            @OrderTag("discount") BigDecimal discount
    ) {
        return orderNo + "|" + total + "|" + operator;
    }
}
