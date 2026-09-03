package com.zhangcanfu.myjava.app.generic;

import com.zhangcanfu.myjava.app.generic.model.*;

import java.util.Arrays;
import java.util.List;

public class GenericUsageTest {

    public static void main(String[] args) {
        showGenericClass();
        showGenericMethod();
        showGenericInterface();
    }

    private static void showGenericClass() {
        System.out.println("------ 泛型类 ------");

        Box<Student> studentBox = new Box<>(new Student(1L, "张三", 18));
        Student student = studentBox.getValue();
        System.out.println(student);

        Pair<Long, Course> coursePair = new Pair<>(101L, new Course(101L, "泛型"));
        System.out.println(coursePair.getKey());
        System.out.println(coursePair.getValue());
    }

    private static void showGenericMethod() {
        System.out.println("------ 泛型方法 ------");

        List<String> names = Arrays.asList("张三", "李四", "王五");
        String firstName = GenericMethods.first(names);
        System.out.println(firstName);

        Integer[] scores = {90, 86, 95};
        GenericMethods.printArray(scores);

        Pair<String, Integer> pair = GenericMethods.createPair("score", 100);
        System.out.println(pair.getKey() + ": " + pair.getValue());
    }

    private static void showGenericInterface() {
        System.out.println("------ 泛型接口 ------");

        Repository<Student, Long> studentRepository = new InMemoryRepository<>();
        studentRepository.save(1L, new Student(1L, "张三", 18));
        studentRepository.save(2L, new Student(2L, "李四", 19));

        Student student = studentRepository.findById(1L);
        List<Student> students = studentRepository.findAll();

        System.out.println(student);
        System.out.println(students);
    }
}
