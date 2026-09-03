package com.zhangcanfu.myjava.app.generic.model;

import java.util.List;

public class GenericMethods {

    private GenericMethods() {
    }

    public static <T> T first(List<T> list) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }

    public static <T> void printArray(T[] array) {
        for (T item : array) {
            System.out.println(item);
        }
    }

    public static <K, V> Pair<K, V> createPair(K key, V value) {
        return new Pair<>(key, value);
    }
}
