package lab.payments.kyc;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class KycDocumentService {

    private final KycDocumentRepository repository;
    private final StorageClient storageClient;
    private final ThumbnailService thumbnailService;

    @Transactional
    public KycDocument upload(MultipartFile file, String customerName) throws IOException {
        String tenantId = TenantContext.getTenantId();
        String path = tenantId + "/" + file.getOriginalFilename();

        storageClient.put(path, file.getBytes());

        KycDocument doc = new KycDocument();
        doc.setTenantId(tenantId);
        doc.setCustomerName(customerName);
        doc.setStoragePath(path);
        doc.setContentType(file.getContentType());
        repository.save(doc);

        thumbnailService.generate(doc.getId());
        return doc;
    }

    public byte[] load(KycDocument doc) {
        return storageClient.get(doc.getStoragePath());
    }
}
