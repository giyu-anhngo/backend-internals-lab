package lab.payments.disbursement;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DisbursementService {

    private final LoanRepository loanRepository;
    private final OutboxRepository outboxRepository;
    private final SmsClient smsClient;

    @Transactional
    public void handle(DisbursementCallback callback) {
        Loan loan = loanRepository.findByReference(callback.getLoanReference());

        if (callback.getStatus().equals("SUCCESS")) {
            loan.setStatus(LoanStatus.DISBURSED);
            loan.setDisbursedAt(LocalDateTime.now());

            smsClient.send(loan.getCustomerPhone(), "Your loan has been disbursed.");

            outboxRepository.save(new OutboxEvent("LOAN_DISBURSED", loan.getId().toString()));
        } else {
            loan.setStatus(LoanStatus.DISBURSEMENT_FAILED);
        }
    }
}
