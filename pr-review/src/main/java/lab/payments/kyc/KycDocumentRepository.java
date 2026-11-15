package lab.payments.kyc;

import org.springframework.data.jpa.repository.JpaRepository;

public interface KycDocumentRepository extends JpaRepository<KycDocument, Long>, KycDocumentSearch {
}
