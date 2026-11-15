package lab.payments.loan;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class Installment {
    private int number;
    private BigDecimal payment;
    private BigDecimal interest;
    private BigDecimal principal;
    private BigDecimal remainingBalance;
}
