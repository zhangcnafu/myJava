package com.zhangcanfu.myjava.app.enumeration;

public class EnumTest {

    public static void main(String[] args) {

        // 自定义Season类模仿枚举类的作用
        Season spring = Season.SPRING;
        System.out.println(spring.getSubName() + ' ' + spring.getDescription());
        System.out.println(Season.SUMMER.getSubName() + ' ' + Season.SPRING.getDescription());
        System.out.println(Season.AUTUMN.getSubName() + ' ' + Season.SPRING.getDescription());
        System.out.println(Season.WINTER.getSubName() + ' ' + Season.SPRING.getDescription());

        WorkDay monday = WorkDay.MONDAY;
        System.out.println(monday);
        System.out.println(WorkDay.TUESDAY.getSubName() + ' ' + WorkDay.TUESDAY.getDescription());
        System.out.println(WorkDay.WEDNESDAY.name());
        System.out.println(WorkDay.THURSDAY.toString());
        System.out.println(WorkDay.FRIDAY);

        /**
         * 相等比较 由于是同一个实例，地址相同，可以用==比较。
         * equals方法继承自Enum<E>,内部使用==比较
         */
        System.out.println("------相等性比较--------");
        System.out.println(monday == WorkDay.MONDAY);
        System.out.println(monday.equals(WorkDay.MONDAY));

        // 遍历: values是emun枚举类的隐式声明的静态函数，返回包含枚举类里所有的实例的数组
        System.out.println("------遍历---------");
        for (WorkDay someDay : WorkDay.values()) {
            System.out.println(someDay);
        }
    }
}
