package org.example.service;

import org.example.model.*;
import org.example.service.exporter.ReportServiceStrategy;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

/**
 * Класс-фасад (Facade), координирующий работу всех банковских сервисов.
 */
public class Bank {
    private final CustomerService customerService;
    private final AccountService accountService;
    private final TransactionService transactionService;
    private final ReportServiceStrategy reportServiceStrategy;
    private final StorageService storageService;

    public Bank(CustomerService customerService,
                AccountService accountService,
                TransactionService transactionService,
                ReportServiceStrategy reportServiceStrategy,
                StorageService storageService) {
        this.customerService = customerService;
        this.accountService = accountService;
        this.transactionService = transactionService;
        this.reportServiceStrategy = reportServiceStrategy;
        this.storageService = storageService;
    }

    // --- УПРАВЛЕНИЕ КЛИЕНТАМИ ---

    public Customer createCustomer(String name) {
        return customerService.createCustomer(name);
    }

    public List<Customer> getCustomers() {
        return customerService.getAllCustomers();
    }

    // --- УПРАВЛЕНИЕ СЧЕТАМИ ---

    public DebitAccount openDebitAccount(int customerId) {
        Customer customer = customerService.findById(customerId);
        if (customer == null) {
            System.out.println("Ошибка: Клиент не найден.");
            return null;
        }
        return accountService.openDebitAccount(customer);
    }

    public CreditAccount openCreditAccount(int customerId, double creditLimit) {
        Customer customer = customerService.findById(customerId);
        if (customer == null) {
            System.out.println("Ошибка: Клиент не найден.");
            return null;
        }
        return accountService.openCreditAccount(customer, creditLimit);
    }

    public List<Account> getCustomerAccounts(int customerId) {
        Customer customer = customerService.findById(customerId);
        return (customer != null) ? customer.getAccounts() : List.of();
    }

    public Collection<Account> getAllAccounts() {
        return accountService.getAllAccounts(customerService);
    }

    public Account findAccount(String accountNumber) {
        return accountService.findByNumber(accountNumber, customerService);
    }

    // --- УДАЛЕНИЕ ---

    public boolean deleteAccount(String accountNumber) {
        return accountService.removeAccount(accountNumber, customerService);
    }


    // --- ОПЕРАЦИИ (ТРАНЗАКЦИИ) ---

    public boolean deposit(String accountNumber, double amount) {
        Account account = accountService.findByNumber(accountNumber, customerService);
        if (account != null) {
            return transactionService.deposit(account, amount);
        }
        System.out.println("Ошибка: Счёт " + accountNumber + " не найден.");
        return false;
    }

    public boolean withdraw(String accountNumber, double amount) {
        Account account = accountService.findByNumber(accountNumber, customerService);
        if (account != null) {
            return transactionService.withdraw(account, amount);
        }
        System.out.println("Ошибка: Счёт " + accountNumber + " не найден.");
        return false;
    }

    public boolean transfer(String from, String to, double amount) {
        Account accFrom = accountService.findByNumber(from, customerService);
        Account accTo = accountService.findByNumber(to, customerService);

        if (accFrom == null) {
            System.out.println("Ошибка: Счет отправителя не найден.");
            return false;
        }
        if (accTo == null) {
            System.out.println("Ошибка: Счет получателя не найден.");
            return false;
        }

        return transactionService.transfer(accFrom, accTo, amount);
    }

    // --- ОТЧЁТНОСТЬ И ЭКСПОРТ ---

    public String generateBankReport() {
        ReportData data = fetchCurrentReportData();
        return reportServiceStrategy.formatToText(data);
    }

    public void exportReport(String format, String filePath) throws IOException {
        ReportData data = fetchCurrentReportData();
        reportServiceStrategy.exportReport(data, format, filePath);  // Исправлено: exportReport вместо saveReport
    }

    public String readReportFromFile(String filePath) {
        try {
            ReportData data = storageService.loadReport(filePath);
            return "--- ПРОСМОТР ОТЧЕТА ---\n" + reportServiceStrategy.formatToText(data);
        } catch (IOException e) {
            return "Ошибка: " + e.getMessage();
        }
    }

    private ReportData fetchCurrentReportData() {
        return reportServiceStrategy.calculateReportData(
                customerService.getAllCustomers().size(),
                accountService.getAllAccounts(customerService)
        );
    }
}