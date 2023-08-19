package ar.com.facundobazan.dao;

import java.util.List;

public interface Crud<T> {

    int create(T t);

    T getById(int id);

    List<T> getAll();

    Boolean update(T t);

    Boolean delete(int id);
}
