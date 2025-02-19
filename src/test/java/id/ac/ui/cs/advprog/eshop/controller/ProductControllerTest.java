package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import java.util.Arrays;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProductControllerTest {

    @Mock
    private ProductService productService;

    @Mock
    private Model model;

    @InjectMocks
    private ProductController productController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateProductPage() {
        String viewName = productController.createProductPage(model);
        verify(model).addAttribute(eq("product"), any(Product.class));
        assertEquals("CreateProduct", viewName);
    }

    @Test
    void testCreateProductPost() {
        Product product = new Product();
        String result = productController.createProductPost(product);
        verify(productService).create(product);
        assertEquals("redirect:/product/list", result);
    }

    @Test
    void testListProduct() {
        when(productService.findAll()).thenReturn(Arrays.asList(new Product(), new Product()));
        String viewName = productController.listProduct(model);
        verify(model).addAttribute(eq("products"), anyList());
        assertEquals("ProductList", viewName);
    }

    @Test
    void testDeleteProduct() {
        String result = productController.deleteProduct("123");
        verify(productService).delete("123");
        assertEquals("redirect:/product/list", result);
    }

    @Test
    void testEditProductPageWithExistingProduct() {
        Product product = new Product();
        when(productService.findById("123")).thenReturn(Optional.of(product));
        String viewName = productController.editProductPage("123", model);
        verify(model).addAttribute("product", product);
        assertEquals("EditProduct", viewName);
    }

    @Test
    void testEditProductPageWithNonExistingProduct() {
        when(productService.findById("123")).thenReturn(Optional.empty());
        String viewName = productController.editProductPage("123", model);
        assertEquals("redirect:/product/list", viewName);
    }

    @Test
    void testEditProduct() {
        Product product = new Product();
        String result = productController.editProduct(product);
        verify(productService).edit(product);
        assertEquals("redirect:/product/list", result);
    }
}
