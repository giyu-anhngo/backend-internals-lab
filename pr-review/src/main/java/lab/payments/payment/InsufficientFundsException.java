package lab.payments.payment;

public class InsufficientFundsException extends RuntimeException {

    public InsufficientFundsException(Long accountId) {
        super("Insufficient funds on account " + accountId);
    }
}
