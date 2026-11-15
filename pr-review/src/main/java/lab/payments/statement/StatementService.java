package lab.payments.statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.YearMonth;
import java.util.List;

@Service
public class StatementService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private StatementRepository statementRepository;

    @Autowired
    private EmailClient emailClient;

    @Transactional
    public void generateAll() {
        List<Customer> customers = customerRepository.findAll();
        YearMonth lastMonth = YearMonth.from(LocalDate.now()).minusMonths(1);

        for (Customer customer : customers) {
            String body = "";
            BigDecimal total = BigDecimal.ZERO;

            for (Transaction tx : customer.getTransactions()) {
                if (YearMonth.from(tx.getCreatedAt()).equals(lastMonth)) {
                    body += StatementFormatter.formatLine(tx) + "\n";
                    total.add(tx.getAmount());
                }
            }

            statementRepository.save(new Statement(customer.getId(), lastMonth, total, body));
            emailClient.send(customer.getEmail(), "Your monthly statement", body);
        }
    }
}
