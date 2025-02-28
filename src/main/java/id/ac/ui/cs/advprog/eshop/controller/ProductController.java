package id.ac.ui.cs.advprog.eshop.controller;
import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.service.CarServiceImpl;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    // Fixed Issue from SonarCloud: Define a constant instead of duplicating this literal "redirect:/product/list" 4 times
    // made new variable instead
    private static final String REDIRECT_PRODUCT_LIST = "redirect:/product/list";

    // Fixed issue from Sonarcloud: Remove this field injection and use constructor injection instead.
    private final ProductService service;

    public ProductController(ProductService service) { // Constructor injection
        this.service = service;
    }

    @GetMapping("/create")
    public String createProductPage(Model model) {
        Product product = new Product();
        model.addAttribute("product", product);
        return "CreateProduct";
    }

    @PostMapping("/create")
    public String createProductPost(@ModelAttribute Product product) {
        service.create(product);
        return REDIRECT_PRODUCT_LIST;
    }

    @GetMapping("/list")
    public String listProduct(Model model) {
        List<Product> allProducts = service.findAll();
        model.addAttribute("products", allProducts);
        return "ProductList";
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable String id) {
        service.delete(id);
        return REDIRECT_PRODUCT_LIST;
    }

    @GetMapping("/edit/{id}")
    public String editProductPage(@PathVariable String id, Model model) {
        Optional<Product> product = service.findById(id);
        if (product.isPresent()) {
            model.addAttribute("product", product.get());
            return "EditProduct";
        }
        return REDIRECT_PRODUCT_LIST;
    }

    @PostMapping("/edit")
    public String editProduct(@ModelAttribute Product product) {
        service.update(product.getProductId(), product);
        return REDIRECT_PRODUCT_LIST;
    }
}
