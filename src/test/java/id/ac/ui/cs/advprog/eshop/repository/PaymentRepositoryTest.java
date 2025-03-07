package id.ac.ui.cs.advprog.eshop.repository;

import static org.junit.jupiter.api.Assertions.*;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.*;

public class PaymentRepositoryTest {
    private PaymentRepository paymentRepository;
    private Order order;
    private Payment payment1;
    private Payment payment2;

    @BeforeEach
    public void setUp() {
        paymentRepository = new PaymentRepository();
        order = Mockito.mock(Order.class); // Mock Order object

        payment1 = new Payment(order, "VoucherCode", Map.of("voucherCode", "ESHOP1234ABC5678"));
        payment2 = new Payment(order, "CashOnDelivery", Map.of("address", "123 Main St", "deliveryFee", "15.00"));

        paymentRepository.save(payment1);
        paymentRepository.save(payment2);
    }

    @Test
    public void testFindByIdValid() {
        assertEquals(payment1, paymentRepository.findById(payment1.getId()));
    }

    @Test
    public void testFindByIdInvalid() {
        assertNull(paymentRepository.findById(UUID.randomUUID().toString())); // Use random UUID to test invalid case
    }

    @Test
    public void testFindAll() {
        List<Payment> payments = paymentRepository.findAll();
        assertTrue(payments.contains(payment1) && payments.contains(payment2) && payments.size() == 2);
    }

    @Test
    public void testSaveAndFind() {
        Payment newPayment = new Payment(order, "VoucherCode", Map.of("voucherCode", "ESHOP5678XYZ1234"));
        paymentRepository.save(newPayment);
        assertEquals(newPayment, paymentRepository.findById(newPayment.getId()));
    }

    @Test
    public void testOverwritePayment() {
        Payment duplicatePayment = new Payment(order, "CashOnDelivery", Map.of("address", "456 Elm St", "deliveryFee", "10.00"));
        paymentRepository.save(duplicatePayment);
        assertEquals(duplicatePayment, paymentRepository.findById(duplicatePayment.getId()));
    }
}
