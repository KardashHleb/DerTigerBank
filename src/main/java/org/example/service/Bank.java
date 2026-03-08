package org.example.service;

import org.example.model.*;
import org.example.service.DTO.ReportData;
import org.example.service.exporter.ReportServiceStrategy;
import org.example.service.importer.StorageService;
import org.example.ui.commands.interfaces.AccountProvider;
import org.example.ui.commands.interfaces.BankService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

/**
 * Класс-фасад (Facade), координирующий работу всех банковских сервисов.
 */
public class Bank implements BankService, AccountProvider {
    private final CustomerService customerService;
    private final AccountService accountService;
    private final TransactionService transactionService;
    private final ReportServiceStrategy reportServiceStrategy;
    private final StorageService storageService;
    private final AnalyticsService analyticsService;

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
        this.analyticsService = new AnalyticsService(this);
    }



    // --- АНАЛИТИКА (делегирование) ---
    public double getTotalIncome(LocalDate start, LocalDate end) {
        return analyticsService.getTotalIncome(start, end);
    }

    public double getTotalExpense(LocalDate start, LocalDate end) {
        return analyticsService.getTotalExpense(start, end);
    }

    public String generateAnalyticsReport(LocalDate start, LocalDate end) {
        return analyticsService.generateReport(start, end);
    }


    // --- УПРАВЛЕНИЕ КЛИЕНТАМИ ---

    public String createCustomer(String name) {
        Customer customer = customerService.createCustomer(name);
        return customer != null ? customer.getFullName() : null;
    }

    public List<Customer> getCustomers() {
        return customerService.getAllCustomers();
    }

    // --- УПРАВЛЕНИЕ СЧЕТАМИ ---



    public String openCreditAccount(int customerId, double limit) {
        Customer customer = customerService.findById(customerId);
        if (customer == null) {
            return null;
        }

        CreditAccount account = accountService.openCreditAccount(customer, limit);

        return String.format("Номер счёта: %s\nВладелец: %s\nКредитный лимит: %.2f",
                account.getAccountNumber(),
                account.getOwner().getFullName(),
                account.getCreditLimit());
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
    @Override
    public String getAccountInfo(String accountNumber) {
        Account account = findAccount(accountNumber);
        if (account == null) return null;

        return String.format("Владелец: %s\nТип: %s\nБаланс: %.2f",
                account.getOwner().getFullName(),
                account.getClass().getSimpleName(),
                account.getBalance());
    }

    @Override
    public String canDeleteAccount(String accountNumber) {
        Account account = findAccount(accountNumber);
        if (account == null) return "Счет не найден.";
        if (account.getBalance() != 0) return "Невозможно удалить счет с ненулевым балансом.";
        return null; // можно удалять
    }
    public String getCustomerAccountsInfo(int customerId) {
        List<Account> accounts = getCustomerAccounts(customerId);
        if (accounts.isEmpty()) return "";

        StringBuilder sb = new StringBuilder();
        for (Account acc : accounts) {
            sb.append(acc.toString()).append("\n");
            if (acc instanceof CreditAccount credit) {
                sb.append(String.format("   Лимит: %.2f, Доступно: %.2f%n",
                        credit.getCreditLimit(), credit.getAvailableCredit()));
            }
        }
        return sb.toString();
    }

    @Override
    public String getAllCustomersInfo() {
        List<Customer> customers = customerService.getAllCustomers();
        if (customers.isEmpty()) return "";

        StringBuilder sb = new StringBuilder();
        for (Customer c : customers) {
            sb.append(String.format("ID: %d, ФИО: %s%n", c.getId(), c.getFullName()));
        }
        return sb.toString();
    }

    @Override
    public String openDebitAccount(int customerId) {
        Customer customer = customerService.findById(customerId);
        if (customer == null) {
            return null;
        }

        DebitAccount account = accountService.openDebitAccount(customer);

        return String.format("Номер счёта: %s\nВладелец: %s",
                account.getAccountNumber(),
                account.getOwner().getFullName());
    }
}