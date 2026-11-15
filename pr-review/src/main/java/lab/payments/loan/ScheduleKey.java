package lab.payments.loan;

import java.math.BigDecimal;

public class ScheduleKey {

    private final BigDecimal principal;
    private final int months;
    private final BigDecimal annualRate;

    public ScheduleKey(BigDecimal principal, int months, BigDecimal annualRate) {
        this.principal = principal;
        this.months = months;
        this.annualRate = annualRate;
    }
}
