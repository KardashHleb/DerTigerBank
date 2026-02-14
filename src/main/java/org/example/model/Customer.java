package org.example.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class Customer {
    private static final AtomicInteger idCounter = new AtomicInteger(1);

    private final int id;
    private final String fullName;
    private final List<Account> accounts;

    public Customer(String fullName) {
        this.id = idCounter.getAndIncrement();
        this.fullName = fullName;
        this.accounts = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public List<Account> getAccounts() {
        return Collections.unmodifiableList(accounts);
    }

    public void addAccount(Account account) {
        if (account != null) {
            accounts.add(account);
        }
    }

    public Account findAccount(String accountNumber) {
        return accounts.stream()
                .filter(a -> a.getAccountNumber().equals(accountNumber))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String toString() {
        return String.format("Клиент: ID=%d, ФИО='%s', Счетов=%d",
                id, fullName, accounts.size());
    }
}