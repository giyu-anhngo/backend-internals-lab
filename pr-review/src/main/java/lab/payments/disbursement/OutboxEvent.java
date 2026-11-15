package lab.payments.disbursement;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class OutboxEvent {

    @Id
    @GeneratedValue
    private Long id;

    private String type;

    private String payload;

    private String status = "NEW";

    private LocalDateTime createdAt = LocalDateTime.now();

    public OutboxEvent(String type, String payload) {
        this.type = type;
        this.payload = payload;
    }
}
