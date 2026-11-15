package lab.payments.transfer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/transfers")
public class TransferController {

    @Autowired
    private TransferService transferService;

    @PostMapping
    public ResponseEntity<BankAccount> transfer(@RequestParam Long fromId,
                                                @RequestParam Long toId,
                                                @RequestParam double amount) {
        try {
            BankAccount from = transferService.transfer(fromId, toId, amount);
            return ResponseEntity.ok(from);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.ok(null);
        }
    }
}
