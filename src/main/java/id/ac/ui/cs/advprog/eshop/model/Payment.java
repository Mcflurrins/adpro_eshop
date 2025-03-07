package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.UUID;

@Getter
public class Payment {
    private final String id;  // Automatically generated
    private final Order order;
    private final String method;
    private String status;
    private final Map<String, String> paymentData;

    public Payment(Order order, String method, Map<String, String> paymentData) {
        this.id = UUID.randomUUID().toString(); // Generate a unique ID
        this.order = order;
        this.method = method;
        this.paymentData = paymentData;
        this.status = determineStatus();
    }

    private String determineStatus() {
        if ("VoucherCode".equals(method)) {
            return validateVoucherCode(paymentData.get("voucherCode")) ? "SUCCESS" : "REJECTED";
        }
        if ("CashOnDelivery".equals(method)) {
            return validateCashOnDelivery(paymentData) ? "SUCCESS" : "REJECTED";
        }
        return "REJECTED";
    }

    private boolean validateVoucherCode(String code) {
        return code != null && code.length() == 16 && code.startsWith("ESHOP") &&
                Pattern.compile("\\d").matcher(code).results().count() == 8;
    }

    private boolean validateCashOnDelivery(Map<String, String> data) {
        return data.get("address") != null && !data.get("address").isEmpty() &&
                data.get("deliveryFee") != null && !data.get("deliveryFee").isEmpty();
    }

    public void setStatus(String status) {
        if (!"SUCCESS".equals(status) && !"REJECTED".equals(status)) {
            throw new IllegalArgumentException("Invalid status: " + status);
        }
        this.status = status;
    }
}
