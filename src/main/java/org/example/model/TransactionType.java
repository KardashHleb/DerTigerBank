package org.example.model;

public enum TransactionType {
    DEPOSIT("Пополнение счета"),
    WITHDRAWAL("Снятие со счета"),
    TRANSFER("Перевод между счетами");

    private final String description;

    TransactionType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}