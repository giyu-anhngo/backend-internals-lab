package lab.payments.kyc;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ThumbnailService {

    private final KycDocumentRepository repository;
    private final StorageClient storageClient;

    @Async
    public void generate(Long documentId) {
        String tenantId = TenantContext.getTenantId();
        KycDocument doc = repository.findById(documentId).orElseThrow();
        byte[] original = storageClient.get(doc.getStoragePath());
        byte[] thumbnail = ImageUtils.resize(original, 200, 200);
        storageClient.put(tenantId + "/thumbnails/" + documentId + ".png", thumbnail);
    }
}
