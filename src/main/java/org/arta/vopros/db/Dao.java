package org.arta.vopros.db;

import org.arta.vopros.domain.User;

import java.util.List;

public interface Dao<T, K> {
    T save(T t);
    boolean delete(K id);
    boolean update(T t);
    T findById(K id);
    List<T> findAll();
}
