package org.example.ui.commands.interfaces;

public interface BankOperations {
    boolean deleteAccount(String accountNumber);
    String createCustomer(String name);
    boolean transfer(String fromAccount, String toAccount, double amount);
    boolean deposit(String accountNumber, double amount);
    boolean withdraw(String accountNumber, double amount);
    String getAllCustomersInfo();
    String openDebitAccount(int customerId);
    String openCreditAccount(int customerId, double limit);
}
