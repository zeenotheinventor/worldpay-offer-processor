package com.worldpay.offerprocessor.dao;

import java.util.Optional;

/**
 * Dao
 */
public interface Dao<T> {

    int create(T t);
    Optional<T> get(int id);
    void update(T t);
    void delete(T t);
}