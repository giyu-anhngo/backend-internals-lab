package lab.payments.kyc;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/kyc-documents")
@RequiredArgsConstructor
public class KycDocumentController {

    private final KycDocumentService service;
    private final KycDocumentRepository repository;

    @PostMapping
    public KycDocument upload(@RequestParam("file") MultipartFile file,
                              @RequestParam String customerName) throws IOException {
        return service.upload(file, customerName);
    }

    @GetMapping("/{id}")
    public ResponseEntity<byte[]> download(@PathVariable Long id) {
        KycDocument doc = repository.findById(id).get();
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(doc.getContentType()))
                .body(service.load(doc));
    }

    @GetMapping
    public List<KycDocument> search(@RequestParam String customerName) {
        return repository.searchByCustomerName(TenantContext.getTenantId(), customerName);
    }
}
