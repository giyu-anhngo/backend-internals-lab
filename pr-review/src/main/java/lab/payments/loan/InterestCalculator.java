package lab.payments.loan;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class InterestCalculator {

    /** annualRate as a fraction, e.g. 0.12 for 12% */
    public BigDecimal monthlyPayment(BigDecimal principal, int months, BigDecimal annualRate) {
        double r = annualRate.doubleValue() / 12;
        double payment = principal.doubleValue() * r / (1 - Math.pow(1 + r, -months));
        return new BigDecimal(payment);
    }
}
