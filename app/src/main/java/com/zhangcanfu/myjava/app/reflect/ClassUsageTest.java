package com.zhangcanfu.myjava.app.reflect;

import com.zhangcanfu.myjava.app.reflect.model.Customer;
import com.zhangcanfu.myjava.app.reflect.model.OrderStatus;
import com.zhangcanfu.myjava.app.reflect.model.Product;
import com.zhangcanfu.myjava.app.reflect.model.ShoppingOrder;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ClassUsageTest {

    public static void main(String[] args) throws ClassNotFoundException {
        showBasicClassInfo();
        showFields();
        showMethods();
        showConstructors();
        showTypeChecks();
        showEnumClass();
    }

    private static void showBasicClassInfo() throws ClassNotFoundException {
        Class<Customer> customerClass = Customer.class;
        Customer customer = new Customer(1L, "张三", "13812345678", 20);
        Class<?> customerClassFromObject = customer.getClass();
        Class<?> customerClassFromName = Class.forName("com.zhangcanfu.myjava.app.reflect.model.Customer");

        System.out.println("------ 获取 Class 对象 ------");
        System.out.println(customerClass == customerClassFromObject);
        System.out.println(customerClass == customerClassFromName);

        System.out.println("------ 类名信息 ------");
        System.out.println(customerClass.getName());
        System.out.println(customerClass.getSimpleName());
        System.out.println(customerClass.getPackageName());
        System.out.println(customerClass.getSuperclass().getName());
    }

    private static void showFields() {
        Class<Customer> customerClass = Customer.class;

        System.out.println("------ 当前类自己声明的属性 ------");
        for (Field field : customerClass.getDeclaredFields()) {
            System.out.println(field.getType().getSimpleName() + " " + field.getName());
        }
    }

    private static void showMethods() {
        Class<Customer> customerClass = Customer.class;

        System.out.println("------ 当前类自己声明的方法 ------");
        for (Method method : customerClass.getDeclaredMethods()) {
            System.out.println(method.getReturnType().getSimpleName() + " " + method.getName());
        }
    }

    private static void showConstructors() {
        Class<ShoppingOrder> orderClass = ShoppingOrder.class;

        System.out.println("------ 当前类自己声明的构造器 ------");
        for (Constructor<?> constructor : orderClass.getDeclaredConstructors()) {
            System.out.println(constructor);
        }
    }

    private static void showTypeChecks() {
        System.out.println("------ 类型判断 ------");
        System.out.println(Customer.class.isInterface());
        System.out.println(OrderStatus.class.isEnum());
        System.out.println(Product[].class.isArray());
        System.out.println(Object.class.isAssignableFrom(Customer.class));
    }

    private static void showEnumClass() {
        System.out.println("------ 枚举常量 ------");
        for (OrderStatus status : OrderStatus.class.getEnumConstants()) {
            System.out.println(status.name() + ": " + status.getDescription());
        }
    }
}
