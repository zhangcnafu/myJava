package com.zhangcanfu.myjava.app.generic.model;

import java.util.List;

public interface Repository<T, ID> {

    void save(ID id, T data);

    T findById(ID id);

    List<T> findAll();
}
