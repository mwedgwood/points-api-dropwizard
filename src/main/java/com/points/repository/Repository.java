package com.points.repository;

import java.io.Serializable;
import java.util.Collection;

public interface Repository<T> {

    T findById(Serializable id);

    Collection<T> find();

    void save(T entity);

    void update(T entity);

    void delete(T entity);
}
