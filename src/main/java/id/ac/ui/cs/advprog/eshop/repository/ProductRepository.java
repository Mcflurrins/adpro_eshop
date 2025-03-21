package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository implements ProductRepositoryInterface {
    private List<Product> productData = new ArrayList<>();

    public Product create(Product product) {
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }

    public boolean delete(String productId) {
        return productData.removeIf(product -> product.getProductId().equals(productId));
    }

    public Optional<Product> findById(String productId) {
        return productData.stream()
                .filter(product -> product.getProductId().equals(productId))
                .findFirst();
    }

    public Product update(String productId, Product updatedProduct) {
        Optional<Product> existingProduct = findById(productId);
        existingProduct.ifPresent(product -> {
            product.setProductName(updatedProduct.getProductName());
            product.setProductQuantity(updatedProduct.getProductQuantity());
        });
        return existingProduct.orElse(null); // Return updated product or null if not found
    }
}
