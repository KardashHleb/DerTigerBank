package org.example.service;

import org.example.model.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionService {
    private final List<Transaction> allTransactions = new ArrayList<>();

    public boolean deposit(Account account, double amount) {
        if (account == null) return false;
        boolean success = account.deposit(amount);
        if (success) recordTransaction(account);
        return success;
    }

    public boolean withdraw(Account account, double amount) {
        if (account == null) return false;
        boolean success = account.withdraw(amount);
        if (success) recordTransaction(account);
        return success;
    }

    public boolean transfer(Account from, Account to, double amount) {
        if (from == null || to == null) return false;
        boolean success = from.transfer(to, amount);
        if (success) recordTransaction(from);
        return success;
    }

    private void recordTransaction(Account account) {
        List<Transaction> txs = account.getTransactions();
        if (txs == null || txs.isEmpty()) return;
        allTransactions.add(txs.get(txs.size() - 1));
    }
}