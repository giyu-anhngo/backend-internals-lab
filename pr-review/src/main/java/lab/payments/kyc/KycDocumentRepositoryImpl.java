package lab.payments.kyc;

import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class KycDocumentRepositoryImpl implements KycDocumentSearch {

    private final EntityManager entityManager;

    @Override
    @SuppressWarnings("unchecked")
    public List<KycDocument> searchByCustomerName(String tenantId, String customerName) {
        String sql = "SELECT * FROM kyc_document WHERE tenant_id = '" + tenantId
                + "' AND customer_name LIKE '%" + customerName + "%'";
        return entityManager.createNativeQuery(sql, KycDocument.class).getResultList();
    }
}
