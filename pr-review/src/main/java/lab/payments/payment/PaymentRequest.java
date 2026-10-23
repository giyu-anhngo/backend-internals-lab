package lab.payments.payment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

public record PaymentRequest(
        @NotNull Long accountId,
        @NotBlank String billerCode,
        @NotNull @Positive BigDecimal amount,
        String currency) {
}
