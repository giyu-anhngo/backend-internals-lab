package lab.payments.loan;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class RepaymentScheduleServiceTest {

    private final RepaymentScheduleService service =
            new RepaymentScheduleService(new InterestCalculator());

    @Test
    void returnsSchedule() {
        List<Installment> schedule =
                service.getSchedule(new BigDecimal("12000"), 12, new BigDecimal("0.12"));
        assertNotNull(schedule);
    }
}
