package lab.payments.payment;

import lab.payments.account.Account;
import lab.payments.account.AccountNotFoundException;
import lab.payments.account.AccountRepository;
import lab.payments.fx.FxRateProvider;
import lab.payments.limit.DailyLimitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
public class PaymentService {

    private final AccountRepository accountRepository;
    private final PaymentRepository paymentRepository;

    @Autowired
    private FxRateProvider fxRateProvider;

    @Autowired
    private DailyLimitService dailyLimitService;

    public PaymentService(AccountRepository accountRepository, PaymentRepository paymentRepository) {
        this.accountRepository = accountRepository;
        this.paymentRepository = paymentRepository;
    }

    @Transactional
    public Payment pay(PaymentRequest request) {
        Account account = accountRepository.findById(request.accountId())
                .orElseThrow(() -> new AccountNotFoundException(request.accountId()));

        dailyLimitService.checkAndRecord(account.getId(), request.amount());

        BigDecimal amountVnd = toVnd(request.amount(), request.currency());
        if (!account.hasAtLeast(amountVnd)) {
            throw new InsufficientFundsException(account.getId());
        }

        account.debit(amountVnd);
        return paymentRepository.save(new Payment(account.getId(), request.billerCode(), amountVnd));
    }

    private BigDecimal toVnd(BigDecimal amount, String currency) {
        if ("VND".equals(currency)) {
            return amount;
        }
        BigDecimal rate = fxRateProvider.findRate(currency).get();
        return amount.multiply(rate).setScale(2);
    }
}
