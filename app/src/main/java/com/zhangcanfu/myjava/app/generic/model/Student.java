package com.zhangcanfu.myjava.app.generic.model;

public class Student extends Person {

    private Integer age;

    public Student(Long id, String name, Integer age) {
        super(id, name);
        this.age = age;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + getId() +
                ", name='" + getName() + '\'' +
                ", age=" + age +
                '}';
    }
}
