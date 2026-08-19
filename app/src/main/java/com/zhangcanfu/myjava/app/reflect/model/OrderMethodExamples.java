package com.zhangcanfu.myjava.app.reflect.model;

import java.util.List;

public class OrderMethodExamples implements OrderOperation {

    public String buildOrderSummary(ShoppingOrder order) {
        return order.getOrderNo() + " - " + order.getCustomer().getDisplayName();
    }

    public static String normalizeOrderNo(String orderNo) {
        return orderNo == null ? "" : orderNo.trim().toUpperCase();
    }

    public void addTags(Product product, String... tags) {
        for (String tag : tags) {
            product.addTag(tag);
        }
    }

    public <T> T firstOrDefault(List<T> values, T defaultValue) {
        if (values == null || values.isEmpty()) {
            return defaultValue;
        }
        return values.get(0);
    }

    public void checkQuantity(int quantity) throws IllegalArgumentException {
        if (quantity <= 0) {
            throw new IllegalArgumentException("quantity must be greater than 0");
        }
    }

    private boolean canCancel(ShoppingOrder order) {
        return order.getStatus() == OrderStatus.CREATED;
    }
}
