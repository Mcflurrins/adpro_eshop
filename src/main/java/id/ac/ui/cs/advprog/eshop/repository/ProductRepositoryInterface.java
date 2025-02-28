package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

public interface ProductRepositoryInterface {
    Product create(Product product);
    Iterator<Product> findAll();
    boolean delete(String productId);
    Optional<Product> findById(String productId);
    Product update(String productId, Product updatedProduct);
}
