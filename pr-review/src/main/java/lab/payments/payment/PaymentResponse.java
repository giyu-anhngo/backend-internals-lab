package lab.payments.payment;

import java.math.BigDecimal;
import java.time.Instant;

public record PaymentResponse(Long paymentId, Long accountId, String billerCode, BigDecimal amount,
                              PaymentStatus status, Instant createdAt) {

    static PaymentResponse from(Payment payment) {
        return new PaymentResponse(payment.getId(), payment.getAccountId(), payment.getBillerCode(),
                payment.getAmount(), payment.getStatus(), payment.getCreatedAt());
    }
}
