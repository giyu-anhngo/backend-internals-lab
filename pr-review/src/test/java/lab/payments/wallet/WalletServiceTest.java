package lab.payments.wallet;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class WalletServiceTest {

    private final WalletService walletService = new WalletService();

    private final AccountId alice = new AccountId("ACC-001");
    private final AccountId bob = new AccountId("ACC-002");

    @Test
    void transfer_movesMoneyBetweenWallets() {
        walletService.deposit(alice, new BigDecimal("500000"));

        walletService.transfer(alice, bob, new BigDecimal("200000"));

        assertThat(walletService.balanceOf(alice)).isEqualByComparingTo("300000");
        assertThat(walletService.balanceOf(bob)).isEqualByComparingTo("200000");
    }

    @Test
    void transfer_rejectsWhenBalanceTooLow() {
        walletService.deposit(alice, new BigDecimal("100000"));

        assertThatThrownBy(() -> walletService.transfer(alice, bob, new BigDecimal("200000")))
                .isInstanceOf(InsufficientBalanceException.class);
    }

    @Test
    void statement_listsTransactions() {
        String statement = walletService.statement(alice, List.of(
                new WalletTransaction(LocalDate.of(2026, 11, 14), "TOP_UP", new BigDecimal("500000")),
                new WalletTransaction(LocalDate.of(2026, 11, 15), "TRANSFER_OUT", new BigDecimal("200000"))));

        assertThat(statement)
                .startsWith("Statement for ACC-001")
                .contains("2026-11-15 TRANSFER_OUT 200000");
    }
}
