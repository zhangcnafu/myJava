package com.zhangcanfu.myjava.app.reflect;

import com.zhangcanfu.myjava.app.reflect.model.Customer;
import com.zhangcanfu.myjava.app.reflect.model.OrderMethodExamples;
import com.zhangcanfu.myjava.app.reflect.model.OrderOperation;
import com.zhangcanfu.myjava.app.reflect.model.Product;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class MethodUsageTest {

    public static void main(String[] args) throws ReflectiveOperationException {
        showPublicMethods();
        showDeclaredMethods();
        invokeInstanceMethod();
        invokeStaticMethod();
        invokePrivateMethod();
        invokeVoidMethod();
        invokeVarArgsMethod();
        showGenericMethod();
        showExceptionTypes();
        showDefaultMethod();
    }

    private static void showPublicMethods() {
        Class<Customer> customerClass = Customer.class;

        System.out.println("------ getMethods: public 方法，包含继承来的 public 方法 ------");
        for (Method method : customerClass.getMethods()) {
            if (method.getDeclaringClass() == Customer.class) {
                printMethod(method);
            }
        }
    }

    private static void showDeclaredMethods() {
        Class<Customer> customerClass = Customer.class;

        System.out.println("------ getDeclaredMethods: 当前类声明的所有方法 ------");
        for (Method method : customerClass.getDeclaredMethods()) {
            printMethod(method);
        }
    }

    private static void invokeInstanceMethod() throws ReflectiveOperationException {
        Customer customer = new Customer(1L, "张三", "13812345678", 20);
        Method method = Customer.class.getMethod("getDisplayName");

        Object result = method.invoke(customer);

        System.out.println("------ 调用 public 实例方法 ------");
        System.out.println(result);
    }

    private static void invokeStaticMethod() throws ReflectiveOperationException {
        Method method = OrderMethodExamples.class.getMethod("normalizeOrderNo", String.class);

        Object result = method.invoke(null, " order-1001 ");

        System.out.println("------ 调用 public static 方法 ------");
        System.out.println(result);
    }

    private static void invokePrivateMethod() throws ReflectiveOperationException {
        Customer customer = new Customer(1L, "张三", "13812345678", 20);
        Method method = Customer.class.getDeclaredMethod("maskPhone");

        method.setAccessible(true);
        Object result = method.invoke(customer);

        System.out.println("------ 调用 private 方法 ------");
        System.out.println(result);
    }

    private static void invokeVoidMethod() throws ReflectiveOperationException {
        Product product = new Product("BOOK-001", "Java 反射入门", new BigDecimal("68.00"), 10);
        Method method = Product.class.getMethod("decreaseStock", int.class);

        Object result = method.invoke(product, 3);

        System.out.println("------ 调用返回值为 void 的方法 ------");
        System.out.println(result);
        System.out.println(product.getStock());
    }

    private static void invokeVarArgsMethod() throws ReflectiveOperationException {
        OrderMethodExamples examples = new OrderMethodExamples();
        Product product = new Product("BOOK-001", "Java 反射入门", new BigDecimal("68.00"), 10);
        Method method = OrderMethodExamples.class.getMethod("addTags", Product.class, String[].class);

        method.invoke(examples, product, new String[]{"编程", "Java", "反射"});

        System.out.println("------ 调用可变参数方法 ------");
        System.out.println(method.isVarArgs());
        System.out.println(product.getTags());
    }

    private static void showGenericMethod() throws ReflectiveOperationException {
        Method method = OrderMethodExamples.class.getMethod("firstOrDefault", List.class, Object.class);

        System.out.println("------ 泛型方法信息 ------");
        System.out.println(Arrays.toString(method.getTypeParameters()));//返回这个方法自己声明了哪些泛型变量
        System.out.println(method.getReturnType().getTypeName());
        System.out.println(method.getGenericReturnType().getTypeName());
        System.out.println(Arrays.toString(method.getParameterTypes()));
        System.out.println(Arrays.toString(method.getGenericParameterTypes()));
        System.out.println(method.toGenericString());
    }

    private static void showExceptionTypes() throws ReflectiveOperationException {
        OrderMethodExamples examples = new OrderMethodExamples();
        Method method = OrderMethodExamples.class.getMethod("checkQuantity", int.class);

        System.out.println("------ 方法声明的异常和 invoke 包装异常 ------");
        System.out.println(Arrays.toString(method.getExceptionTypes()));

        try {
            method.invoke(examples, 0);
        } catch (InvocationTargetException exception) {
            System.out.println(exception.getClass().getSimpleName());
            System.out.println(exception.getTargetException().getClass().getSimpleName());
            System.out.println(exception.getTargetException().getMessage());
        }
    }

    private static void showDefaultMethod() throws ReflectiveOperationException {
        OrderMethodExamples examples = new OrderMethodExamples();
        Method method = OrderOperation.class.getMethod("getOperationName");

        System.out.println("------ 接口 default 方法 ------");
        System.out.println(method.isDefault());
        System.out.println(method.invoke(examples));
    }

    private static void printMethod(Method method) {
        System.out.println("所属类: " + method.getDeclaringClass().getSimpleName());
        System.out.println("方法名: " + method.getName());
        System.out.println("修饰符: " + Modifier.toString(method.getModifiers()));
        System.out.println("返回类型: " + method.getReturnType().getSimpleName());
        System.out.println("泛型返回类型: " + method.getGenericReturnType().getTypeName());
        System.out.println("参数个数: " + method.getParameterCount());
        System.out.println("参数类型: " + Arrays.toString(method.getParameterTypes()));
        System.out.println("是否可变参数: " + method.isVarArgs());
        System.out.println("是否默认方法: " + method.isDefault());
        System.out.println("是否编译器生成: " + method.isSynthetic());
        System.out.println();
    }

}
