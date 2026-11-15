package lab.payments.disbursement;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class OutboxPublisher {

    private final OutboxRepository outboxRepository;
    private final RabbitTemplate rabbitTemplate;

    @Scheduled(fixedRate = 1000)
    public void publish() {
        List<OutboxEvent> events = outboxRepository.findByStatus("NEW");
        for (OutboxEvent event : events) {
            try {
                rabbitTemplate.convertAndSend("loan-events", event.getType(), event.getPayload());
                event.setStatus("SENT");
            } catch (Exception e) {
                // will retry on next run
            }
        }
        outboxRepository.saveAll(events);
    }
}
