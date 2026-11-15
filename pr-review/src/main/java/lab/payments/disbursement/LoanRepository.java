package lab.payments.disbursement;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<Loan, Long> {

    Loan findByReference(String reference);
}
