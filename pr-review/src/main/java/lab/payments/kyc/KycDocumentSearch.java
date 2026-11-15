package lab.payments.kyc;

import java.util.List;

public interface KycDocumentSearch {

    List<KycDocument> searchByCustomerName(String tenantId, String customerName);
}
