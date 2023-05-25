package com.example.bankingserver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@Controller
@RequestMapping("/banking")
public class BankingWebController {
    private static final Logger LOGGER = LoggerFactory.getLogger(BankingWebController.class);
    private final BankingService bankingService;

    public BankingWebController(BankingService bankingService) {
        this.bankingService = bankingService;
    }

    @ModelAttribute("accountbalance")
    public Balance balance() {
        return bankingService.getBalance("1");
    }

    @GetMapping
    public String balancePage() {
        return "banking";
    }

    @GetMapping("/transfer")
    public String bankingTransferForm(Model model) {
        model.addAttribute("transfer", new Transfer("1", "2", BigDecimal.ZERO));
        return "transfer";
    }

    @PostMapping(path = "/performtransfer", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void create(Transfer transfer) {

        LOGGER.info("Banking transfer via POST request '/web': {}", transfer);

        this.bankingService.transfer(transfer.getFrom(), transfer.getTo(), transfer.getAmount());
    }
}
