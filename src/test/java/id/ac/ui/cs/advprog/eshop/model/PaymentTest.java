package id.ac.ui.cs.advprog.eshop.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.Map;

public class PaymentTest {

    private String id;
    private String method;
    private Map<String, String> paymentData;
    private Payment payment;

    @BeforeEach
    public void setUp() {
        id = "payment1";
        method = "Generic";
        paymentData = new HashMap<>();
        payment = new Payment(id, method, paymentData);
    }

    @Test
    public void testCorrectAttributesOnCreation() {
        assertEquals(id, payment.getId());
        assertEquals(method, payment.getMethod());
        assertEquals(paymentData, payment.getPaymentData());
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    public void testVoucherCodeValid() {
        paymentData.put("voucherCode", "ESHOP1234ABC5678");
        payment = new Payment(id, "VoucherCode", paymentData);
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    public void testVoucherCodeInvalid() {
        paymentData.put("voucherCode", "ESHOP123");
        payment = new Payment(id, "VoucherCode", paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    public void testCashOnDeliveryValid() {
        paymentData.put("address", "123 Main St");
        paymentData.put("deliveryFee", "15.00");
        payment = new Payment(id, "CashOnDelivery", paymentData);
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    public void testCashOnDeliveryEmptyAddress() {
        paymentData.put("address", "");
        paymentData.put("deliveryFee", "15.00");
        payment = new Payment(id, "CashOnDelivery", paymentData);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    public void testCashOnDeliveryEmptyDeliveryFee() {
        paymentData.put("address", "123 Main St");
        paymentData.put("deliveryFee", "");
        payment = new Payment(id, "CashOnDelivery", paymentData);
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
