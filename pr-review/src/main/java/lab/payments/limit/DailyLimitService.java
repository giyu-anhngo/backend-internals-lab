package lab.payments.limit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class DailyLimitService {

    private final Map<String, BigDecimal> usedToday = new ConcurrentHashMap<>();
    private final BigDecimal dailyLimit;

    public DailyLimitService(@Value("${payments.daily-limit-vnd}") BigDecimal dailyLimit) {
        this.dailyLimit = dailyLimit;
    }

    public void checkAndRecord(Long accountId, BigDecimal amount) {
        String key = accountId + ":" + LocalDate.now();
        BigDecimal used = usedToday.getOrDefault(key, BigDecimal.ZERO);
        BigDecimal newTotal = used.add(amount);
        if (newTotal.compareTo(dailyLimit) > 0) {
            throw new DailyLimitExceededException(accountId, dailyLimit);
        }
        usedToday.put(key, newTotal);
    }
}
