package com.zhangcanfu.myjava.app.reflect;

import com.zhangcanfu.myjava.app.reflect.model.OrderMethodExamples;
import com.zhangcanfu.myjava.app.reflect.model.OrderTag;
import com.zhangcanfu.myjava.app.reflect.model.Product;
import com.zhangcanfu.myjava.app.reflect.model.ParameterModel;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.lang.reflect.TypeVariable;
import java.util.Arrays;
import java.util.List;

public class ParameterUsageTest {

    public static void main(String[] args) throws ReflectiveOperationException {
        inspectMethodParameters();
        inspectVarArgsParameters();
        inspectGenericMethodParameters();
        inspectConstructorParameters();
    }

    private static void inspectMethodParameters() throws NoSuchMethodException {
        Method method = OrderMethodExamples.class.getMethod("normalizeOrderNo", String.class);
        Parameter[] parameters = method.getParameters();

        System.out.println("------ 单参数方法（基本类型示例） ------");
        printParameters(method.getName(), parameters);
    }

    private static void inspectVarArgsParameters() throws NoSuchMethodException {
        Method method = OrderMethodExamples.class.getMethod("addTags", Product.class, String[].class);
        Parameter[] parameters = method.getParameters();

        System.out.println("------ 可变参数方法参数 ------");
        printParameters(method.getName(), parameters);
    }

    private static void inspectGenericMethodParameters() throws NoSuchMethodException {
        Method method = OrderMethodExamples.class.getMethod("firstOrDefault", List.class, Object.class);
        Parameter[] parameters = method.getParameters();

        System.out.println("------ 泛型方法参数 ------");
        printParameters(method.getName(), parameters);

        for (TypeVariable<?> variable : method.getTypeParameters()) {
            System.out.println("方法类型变量: " + variable.getName());
        }
        System.out.println();
    }

    private static void inspectConstructorParameters() throws NoSuchMethodException {
        Constructor<?> constructor = ParameterModel.class.getDeclaredConstructor(String.class, int.class, String.class);
        Parameter[] parameters = constructor.getParameters();

        System.out.println("------ 构造器参数（含自定义参数注解）------");
        printParameters(constructor.getName(), parameters);
    }

    private static void printParameters(String title, Parameter[] parameters) {
        System.out.println("上下文: " + title);
        for (Parameter parameter : parameters) {
            System.out.println("参数名: " + parameter.getName());
            System.out.println("名称是否保留: " + parameter.isNamePresent());
            System.out.println("类型: " + parameter.getType().getName());
            System.out.println("泛型类型: " + parameter.getParameterizedType().getTypeName());
            System.out.println("修饰符: " + parameter.getModifiers());
            System.out.println("是否 varargs 元素: " + parameter.isVarArgs());
            System.out.println("是否隐式: " + parameter.isImplicit());
            System.out.println("是否编译器生成: " + parameter.isSynthetic());
            System.out.println("是否 final: " + java.lang.reflect.Modifier.isFinal(parameter.getModifiers()));

            System.out.println("注解: " + Arrays.toString(parameter.getAnnotations()));
            OrderTag tag = parameter.getAnnotation(OrderTag.class);
            if (tag != null) {
                System.out.println("自定义注解值: " + tag.value());
            }
            System.out.println("AnnotatedType: " + parameter.getAnnotatedType().getType());
            System.out.println();
        }
    }
}
