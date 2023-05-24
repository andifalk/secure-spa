package com.example.bankingserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
public class BankingRestController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BankingRestController.class);
    private final BankingService bankingService;

    public BankingRestController(BankingService bankingService) {
        this.bankingService = bankingService;
    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transfer(@RequestBody Transfer transfer) {
        LOGGER.info("****** Transfer: {} *******", transfer);
        try {
            bankingService.transfer(transfer.getFrom(), transfer.getTo(), transfer.getAmount());
            return ResponseEntity.ok().build();
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/balance/{account}")
    public ResponseEntity<Balance> getAccountBalance(@PathVariable String account) {
        Balance balance = bankingService.getBalance(account);
        if (balance != null) {
            LOGGER.info("******** Get balance for account {}: {} *********", account, balance.getBalance());
            return ResponseEntity.ok(balance);
        } else {
            LOGGER.warn("No account found for {}", account);
            return ResponseEntity.notFound().build();
        }
    }

}
