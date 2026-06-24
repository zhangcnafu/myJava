package com.zhangcanfu.myJava.enumeration;

/**
 * 工作日枚举类
 * subName 中文名称
 * description 相关描述
 */
public enum WorkDay {

    /**
     * 这里表示定义了几个静态final属性,无参构造和有参构造
     * 1.MONDAY相当于 private static MONDAY = new MONDAY()
     * 2.定义几个类实例属性时需写在最前面，用,分隔，最后一个用;结尾，
     */
    MONDAY, TUESDAY("周二", "加油"),
    WEDNESDAY("周三", "过半"),
    THURSDAY, FRIDAY;

    private String subName;
    private String description;

    /**
     * private的构造器，外部不能new新的实例，也就是这个类只有声明的几个固定实例，因此称为枚举类
     */
    private WorkDay() {

    }

    private WorkDay(String subName, String description) {
        this.subName = subName;
        this.description = description;
    }

    public String getSubName() {
        return subName;
    }

    public String getDescription() {
        return description;
    }
}
