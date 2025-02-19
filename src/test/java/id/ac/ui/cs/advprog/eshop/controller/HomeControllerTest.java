package id.ac.ui.cs.advprog.eshop.controller;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HomeControllerTest {

    @Test
    void testHomePage() {
        HomeController homeController = new HomeController();
        String viewName = homeController.homePage();
        assertEquals("Homepage", viewName);
    }
}
