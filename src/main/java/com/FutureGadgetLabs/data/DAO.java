package com.FutureGadgetLabs.data;

import org.springframework.stereotype.Component;

import java.sql.SQLException;
import java.util.List;

@Component
public interface DAO<T> {

    T get(int id);

    List<T> getAll();

    void insert(T t);

    void batchInsert(List<T> t) throws SQLException;

    void update(T t);

    void delete(int id);

}
