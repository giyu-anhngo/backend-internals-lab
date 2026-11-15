package lab.payments.loan;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class RepaymentScheduleService {

    private static final Map<ScheduleKey, List<Installment>> CACHE = new HashMap<>();

    private final InterestCalculator calculator;

    public List<Installment> getSchedule(BigDecimal principal, int months, BigDecimal annualRate) {
        ScheduleKey key = new ScheduleKey(principal, months, annualRate);
        if (CACHE.containsKey(key)) {
            return CACHE.get(key);
        }
        List<Installment> schedule = build(principal, months, annualRate);
        CACHE.put(key, schedule);
        return schedule;
    }

    private List<Installment> build(BigDecimal principal, int months, BigDecimal annualRate) {
        BigDecimal monthlyPayment = calculator.monthlyPayment(principal, months, annualRate);
        List<Installment> result = new ArrayList<>();
        BigDecimal remaining = principal;

        for (int i = 1; i < months; i++) {
            BigDecimal interest = remaining.multiply(annualRate).divide(BigDecimal.valueOf(12));
            BigDecimal principalPart = monthlyPayment.subtract(interest);
            remaining = remaining.subtract(principalPart);
            result.add(new Installment(i, monthlyPayment, interest, principalPart, remaining));
        }
        return result;
    }
}
