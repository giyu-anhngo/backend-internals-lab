package lab.payments.statement;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.math.BigDecimal;
import java.time.YearMonth;

@Entity
public class Statement {

    @Id
    @GeneratedValue
    private Long id;

    private Long customerId;

    /** Statement month, e.g. "2026-10". */
    private String period;

    private BigDecimal total;

    @Column(length = 100_000)
    private String body;

    protected Statement() {
    }

    public Statement(Long customerId, YearMonth period, BigDecimal total, String body) {
        this.customerId = customerId;
        this.period = period.toString();
        this.total = total;
        this.body = body;
    }

    public Long getId() {
        return id;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getPeriod() {
        return period;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public String getBody() {
        return body;
    }
}
