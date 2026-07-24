package com.zhangcanfu.myjava.app.reflect.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Product {

    private String sku;
    private String name;
    private BigDecimal price;
    private int stock;
    private List<String> tags = new ArrayList<>();

    public Product() {
    }

    public Product(String sku, String name, BigDecimal price, int stock) {
        this.sku = sku;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public String getSku() {
        return sku;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    public List<String> getTags() {
        return tags;
    }

    public void addTag(String tag) {
        tags.add(tag);
    }

    public boolean hasStock() {
        return stock > 0;
    }

    public void decreaseStock(int quantity) {
        if (quantity <= 0) {
            return;
        }
        stock = Math.max(0, stock - quantity);
    }
}
