package lab.payments.disbursement;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Loan {

    @Id
    @GeneratedValue
    private Long id;

    private String reference;

    private BigDecimal approvedAmount;

    private String customerPhone;

    @Enumerated(EnumType.STRING)
    private LoanStatus status;

    private LocalDateTime disbursedAt;
}
