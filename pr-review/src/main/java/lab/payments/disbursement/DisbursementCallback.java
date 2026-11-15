package lab.payments.disbursement;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class DisbursementCallback {
    private String loanReference;
    private String status;          // "SUCCESS" or "FAILED"
    private BigDecimal amount;
    private String beneficiaryAccountNumber;
    private String beneficiaryName;
}
