package lab.payments.limit;

import java.math.BigDecimal;

public class DailyLimitExceededException extends RuntimeException {

    public DailyLimitExceededException(Long accountId, BigDecimal dailyLimit) {
        super("Daily limit of " + dailyLimit + " VND exceeded for account " + accountId);
    }
}
