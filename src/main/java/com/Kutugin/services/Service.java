package com.Kutugin.services;

import java.util.List;

public interface Service<T> {
    boolean save(T item);
    void deleteById(long id);
    boolean isPresent(String id);
    void update(long id, T item);
    List<T> getAll();
}
