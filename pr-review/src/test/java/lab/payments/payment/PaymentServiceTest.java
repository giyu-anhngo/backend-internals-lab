package lab.payments.payment;

import lab.payments.account.Account;
import lab.payments.account.AccountNotFoundException;
import lab.payments.account.AccountRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class PaymentServiceTest {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private AccountRepository accountRepository;

    @Test
    void pay_debitsAccountAndRecordsPayment() {
        Account account = accountRepository.save(new Account("Test", new BigDecimal("1000000.00")));

        Payment payment = paymentService.pay(
                new PaymentRequest(account.getId(), "EVN-HCM", new BigDecimal("250000.00")));

        assertThat(payment.getStatus()).isEqualTo(PaymentStatus.COMPLETED);
        assertThat(accountRepository.findById(account.getId()).orElseThrow().getBalance())
                .isEqualByComparingTo("750000.00");
    }

    @Test
    void pay_rejectsWhenBalanceTooLow() {
        Account account = accountRepository.save(new Account("Test", new BigDecimal("100.00")));

        assertThatThrownBy(() -> paymentService.pay(
                new PaymentRequest(account.getId(), "EVN-HCM", new BigDecimal("250000.00"))))
                .isInstanceOf(InsufficientFundsException.class);
    }

    @Test
    void pay_rejectsUnknownAccount() {
        assertThatThrownBy(() -> paymentService.pay(
                new PaymentRequest(999_999L, "EVN-HCM", new BigDecimal("1.00"))))
                .isInstanceOf(AccountNotFoundException.class);
    }
}
