package com.zhangcanfu.myjava.app.reflect;

import com.zhangcanfu.myjava.app.reflect.model.Customer;
import com.zhangcanfu.myjava.app.reflect.model.OrderStatus;
import com.zhangcanfu.myjava.app.reflect.model.Product;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;

public class FieldUsageTest {

    public static void main(String[] args) throws ReflectiveOperationException {
        showPublicFields();
        showDeclaredFields();
        readStaticField();
        readPrivateField();
        updatePrivateField();
        showPrimitiveFieldAccess();
        showGenericField();
        showEnumConstantField();
    }

    private static void showPublicFields() {
        Class<Customer> customerClass = Customer.class;

        System.out.println("------ getFields: public 字段，包含继承来的 public 字段 ------");
        for (Field field : customerClass.getFields()) {
            printField(field);
        }
    }

    private static void showDeclaredFields() {
        Class<Customer> customerClass = Customer.class;

        System.out.println("------ getDeclaredFields: 当前类声明的所有字段 ------");
        for (Field field : customerClass.getDeclaredFields()) {
            printField(field);
        }
    }

    private static void readStaticField() throws ReflectiveOperationException {
        Field field = Customer.class.getField("DEFAULT_CITY");

        System.out.println("------ 读取 public static 字段 ------");
        System.out.println(field.get(null));
    }

    private static void readPrivateField() throws ReflectiveOperationException {
        Customer customer = new Customer(1L, "张三", "13812345678", 20);
        Field nameField = Customer.class.getDeclaredField("name");

        System.out.println("------ 读取 private 字段 ------");
        try {
            System.out.println(nameField.get(customer));
        } catch (IllegalAccessException exception) {
            System.out.println("没有打开访问权限时，不能直接读取 private 字段");
        }

        nameField.setAccessible(true);
        System.out.println(nameField.get(customer));
    }

    private static void updatePrivateField() throws ReflectiveOperationException {
        Customer customer = new Customer(1L, "张三", "13812345678", 20);
        Field nameField = Customer.class.getDeclaredField("name");
        Field vipField = Customer.class.getDeclaredField("vip");

        nameField.setAccessible(true);
        vipField.setAccessible(true);

        nameField.set(customer, "李四");
        vipField.setBoolean(customer, true);

        System.out.println("------ 修改 private 字段 ------");
        System.out.println(customer.getDisplayName());
    }

    private static void showPrimitiveFieldAccess() throws ReflectiveOperationException {
        Customer customer = new Customer(1L, "张三", "13812345678", 20);
        Field ageField = Customer.class.getDeclaredField("age");

        ageField.setAccessible(true);
        ageField.setInt(customer, 25);

        System.out.println("------ 读取和修改基本类型字段 ------");
        System.out.println(ageField.getInt(customer));
        System.out.println(customer.isAdult());
    }

    private static void showGenericField() throws ReflectiveOperationException {
        Product product = new Product("BOOK-001", "Java 反射入门", new BigDecimal("68.00"), 10);
        product.addTag("编程");
        product.addTag("Java");

        Field tagsField = Product.class.getDeclaredField("tags");
        tagsField.setAccessible(true);

        System.out.println("------ getType 和 getGenericType 的区别 ------");
        System.out.println(tagsField.getType().getName());
        System.out.println(tagsField.getGenericType().getTypeName());
        System.out.println(tagsField.get(product));
    }

    private static void showEnumConstantField() throws ReflectiveOperationException {
        Field paidField = OrderStatus.class.getField("PAID");

        System.out.println("------ 枚举常量也是字段 ------");
        System.out.println(paidField.isEnumConstant());
        System.out.println(paidField.get(null));
    }

    private static void printField(Field field) {
        System.out.println("所属类: " + field.getDeclaringClass().getSimpleName());
        System.out.println("字段名: " + field.getName());
        System.out.println("字段类型: " + field.getType().getSimpleName());
        System.out.println("泛型类型: " + field.getGenericType().getTypeName());
        System.out.println("修饰符: " + Modifier.toString(field.getModifiers()));
        System.out.println("是否枚举常量: " + field.isEnumConstant());
        System.out.println("是否编译器生成: " + field.isSynthetic());
        System.out.println();
    }
}
