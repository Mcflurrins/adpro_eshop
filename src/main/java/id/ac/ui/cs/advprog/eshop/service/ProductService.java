package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import java.util.List;
import java.util.Optional;

public interface ProductService {
    Product create(Product product);
    List<Product> findAll();
    boolean delete(String productId);
    Optional<Product> findById(String productId);
    Product edit(Product editedProduct);

}
