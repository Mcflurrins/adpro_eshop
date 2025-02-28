package id.ac.ui.cs.advprog.eshop.service;

import java.util.List;
import java.util.Optional;

public interface GeneralService<T, ID> {
    T create(T entity);
    List<T> findAll();
    Optional<T> findById(ID id);
    T update(ID id, T entity);
    boolean delete(ID id);
}
