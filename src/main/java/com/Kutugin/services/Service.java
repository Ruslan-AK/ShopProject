package com.Kutugin.services;

import java.util.List;

public interface Service<T> {
    public boolean save(T item);
    void deleteById(long id);
    boolean contains(String id);
    void update(long id, T item);
    List<T> getAll();
}
