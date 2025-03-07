package id.ac.ui.cs.advprog.eshop.service;

import static org.junit.jupiter.api.Assertions.*;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Map;

public class PaymentServiceImplTest {
    private PaymentService paymentService;
    private PaymentRepository paymentRepository;
    private Order order;

    @BeforeEach
    public void setUp() {
        paymentRepository = new PaymentRepository();
        paymentService = new PaymentServiceImpl(paymentRepository); // Only pass PaymentRepository

        order = Mockito.mock(Order.class);
    }

    @Test
    public void testAddPayment_ShouldCreateAndSavePayment() {
        Payment payment = paymentService.addPayment(order, "VoucherCode", Map.of("voucherCode", "ESHOP1234ABC5678"));

        assertNotNull(payment);
        assertNotNull(payment.getId()); // Ensure UUID is generated
        assertEquals("SUCCESS", payment.getStatus());
        assertEquals(order, payment.getOrder()); // Ensure correct order is linked
        assertEquals(payment, paymentRepository.findById(payment.getId()));
    }

    @Test
    public void testSetStatusSuccess_ShouldUpdatePaymentAndOrderStatus() {
        Payment payment = new Payment(order, "VoucherCode", Map.of("voucherCode", "ESHOP1234ABC5678"));
        paymentRepository.save(payment);

        Mockito.doNothing().when(order).setStatus(Mockito.anyString());

        paymentService.setStatus(payment, "SUCCESS");

        assertEquals("SUCCESS", payment.getStatus());
        Mockito.verify(order).setStatus("SUCCESS"); // Ensure order status is updated
    }

    @Test
    public void testSetStatusRejected_ShouldUpdatePaymentAndOrderStatus() {
        Payment payment = new Payment(order, "CashOnDelivery", Map.of("address", "123 Main St", "deliveryFee", "15.00"));
        paymentRepository.save(payment);

        Mockito.doNothing().when(order).setStatus(Mockito.anyString());

        paymentService.setStatus(payment, "REJECTED");

        assertEquals("REJECTED", payment.getStatus());
        Mockito.verify(order).setStatus("FAILED"); // Ensure order status is updated
    }

    @Test
    public void testGetPayment_ShouldReturnSpecificPayment() {
        Payment payment = new Payment(order, "VoucherCode", Map.of("voucherCode", "ESHOP1234ABC5678"));
        paymentRepository.save(payment);

        Payment retrievedPayment = paymentService.getPayment(payment.getId());
        assertEquals(payment, retrievedPayment);
    }

    @Test
    public void testGetAllPayments_ShouldReturnAllPayments() {
        Payment payment1 = new Payment(order, "VoucherCode", Map.of("voucherCode", "ESHOP1234ABC5678"));
        Payment payment2 = new Payment(order, "CashOnDelivery", Map.of("address", "123 Main St", "deliveryFee", "15.00"));
        paymentRepository.save(payment1);
        paymentRepository.save(payment2);

        List<Payment> payments = paymentService.getAllPayments();
        assertTrue(payments.contains(payment1) && payments.contains(payment2) && payments.size() == 2);
    }
}
