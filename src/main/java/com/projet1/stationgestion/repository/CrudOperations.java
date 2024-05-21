package com.projet1.stationgestion.repository;

import java.util.List;

public interface CrudOperations<T> {
    List<T> findAll();
    T save(T toSave);
    boolean update(T entity);
}

