package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreate() {
        Product product = new Product();
        when(productRepository.create(product)).thenReturn(product);

        Product createdProduct = productService.create(product);

        assertNotNull(createdProduct);
        assertEquals(product, createdProduct);
        verify(productRepository, times(1)).create(product);
    }

    @Test
    void testFindAll() {
        Product product1 = new Product();
        Product product2 = new Product();
        List<Product> productList = Arrays.asList(product1, product2);

        Iterator<Product> iteratorMock = productList.iterator();
        when(productRepository.findAll()).thenReturn(iteratorMock);

        List<Product> result = productService.findAll();

        assertEquals(2, result.size());
        assertTrue(result.contains(product1));
        assertTrue(result.contains(product2));
        verify(productRepository, times(1)).findAll();
    }

    @Test
    void testDelete() {
        String productId = "123";
        when(productRepository.delete(productId)).thenReturn(true);

        boolean result = productService.delete(productId);

        assertTrue(result);
        verify(productRepository, times(1)).delete(productId);
    }

    @Test
    void testFindById() {
        String productId = "123";
        Product product = new Product();
        when(productRepository.findById(productId)).thenReturn(Optional.of(product));

        Optional<Product> foundProduct = productService.findById(productId);

        assertTrue(foundProduct.isPresent());
        assertEquals(product, foundProduct.get());
        verify(productRepository, times(1)).findById(productId);
    }

    @Test
    void testFindByIdNotFound() {
        String productId = "123";
        when(productRepository.findById(productId)).thenReturn(Optional.empty());

        Optional<Product> foundProduct = productService.findById(productId);

        assertFalse(foundProduct.isPresent());
        verify(productRepository, times(1)).findById(productId);
    }

    @Test
    void testEdit() {
        Product product = new Product();
        when(productRepository.edit(product)).thenReturn(product);

        Product updatedProduct = productService.edit(product);

        assertNotNull(updatedProduct);
        assertEquals(product, updatedProduct);
        verify(productRepository, times(1)).edit(product);
    }
}
