package org.inofttech.butler.storage;

import org.inofttech.butler.model.Item;

import java.util.List;

public interface Storage<T extends Item> {

    T get(String uuid);

    void save(T item);

    void delete(String uuid);

    void update(T item);

    void clear();

    public List<T> getAllSorted();

    public int size();


}
