package id.ac.ui.cs.advprog.eshop.repository;

import java.util.Iterator;
import java.util.Optional;

public interface GeneralRepositoryInterface<T, ID> {
    T create(T entity);
    Iterator<T> findAll();
    boolean delete(ID id);
    Optional<T> findById(ID id);
    T update(ID id, T updatedEntity);
}
