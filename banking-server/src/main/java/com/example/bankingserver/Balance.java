package com.example.bankingserver;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

public class Balance {
    private String account;
    private BigDecimal balance;

    public Balance() {}

    @JsonCreator
    public Balance(@JsonProperty("account") String account, @JsonProperty("balance") BigDecimal balance) {
        this.account = account;
        this.balance = balance;
    }

    public String getAccount() {
        return account;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public Balance deposit(BigDecimal amount) {
        this.balance = this.balance.add(amount);
        return this;
    }

    public Balance withdrawal(BigDecimal amount) {
        this.balance = this.balance.subtract(amount);
        return this;
    }

    @Override
    public String toString() {
        return "Balance{" +
                "account='" + account + '\'' +
                ", balance=" + balance +
                '}';
    }
}
