package com.zhangcanfu.myjava.app.reflect.model;

public class Customer {

    public static final String DEFAULT_CITY = "Hangzhou";

    private Long id;
    private String name;
    private String phone;
    private int age;
    private boolean vip;

    public Customer() {
    }

    public Customer(Long id, String name, String phone, int age) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.age = age;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public int getAge() {
        return age;
    }

    public boolean isVip() {
        return vip;
    }

    public void setVip(boolean vip) {
        this.vip = vip;
    }

    public boolean isAdult() {
        return age >= 18;
    }

    public String getDisplayName() {
        return vip ? name + "(VIP)" : name;
    }

    private String maskPhone() {
        if (phone == null || phone.length() < 7) {
            return phone;
        }
        return phone.substring(0, 3) + "****" + phone.substring(7);
    }

    @Override
    public String toString() {
        return "Customer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + maskPhone() + '\'' +
                ", age=" + age +
                ", vip=" + vip +
                '}';
    }
}
