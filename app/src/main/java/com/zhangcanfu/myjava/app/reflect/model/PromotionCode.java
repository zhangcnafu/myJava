package com.zhangcanfu.myjava.app.reflect.model;

import java.math.BigDecimal;

public class PromotionCode {

    private String code;
    private BigDecimal discountAmount;
    private boolean enabled;

    private PromotionCode(String code, BigDecimal discountAmount) {
        this.code = code;
        this.discountAmount = discountAmount;
        this.enabled = true;
    }

    public static PromotionCode create(String code, BigDecimal discountAmount) {
        return new PromotionCode(code, discountAmount);
    }

    public String getCode() {
        return code;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public boolean isEnabled() {
        return enabled;
    }

    @Override
    public String toString() {
        return "PromotionCode{" +
                "code='" + code + '\'' +
                ", discountAmount=" + discountAmount +
                ", enabled=" + enabled +
                '}';
    }
}
