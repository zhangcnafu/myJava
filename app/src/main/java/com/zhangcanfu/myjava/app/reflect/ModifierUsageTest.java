package com.zhangcanfu.myjava.app.reflect;

import com.zhangcanfu.myjava.app.reflect.model.Customer;
import com.zhangcanfu.myjava.app.reflect.model.OrderMethodExamples;
import com.zhangcanfu.myjava.app.reflect.model.ParameterModel;
import com.zhangcanfu.myjava.app.reflect.model.Product;
import com.zhangcanfu.myjava.app.reflect.model.PromotionCode;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

public class ModifierUsageTest {

    public static void main(String[] args) throws ReflectiveOperationException {
        showClassModifiers();
        showFieldModifiers();
        showMethodModifiers();
        showConstructorModifiers();
        showAllowedModifierMasks();
    }

    private static void showClassModifiers() {
        printTarget("Customer class", Customer.class.getModifiers());
        printTarget("OrderStatus enum", com.zhangcanfu.myjava.app.reflect.model.OrderStatus.class.getModifiers());
        printTarget("Runnable interface", java.lang.Runnable.class.getModifiers());
    }

    private static void showFieldModifiers() throws NoSuchFieldException {
        Field publicConst = Customer.class.getField("DEFAULT_CITY");
        Field privateField = Customer.class.getDeclaredField("name");
        Field productField = Product.class.getDeclaredField("tags");

        printTarget("Customer.DEFAULT_CITY", publicConst.getModifiers());
        printTarget("Customer.name", privateField.getModifiers());
        printTarget("Product.tags", productField.getModifiers());
    }

    private static void showMethodModifiers() throws NoSuchMethodException {
        Method publicMethod = Customer.class.getMethod("getDisplayName");
        Method privateMethod = Customer.class.getDeclaredMethod("maskPhone");
        Method staticMethod = OrderMethodExamples.class.getMethod("normalizeOrderNo", String.class);
        Method varArgsMethod = OrderMethodExamples.class.getMethod("addTags", Product.class, String[].class);

        printTarget("Customer#getDisplayName", publicMethod.getModifiers());
        printTarget("Customer#maskPhone", privateMethod.getModifiers());
        printTarget("OrderMethodExamples#normalizeOrderNo", staticMethod.getModifiers());
        printTarget("OrderMethodExamples#addTags", varArgsMethod.getModifiers());
    }

    private static void showConstructorModifiers() throws NoSuchMethodException {
        Constructor<Customer> publicCtor = Customer.class.getConstructor();
        Constructor<Customer> argsCtor = Customer.class.getConstructor(Long.class, String.class, String.class, int.class);
        Constructor<PromotionCode> privateCtor = PromotionCode.class.getDeclaredConstructor(String.class, java.math.BigDecimal.class);

        printTarget("Customer()", publicCtor.getModifiers());
        printTarget("Customer(Long,String,String,int)", argsCtor.getModifiers());
        printTarget("PromotionCode(String,BigDecimal)", privateCtor.getModifiers());
    }

    private static void showAllowedModifierMasks() throws NoSuchMethodException {
        System.out.println("------ 合法修饰符集合（按语义解码，不是实例值） ------");
        printMask("Modifier.classModifiers()", Modifier.classModifiers());
        printMask("Modifier.interfaceModifiers()", Modifier.interfaceModifiers());
//        printMask("Modifier.enumModifiers()", Modifier.enumModifiers());
        printMask("Modifier.fieldModifiers()", Modifier.fieldModifiers());
        printMask("Modifier.methodModifiers()", Modifier.methodModifiers());
        printMask("Modifier.constructorModifiers()", Modifier.constructorModifiers());
        printMask("Modifier.parameterModifiers()", Modifier.parameterModifiers());

        printTarget("普通方法参数 (addTags 的第二参数)", getVarArgLastParameterModifiers());
    }

    private static int getVarArgLastParameterModifiers() throws NoSuchMethodException {
        Method varArgsMethod = OrderMethodExamples.class.getMethod("addTags", Product.class, String[].class);
        return varArgsMethod.getParameters()[1].getModifiers();
    }

    private static void printTarget(String name, int modifiers) {
        System.out.println("------ " + name + " ------");
        System.out.println("raw: " + modifiers);
        System.out.println("toString: " + Modifier.toString(modifiers));
        System.out.println("isPublic: " + Modifier.isPublic(modifiers));
        System.out.println("isProtected: " + Modifier.isProtected(modifiers));
        System.out.println("isPrivate: " + Modifier.isPrivate(modifiers));
        System.out.println("isStatic: " + Modifier.isStatic(modifiers));
        System.out.println("isFinal: " + Modifier.isFinal(modifiers));
        System.out.println("isAbstract: " + Modifier.isAbstract(modifiers));
        System.out.println("isNative: " + Modifier.isNative(modifiers));
        System.out.println("isSynchronized: " + Modifier.isSynchronized(modifiers));
        System.out.println("isTransient: " + Modifier.isTransient(modifiers));
        System.out.println("isVolatile: " + Modifier.isVolatile(modifiers));
        System.out.println("isStrict: " + Modifier.isStrict(modifiers));
        System.out.println();
    }

    private static void printMask(String name, int mask) {
        System.out.println(name + " -> " + Modifier.toString(mask));
        System.out.println();
    }
}
