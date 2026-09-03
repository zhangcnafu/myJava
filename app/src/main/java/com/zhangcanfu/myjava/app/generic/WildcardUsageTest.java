package com.zhangcanfu.myjava.app.generic;

import com.zhangcanfu.myjava.app.generic.model.Box;
import com.zhangcanfu.myjava.app.generic.model.Course;
import com.zhangcanfu.myjava.app.generic.model.Person;
import com.zhangcanfu.myjava.app.generic.model.Student;

public class WildcardUsageTest {

    public static void main(String[] args) {
        showUnknownWildcard();
        showExtendsWildcard();
        showSuperWildcard();
    }

    private static void showUnknownWildcard() {
        System.out.println("------ ? 通配符：未知类型 ------");

        Box<Student> studentBox = new Box<>(new Student(1L, "张三", 18));
        Box<Course> courseBox = new Box<>(new Course(101L, "泛型"));

        printBox(studentBox);
        printBox(courseBox);
    }

    private static void printBox(Box<?> box) {
        Object value = box.getValue();
        System.out.println(value);

        // box.setValue(new Student(2L, "李四", 19));
        // 上面这行不能打开，因为 Box<?> 的真实类型未知。
        // 它可能是 Box<Student>，也可能是 Box<Course>，随便写入会破坏类型安全。
    }

    private static void showExtendsWildcard() {
        System.out.println("------ ? extends Person：适合读取 ------");

        Box<Person> personBox = new Box<>(new Person(1L, "普通用户"));
        Box<Student> studentBox = new Box<>(new Student(2L, "学生用户", 18));

        printPersonName(personBox);
        printPersonName(studentBox);
    }

    private static void printPersonName(Box<? extends Person> box) {
        Person person = box.getValue();
        System.out.println(person.getName());

        // box.setValue(new Student(3L, "新学生", 20));
        // 上面这行不能打开，因为真实类型可能是 Box<Person>，也可能是 Box<Student>。
        // ? extends Person 能保证读出来至少是 Person，但不能保证可以写入 Student。
    }

    private static void showSuperWildcard() {
        System.out.println("------ ? super Student：适合写入 ------");

        Box<Student> studentBox = new Box<>(new Student(1L, "原学生", 18));
        Box<Person> personBox = new Box<>(new Person(2L, "原用户"));
        Box<Object> objectBox = new Box<>(new Object());

        saveStudent(studentBox);
        saveStudent(personBox);
        saveStudent(objectBox);

        System.out.println(studentBox.getValue());
        System.out.println(personBox.getValue());
        System.out.println(objectBox.getValue());
    }

    private static void saveStudent(Box<? super Student> box) {
        box.setValue(new Student(3L, "新学生", 20));

        Object value = box.getValue();
        System.out.println("读取时只能安全地当 Object 使用：" + value);

        // Student student = box.getValue();
        // 上面这行不能打开，因为真实类型可能是 Box<Object>。
        // ? super Student 能保证可以写入 Student，但不能保证读出来一定是 Student。
    }
}
