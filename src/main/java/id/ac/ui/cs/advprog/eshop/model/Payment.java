package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;
import java.util.Map;
import java.util.regex.Pattern;

@Getter
public class Payment {
    private String id;
    private String method;
    private String status;
    private Map<String, String> paymentData;


    public Payment(String id, String method, Map<String, String> paymentData) {
        this.id = id;
        this.method = method;
        this.paymentData = paymentData;
        this.status = evaluateStatus();
    }

    private String evaluateStatus() {
        if ("VoucherCode".equals(method)) {
            String code = paymentData.get("voucherCode");
            return (code != null && code.length() == 16 && code.startsWith("ESHOP") &&
                    code.chars().filter(Character::isDigit).count() == 8) ? "SUCCESS" : "REJECTED";
        }
        if ("CashOnDelivery".equals(method)) {
            return (!paymentData.getOrDefault("address", "").trim().isEmpty() &&
                    !paymentData.getOrDefault("deliveryFee", "").trim().isEmpty()) ? "SUCCESS" : "REJECTED";
        }
        return "REJECTED";
    }

    public void setStatus(String status) {
        if (!"SUCCESS".equals(status) && !"REJECTED".equals(status)) {
            throw new IllegalArgumentException("Invalid status: " + status);
        }
        this.status = status;
    }
}
