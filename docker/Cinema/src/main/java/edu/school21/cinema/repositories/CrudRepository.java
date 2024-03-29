package edu.school21.cinema.repositories;

import java.util.List;

public interface CrudRepository<T> {
    T findById(Long id);
    List<T> findAll();
    void save(T entity);
    void delete(Long id);
    void update(T entity);
}
