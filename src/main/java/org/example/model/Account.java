package org.example.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public abstract class Account {
    private static final AtomicLong accountCounter = new AtomicLong(1000000000L);

    protected final String accountNumber;
    protected double balance;
    protected final Customer owner;
    protected final List<Transaction> transactions;

    public Account(Customer owner) {
        this.accountNumber = generateAccountNumber();
        this.balance = 0.0;
        this.owner = owner;
        this.transactions = new ArrayList<>();
    }

    private String generateAccountNumber() {
        return "ACC" + accountCounter.getAndIncrement();
    }

    public boolean deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Ошибка: Сумма пополнения должна быть больше 0");
            transactions.add(TransactionFactory.createFailedDeposit(
                    accountNumber, amount, "Сумма пополнения должна быть больше 0"));
            return false;
        }

        this.balance += amount;
        transactions.add(TransactionFactory.createSuccessDeposit(accountNumber, amount));
        System.out.printf("Счёт %s пополнен на %.2f. Новый баланс: %.2f%n",
                accountNumber, amount, balance);
        return true;
    }

    public abstract boolean withdraw(double amount);

    public boolean transfer(Account toAccount, double amount) {
        if (amount <= 0) {
            System.out.println("Ошибка: Сумма перевода должна быть больше 0");
            return false;
        }

        if (toAccount == null) {
            System.out.println("Ошибка: Счёт получателя не существует");
            return false;
        }

        if (this == toAccount) {
            System.out.println("Ошибка: Нельзя перевести деньги на тот же счёт");
            return false;
        }

        if (this.withdraw(amount)) {
            boolean depositResult = toAccount.deposit(amount);

            transactions.add(TransactionFactory.createTransfer(
                    this.accountNumber, toAccount.getAccountNumber(), amount, true, "OK"));
            toAccount.addTransaction(TransactionFactory.createTransfer(
                    this.accountNumber, toAccount.getAccountNumber(), amount, depositResult,
                    depositResult ? "OK" : "Deposit failed"));

            System.out.printf("Перевод %.2f со счёта %s на счёт %s выполнен успешно%n",
                    amount, this.accountNumber, toAccount.getAccountNumber());
            return true;
        } else {
            System.out.println("Ошибка: Перевод не выполнен из-за недостатка средств");
            return false;
        }
    }

   protected void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public double getBalance() {
        return balance;
    }

    public Customer getOwner() {
        return owner;
    }

    public List<Transaction> getTransactions() {
        return new ArrayList<>(transactions);
    }

    @Override
    public String toString() {
        return String.format("Счёт: %s, Баланс: %.2f, Владелец: %s",
                accountNumber, balance, owner.getFullName());
    }
}