package lab.payments.disbursement;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * Placeholder until the SMS gateway integration (LOAN-231) is ready.
 */
@Slf4j
@Component
public class LoggingSmsClient implements SmsClient {

    @Override
    public void send(String phoneNumber, String message) {
        log.info("SMS queued ({} chars)", message.length());
    }
}
