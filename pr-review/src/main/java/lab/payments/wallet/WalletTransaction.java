package lab.payments.wallet;

import java.math.BigDecimal;
import java.time.LocalDate;

public record WalletTransaction(LocalDate date, String type, BigDecimal amount) {
}
