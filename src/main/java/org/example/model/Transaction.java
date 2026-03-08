package org.example.model;

import java.time.LocalDateTime;

public class Transaction {



    private final String fromAccount;
    private final String toAccount;
    private final double amount;
    private final TransactionType type;
    private final LocalDateTime timestamp;
    private final boolean success;
    private final String message;

    public Transaction(String fromAccount, String toAccount, double amount,
                       TransactionType type, boolean success, String message) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
        this.amount = amount;
        this.type = type;
        this.timestamp = LocalDateTime.now();
        this.success = success;
        this.message = message != null ? message : (success ? "OK" : "Ошибка");
    }

    public double getAmount() {
        return amount;
    }

    public TransactionType getType() {
        return type;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public boolean isSuccess() {
        return success;
    }

    @Override
    public String toString() {
        String operation = "";
        switch (type) {
            case DEPOSIT:
                operation = "Пополнение";
                break;
            case WITHDRAWAL:
                operation = "Снятие";
                break;
            case TRANSFER:
                operation = "Перевод";
                break;
        }
        String status = success ? "УСПЕХ" : "ОШИБКА";
        return String.format("[%s] %s -> %s: %.2f (%s) | %s: %s",
                timestamp,
                fromAccount != null ? fromAccount : "N/A",
                toAccount != null ? toAccount : "N/A",
                amount,
                operation,
                status,
                message);
    }
}