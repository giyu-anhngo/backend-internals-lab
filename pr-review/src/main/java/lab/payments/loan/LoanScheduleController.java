package lab.payments.loan;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LoanScheduleController {

    private final LoanRepository loanRepository;
    private final RepaymentScheduleService scheduleService;

    @GetMapping("/api/loans/{loanId}/schedule")
    public List<Installment> getSchedule(@PathVariable Long loanId) {
        Loan loan = loanRepository.findById(loanId).orElseThrow();
        return scheduleService.getSchedule(loan.getPrincipal(), loan.getTermMonths(), loan.getAnnualRate());
    }
}
