package lab.payments.wallet;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class WalletService {

    private final Map<AccountId, BigDecimal> balances = new HashMap<>();
    private static final List<String> TRANSFER_LOG = new ArrayList<>();

    public void deposit(AccountId id, BigDecimal amount) {
        balances.merge(id, amount, BigDecimal::add);
    }

    public BigDecimal balanceOf(AccountId id) {
        return balances.getOrDefault(id, BigDecimal.ZERO);
    }

    public void transfer(AccountId from, AccountId to, BigDecimal amount) {
        if (amount == null || amount.signum() <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        BigDecimal fromBalance = balances.get(from);
        if (fromBalance == null || fromBalance.compareTo(amount) < 0) {
            throw new InsufficientBalanceException(from);
        }
        balances.put(from, fromBalance.subtract(amount));
        balances.put(to, balances.getOrDefault(to, BigDecimal.ZERO).add(amount));
        TRANSFER_LOG.add(from + " -> " + to + " : " + amount);
    }

    public String statement(AccountId id, List<WalletTransaction> txs) {
        String result = "Statement for " + id + "\n";
        for (WalletTransaction tx : txs) {
            result += tx.date() + " " + tx.type() + " " + tx.amount() + "\n";
        }
        return result;
    }
}
