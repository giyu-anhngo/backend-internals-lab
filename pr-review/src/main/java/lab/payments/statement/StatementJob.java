package lab.payments.statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class StatementJob {

    @Autowired
    private StatementService statementService;

    @Scheduled(cron = "0 0 1 1 * *")
    public void run() {
        statementService.generateAll();
    }
}
