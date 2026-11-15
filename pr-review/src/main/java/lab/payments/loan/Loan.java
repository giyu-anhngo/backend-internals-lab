package lab.payments.loan;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Getter
@NoArgsConstructor
public class Loan {

    @Id
    @GeneratedValue
    private Long id;

    private Long customerId;

    private BigDecimal principal;

    private int termMonths;

    /** Annual interest rate as a fraction, e.g. 0.12 for 12%. */
    private BigDecimal annualRate;
}
