package com.zhangcanfu.myjava.app.reflect;

import com.zhangcanfu.myjava.app.reflect.model.Customer;
import com.zhangcanfu.myjava.app.reflect.model.Product;
import com.zhangcanfu.myjava.app.reflect.model.PromotionCode;
import com.zhangcanfu.myjava.app.reflect.model.ShoppingOrder;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.Arrays;

public class ConstructorUsageTest {

    public static void main(String[] args) throws ReflectiveOperationException {
        showPublicConstructors();
        showDeclaredConstructors();
        createObjectByNoArgsConstructor();
        createObjectByArgsConstructor();
        createOrderByArgsConstructor();
        createObjectByPrivateConstructor();
    }

    private static void showPublicConstructors() {
        Class<Customer> customerClass = Customer.class;

        System.out.println("------ getConstructors: 只能获取 public 构造器 ------");
        for (Constructor<?> constructor : customerClass.getConstructors()) {
            printConstructor(constructor);
        }
    }

    private static void showDeclaredConstructors() {
        Class<PromotionCode> promotionCodeClass = PromotionCode.class;

        System.out.println("------ getDeclaredConstructors: 获取当前类声明的所有构造器 ------");
        for (Constructor<?> constructor : promotionCodeClass.getDeclaredConstructors()) {
            printConstructor(constructor);
        }
    }

    private static void createObjectByNoArgsConstructor() throws ReflectiveOperationException {
        Class<Customer> customerClass = Customer.class;
        Constructor<Customer> constructor = customerClass.getConstructor();

        Customer customer = constructor.newInstance();

        System.out.println("------ 通过无参 public 构造器创建对象 ------");
        System.out.println(customer);
    }

    private static void createObjectByArgsConstructor() throws ReflectiveOperationException {
        Class<Customer> customerClass = Customer.class;
        Constructor<Customer> constructor = customerClass.getConstructor(
                Long.class,
                String.class,
                String.class,
                int.class
        );

        Customer customer = constructor.newInstance(1L, "张三", "13812345678", 20);

        System.out.println("------ 通过有参 public 构造器创建对象 ------");
        System.out.println(customer.getId());
        System.out.println(customer.getDisplayName());
        System.out.println(customer.isAdult());
    }

    private static void createObjectByPrivateConstructor() throws ReflectiveOperationException {
        Class<PromotionCode> promotionCodeClass = PromotionCode.class;
        Constructor<PromotionCode> constructor = promotionCodeClass.getDeclaredConstructor(
                String.class,
                BigDecimal.class
        );

        constructor.setAccessible(true);
        PromotionCode promotionCode = constructor.newInstance("NEW_USER_20", new BigDecimal("20.00"));

        System.out.println("------ 通过 private 构造器创建对象 ------");
        System.out.println(promotionCode);
    }

    private static void printConstructor(Constructor<?> constructor) {
        System.out.println("所属类: " + constructor.getDeclaringClass().getSimpleName());
        System.out.println("构造器名: " + constructor.getName());
        System.out.println("修饰符: " + Modifier.toString(constructor.getModifiers()));
        System.out.println("参数个数: " + constructor.getParameterCount());
        System.out.println("参数类型: " + Arrays.toString(constructor.getParameterTypes()));
        System.out.println();
    }

    private static void createOrderByArgsConstructor() throws ReflectiveOperationException {
        Customer customer = new Customer(1L, "张三", "13812345678", 20);
        Product product = new Product("BOOK-001", "反射", new BigDecimal("68.00"), 10);

        Constructor<ShoppingOrder> constructor = ShoppingOrder.class.getConstructor(
                String.class,
                Customer.class,
                Product.class,
                int.class
        );
        ShoppingOrder order = constructor.newInstance("ORDER-1001", customer, product, 2);

        System.out.println("------ 通过订单有参构造器创建对象 ------");
        System.out.println(order.getOrderNo());
        System.out.println(order.getCustomer().getDisplayName());
        System.out.println(order.calculateTotalAmount());
    }
}
