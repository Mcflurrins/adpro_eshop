package id.ac.ui.cs.advprog.eshop.functional;

import io.github.bonigarcia.seljup.SeleniumJupiter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@SpringBootTest(webEnvironment = RANDOM_PORT)
@ExtendWith(SeleniumJupiter.class)
class CreateProductFunctionalTest {
    /**
     * The port number assigned to the running application during test execution.
     * Set automatically during each test run by Spring Framework's test context.
     */

    @LocalServerPort
    private int serverPort;
    /**
     * The base URL for testing. Default to {@code http://localhost}.
     */
    @Value("${app.baseUrl:http://localhost}")
    private String testBaseUrl;

    private String baseUrl;
    private String homeUrl;

    @BeforeEach
    void setupTest() {
        baseUrl = String.format("%s:%d/product/create", testBaseUrl, serverPort);
        homeUrl = String.format("%s:%d/product/list", testBaseUrl, serverPort);
    }

    @Test
    void testCreateProduct(ChromeDriver driver) {
        // Navigate to the product creation page
        driver.get(baseUrl);

        // Fill in product details
        WebElement productNameField = driver.findElement(By.id("nameInput"));
        WebElement productQuantityField = driver.findElement(By.id("quantityInput"));
        WebElement submitButton = driver.findElement(By.id("submit"));

        productNameField.sendKeys("Test Product");
        productQuantityField.sendKeys("10");
        submitButton.click();

        // Verify redirection to the product list
        driver.get(homeUrl);

        // Check if the product appears in the list
        WebElement productTable = driver.findElement(By.id("productTable"));
        assertTrue(productTable.getText().contains("Test Product"), "Product should be listed");
    }
}