package lab.payments.statement;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Placeholder until the mail gateway integration (PAY-191) is ready.
 */
@Component
public class LoggingEmailClient implements EmailClient {

    private static final Logger log = LoggerFactory.getLogger(LoggingEmailClient.class);

    @Override
    public void send(String to, String subject, String body) {
        log.info("Email '{}' queued ({} chars)", subject, body.length());
    }
}
