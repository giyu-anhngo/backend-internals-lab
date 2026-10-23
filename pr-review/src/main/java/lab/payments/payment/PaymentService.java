package lab.payments.payment;

import lab.payments.account.Account;
import lab.payments.account.AccountNotFoundException;
import lab.payments.account.AccountRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentService {

    private final AccountRepository accountRepository;
    private final PaymentRepository paymentRepository;

    public PaymentService(AccountRepository accountRepository, PaymentRepository paymentRepository) {
        this.accountRepository = accountRepository;
        this.paymentRepository = paymentRepository;
    }

    @Transactional
    public Payment pay(PaymentRequest request) {
        Account account = accountRepository.findById(request.accountId())
                .orElseThrow(() -> new AccountNotFoundException(request.accountId()));

        if (!account.hasAtLeast(request.amount())) {
            throw new InsufficientFundsException(account.getId());
        }

        account.debit(request.amount());
        return paymentRepository.save(new Payment(account.getId(), request.billerCode(), request.amount()));
    }
}
