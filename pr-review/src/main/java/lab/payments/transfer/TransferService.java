package lab.payments.transfer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransferService {

    private static final Logger log = LoggerFactory.getLogger(TransferService.class);

    @Autowired
    private BankAccountRepository accountRepository;

    public BankAccount transfer(Long fromId, Long toId, double amount) {
        BankAccount from = accountRepository.findById(fromId).get();
        BankAccount to = accountRepository.findById(toId).get();

        log.info("Transferring " + amount + " from " + from.getAccountNumber()
                + " to " + to.getAccountNumber());

        if (from.getBalance() >= amount) {
            from.setBalance(from.getBalance() - amount);
            accountRepository.save(from);

            to.setBalance(to.getBalance() + amount);
            accountRepository.save(to);
        }
        return from;
    }
}
