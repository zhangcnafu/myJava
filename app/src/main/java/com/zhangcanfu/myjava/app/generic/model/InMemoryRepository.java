package com.zhangcanfu.myjava.app.generic.model;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class InMemoryRepository<T, ID> implements Repository<T, ID> {

    private final Map<ID, T> dataMap = new LinkedHashMap<>();

    @Override
    public void save(ID id, T data) {
        dataMap.put(id, data);
    }

    @Override
    public T findById(ID id) {
        return dataMap.get(id);
    }

    @Override
    public List<T> findAll() {
        return new ArrayList<>(dataMap.values());
    }
}
