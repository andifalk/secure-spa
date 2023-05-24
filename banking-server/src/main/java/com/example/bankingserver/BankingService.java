package com.example.bankingserver;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Service
public class BankingService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BankingService.class);
    private final Map<String,Balance> accounts = new HashMap<>();

    @PostConstruct
    public void initAccounts() {
        accounts.put("1", new Balance("1", new BigDecimal("200")));
        accounts.put("2", new Balance("2", new BigDecimal("500.50")));
        accounts.put("3", new Balance("3", new BigDecimal("20000")));
        accounts.put("4", new Balance("4", new BigDecimal("9999.99")));
        accounts.put("5", new Balance("5", new BigDecimal("-500")));
        accounts.put("6", new Balance("6", new BigDecimal("100000")));
        accounts.put("7", new Balance("7", new BigDecimal("20")));
        accounts.put("8", new Balance("8", new BigDecimal("30.75")));
        accounts.put("9", new Balance("9", new BigDecimal("5.50")));
        accounts.put("10", new Balance("10", new BigDecimal("200")));
    }

    public void transfer(String accountFrom, String accountTo, BigDecimal amount) {
        Balance from = accounts.get(accountFrom);
        Balance to = accounts.get(accountTo);
        if (from != null && to != null) {
            LOGGER.info("Transfer {} from {} to {}", amount, accountFrom, accountTo);
            from = from.withdrawal(amount);
            to = to.deposit(amount);
            LOGGER.info("Transfer complete. Balance of debitor {}, Balance of creditor {}", from, to);
        } else {
            throw new IllegalArgumentException("Either debitor or creditor account is not existing!");
        }
    }

    public Balance getBalance(String account) {
        return accounts.get(account);
    }
}
