package lab.payments.wallet;

public class InsufficientBalanceException extends RuntimeException {

    public InsufficientBalanceException(AccountId accountId) {
        super("Insufficient balance in wallet " + accountId);
    }
}
