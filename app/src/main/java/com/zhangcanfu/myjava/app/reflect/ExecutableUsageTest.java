package com.zhangcanfu.myjava.app.reflect;

import com.zhangcanfu.myjava.app.reflect.model.Customer;
import com.zhangcanfu.myjava.app.reflect.model.OrderMethodExamples;
import com.zhangcanfu.myjava.app.reflect.model.Product;

import java.lang.reflect.Executable;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.lang.reflect.TypeVariable;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

public class ExecutableUsageTest {

    public static void main(String[] args) throws ReflectiveOperationException {
        inspectMethodsAsExecutable();
        inspectConstructorsAsExecutable();
        inspectRuntimeInvocationRules();
    }

    private static void inspectMethodsAsExecutable() throws ReflectiveOperationException {
        Method method = OrderMethodExamples.class.getDeclaredMethod("addTags", Product.class, String[].class);
        Method genericMethod = OrderMethodExamples.class.getDeclaredMethod("firstOrDefault", List.class, Object.class);
        Method exceptionMethod = OrderMethodExamples.class.getDeclaredMethod("checkQuantity", int.class);

        printExecutable("1) 可变参数实例方法", method);
        printExecutable("2) 泛型实例方法", genericMethod);
        printExecutable("3) 声明异常的方法", exceptionMethod);

        printParameters(method);
        printParameters(genericMethod);
    }

    private static void inspectConstructorsAsExecutable() throws ReflectiveOperationException {
        Constructor<?> noArgs = Customer.class.getConstructor();
        Constructor<Customer> fullArgs = Customer.class.getConstructor(Long.class, String.class, String.class, int.class);

        printExecutable("4) 无参构造器", noArgs);
        printExecutable("5) 有参构造器", fullArgs);
    }

    private static void inspectRuntimeInvocationRules() {
        try {
            Method privateMethod = Customer.class.getDeclaredMethod("maskPhone");
            printExecutable("6) 可访问性对照（private）", privateMethod);
            Customer customer = new Customer(1L, "张三", "13812345678", 20);

            System.out.println("private 方法当前可直接访问: " + privateMethod.canAccess(customer));
            privateMethod.setAccessible(true);
            System.out.println("开启可访问性后可直接访问: " + privateMethod.canAccess(customer));
        } catch (NoSuchMethodException e) {
            System.out.println("找不到方法");
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException(e);
        }
    }

    private static void printExecutable(String title, Executable executable) throws ReflectiveOperationException {
        System.out.println("------ " + title + " ------");
        System.out.println("声明类: " + executable.getDeclaringClass().getSimpleName());
        System.out.println("成员名: " + executable.getName());
        System.out.println("签名字符串: " + executable.toGenericString());
        System.out.println("字符串形式: " + executable.toString());
        System.out.println("修饰符: " + Modifier.toString(executable.getModifiers()));
        System.out.println("参数数量: " + executable.getParameterCount());
        System.out.println("参数类型: " + Arrays.toString(executable.getParameterTypes()));
        System.out.println("泛型参数类型: " + Arrays.toString(executable.getGenericParameterTypes()));
        System.out.println("异常类型: " + Arrays.toString(executable.getExceptionTypes()));
        System.out.println("泛型异常类型: " + Arrays.toString(executable.getGenericExceptionTypes()));
        System.out.println("可变参数: " + executable.isVarArgs());
        System.out.println("是否由编译器生成: " + executable.isSynthetic());
        System.out.println("是否公开: " + Modifier.isPublic(executable.getModifiers()));
        System.out.println();

        TypeVariable<?>[] typeVariables = executable.getTypeParameters();
        if (typeVariables.length > 0) {
            System.out.println("泛型变量:");
            for (TypeVariable<?> variable : typeVariables) {
                System.out.println(" - " + variable.getName());
            }
        }
        System.out.println();
    }

    private static void printParameters(Executable executable) {
        System.out.println("参数名与属性（可能是编译时未保留真实参数名）");
        for (Parameter parameter : executable.getParameters()) {
            System.out.println(" - " + parameter.getType().getSimpleName() + " " + parameter.getName());
            if (parameter.isVarArgs()) {
                System.out.println("   (varargs)");
            }
        }
        System.out.println();
    }
}
