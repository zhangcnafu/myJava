package com.zhangcanfu.myjava.app.reflect.model;

import java.math.BigDecimal;

public class ShoppingOrder {

    private String orderNo;
    private Customer customer;
    private Product product;
    private int quantity;
    private OrderStatus status;

    public ShoppingOrder() {
        this.status = OrderStatus.CREATED;
    }

    public ShoppingOrder(String orderNo, Customer customer, Product product, int quantity) {
        this.orderNo = orderNo;
        this.customer = customer;
        this.product = product;
        this.quantity = quantity;
        this.status = OrderStatus.CREATED;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public BigDecimal calculateTotalAmount() {
        if (product == null || product.getPrice() == null) {
            return BigDecimal.ZERO;
        }
        return product.getPrice().multiply(BigDecimal.valueOf(quantity));
    }

    public void pay() {
        this.status = OrderStatus.PAID;
    }

    public boolean belongsTo(Customer customer) {
        return this.customer == customer;
    }
}
