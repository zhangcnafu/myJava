package com.zhangcanfu.myjava.app.reflect;

import com.zhangcanfu.myjava.app.reflect.model.OrderMethodExamples;
import com.zhangcanfu.myjava.app.reflect.model.Product;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.List;

public class TypeUsageTest {

    public static void main(String[] args) throws NoSuchMethodException, NoSuchFieldException {
        showTypeIsUniversal();
        showParameterizedTypeFromField();
        showTypeVariableFromGenericMethod();
        showTypeVariableFromGenericClass();
        showActualTypeArguments();
    }

    private static void showTypeIsUniversal() throws NoSuchMethodException {
        System.out.println("------ Type 是一切可声明类型的顶层视图 ------");

        Type typeOfClass = Product.class;
        Type typeOfMethodReturn = OrderMethodExamples.class
                .getMethod("normalizeOrderNo", String.class)
                .getGenericReturnType();
        Type typeOfVarargsParam = OrderMethodExamples.class
                .getMethod("addTags", Product.class, String[].class)
                .getGenericParameterTypes()[1];

        printType("Product.class", typeOfClass);
        printType("normalizeOrderNo 的返回类型", typeOfMethodReturn);
        printType("addTags 的 tags 参数类型", typeOfVarargsParam);
    }

    private static void showParameterizedTypeFromField() throws NoSuchFieldException {
        System.out.println("------ 通过字段得到 ParameterizedType：Product.tags ------");

        Field tagsField = Product.class.getDeclaredField("tags");
        Type genericFieldType = tagsField.getGenericType();

        printType("字段 tags 的 genericType", genericFieldType);
        if (genericFieldType instanceof ParameterizedType parameterizedType) {
            System.out.println("rawType: " + parameterizedType.getRawType());
            System.out.println("ownerType: " + parameterizedType.getOwnerType());
            System.out.println("actualTypeArguments: " + Arrays.toString(parameterizedType.getActualTypeArguments()));
        }
        System.out.println();
    }

    private static void showTypeVariableFromGenericMethod() throws NoSuchMethodException {
        Method method = OrderMethodExamples.class.getMethod("firstOrDefault", List.class, Object.class);
        Type returnType = method.getGenericReturnType();
        Type[] parameterizedTypes = method.getGenericParameterTypes();

        System.out.println("------ 解析泛型方法：firstOrDefault<T>(List<T>, T) ------");
        printType("方法返回值（T）", returnType);

        if (parameterizedTypes.length > 0) {
            printType("第一个参数 List<T>", parameterizedTypes[0]);
        }

        TypeVariable<Method>[] typeVariables = method.getTypeParameters();
        for (TypeVariable<Method> variable : typeVariables) {
            System.out.println("方法声明的 TypeVariable: " + variable.getName());
            System.out.println("声明者: " + variable.getGenericDeclaration());
            System.out.println("可选上界: " + Arrays.toString(variable.getBounds()));
            System.out.println("注解上界: " + Arrays.toString(variable.getAnnotatedBounds()));
        }
        System.out.println();
    }

    private static void showTypeVariableFromGenericClass() {
        System.out.println("------ 解析泛型类：GenericPair<K, V> ------");
        TypeVariable<Class<GenericPair>>[] variables = GenericPair.class.getTypeParameters();

        for (TypeVariable<Class<GenericPair>> variable : variables) {
            System.out.println("类声明的 TypeVariable: " + variable.getName());
            System.out.println("声明者: " + variable.getGenericDeclaration());
            System.out.println("默认上界: " + Arrays.toString(variable.getBounds()));
            System.out.println("是否有注解上界: " + Arrays.toString(variable.getAnnotatedBounds()));
        }
        System.out.println();
    }

    private static void showActualTypeArguments() {
        System.out.println("------ 看一看泛型类实际化后的参数 ------");
        Type superType = StringIntegerPair.class.getGenericSuperclass();
        printType("StringIntegerPair 的 generic superclass", superType);

        if (superType instanceof ParameterizedType parameterizedType) {
            Type[] args = parameterizedType.getActualTypeArguments();
            System.out.println("GenericPair<String, Integer> 的实参列表: " + Arrays.toString(args));
        }

        System.out.println();
    }

    private static void printType(String label, Type type) {
        System.out.println(label + " -> 类型名: " + type.getTypeName());
        System.out.println("实现类: " + type.getClass().getName());
        System.out.println("接口层面: Type");
        System.out.println();
    }

    public static class GenericPair<K, V> {
        private K key;
        private V value;
    }

    public static class StringIntegerPair extends GenericPair<String, Integer> {
    }
}
