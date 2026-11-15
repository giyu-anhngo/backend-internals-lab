package lab.payments.disbursement;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequiredArgsConstructor
public class DisbursementCallbackController {

    private final SignatureVerifier signatureVerifier;
    private final DisbursementService disbursementService;

    @PostMapping("/callbacks/disbursement")
    public ResponseEntity<String> onCallback(@RequestHeader("X-Signature") String signature,
                                             @RequestBody DisbursementCallback body) {
        log.info("Received disbursement callback: {}", body);

        disbursementService.handle(body);

        if (!signatureVerifier.verify(body.toString(), signature)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok("OK");
    }
}
