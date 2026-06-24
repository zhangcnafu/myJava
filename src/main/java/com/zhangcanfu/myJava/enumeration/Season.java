package com.zhangcanfu.myJava.enumeration;

/**
 * subName 季节中文名称
 * description 季节相关描述
 */
public class Season {

    private String subName;
    private String description;

    // 公共静态不可变Season类的实例对象属性，调用私有构造器赋值实例对象
    public final static Season SPRING = new Season("春天", "温暖");
    public final static Season SUMMER = new Season("夏天", "炎热");
    public final static Season AUTUMN = new Season("秋天", "凉爽");
    public final static Season WINTER = new Season("冬天", "寒冷");

    // 声明为private,外部就无法new这类的对象，也就是说只有公共静态属性指定的几个实例对象可以访问
    private Season(String subName, String description) {
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
