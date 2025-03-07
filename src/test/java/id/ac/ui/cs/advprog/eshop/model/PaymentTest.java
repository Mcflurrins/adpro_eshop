package id.ac.ui.cs.advprog.eshop.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.HashMap;
import java.util.Map;

public class PaymentTest {

    private Order order;
    private String method;
    private Map<String, String> paymentData;
    private Payment payment;

    @BeforeEach
    public void setUp() {
        order = Mockito.mock(Order.class); // Mock Order
        method = "Generic";
        paymentData = new HashMap<>();
        payment = new Payment(order, method, paymentData); // Use new constructor
    }

    @Test
    public void testCorrectAttributesOnCreation() {
        assertNotNull(payment.getId()); // ID should be auto-generated
        assertEquals(method, payment.getMethod());
        assertEquals(paymentData, payment.getPaymentData());
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    public void testVoucherCodeValid() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        payment = new Payment(order, "VoucherCode", paymentData);
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    public void testVoucherCodeInvalid() {
        paymentData.put("voucherCode", "ESHOP123");
        payment = new Payment(order, "VoucherCode", paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    public void testCashOnDeliveryValid() {
        paymentData.put("address", "123 Main St");
        paymentData.put("deliveryFee", "15.00");
        payment = new Payment(order, "CashOnDelivery", paymentData);
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    public void testCashOnDeliveryEmptyAddress() {
        paymentData.put("address", "");
        paymentData.put("deliveryFee", "15.00");
        payment = new Payment(order, "CashOnDelivery", paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    public void testCashOnDeliveryEmptyDeliveryFee() {
        paymentData.put("address", "123 Main St");
        paymentData.put("deliveryFee", "");
        payment = new Payment(order, "CashOnDelivery", paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    public void testSetIllegalStatus() {
        assertThrows(IllegalArgumentException.class, () -> payment.setStatus("INVALID_STATUS"));
    }

    @Test
    public void testChangeStatusWithSetStatus() {
        payment.setStatus("SUCCESS");
        assertEquals("SUCCESS", payment.getStatus());
    }
}
