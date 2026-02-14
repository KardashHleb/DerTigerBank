package org.example.service;

import org.example.model.Transaction;
import org.example.model.Account;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class AnalyticsService {
    private final Bank bank;
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public AnalyticsService(Bank bank) {
        this.bank = bank;
    }

    private List<Transaction> getAllTransactions() {
        List<Transaction> allTransactions = new ArrayList<>();
        for (Account account : bank.getAllAccounts()) {
            allTransactions.addAll(account.getTransactions());
        }
        return allTransactions;
    }

    private List<Transaction> filterByPeriod(List<Transaction> transactions, LocalDate start, LocalDate end) {
        return transactions.stream()
                .filter(t -> {
                    LocalDate date = t.getTimestamp().toLocalDate();
                    return !date.isBefore(start) && !date.isAfter(end);
                })
                .collect(Collectors.toList());
    }

    public double getTotalIncome(LocalDate start, LocalDate end) {
        List<Transaction> transactions = filterByPeriod(getAllTransactions(), start, end);

        return transactions.stream()
                .filter(t -> t.getType() == Transaction.TransactionType.DEPOSIT)
                .filter(Transaction::isSuccess)
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public double getTotalExpense(LocalDate start, LocalDate end) {
        List<Transaction> transactions = filterByPeriod(getAllTransactions(), start, end);

        return transactions.stream()
                .filter(t -> t.getType() == Transaction.TransactionType.WITHDRAWAL)
                .filter(Transaction::isSuccess)
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    public String generateReport(LocalDate start, LocalDate end) {
        StringBuilder report = new StringBuilder();

        report.append("\n══════════════════════════════════════════════\n");
        report.append("         ФИНАНСОВЫЙ ОТЧЕТ\n");
        report.append(String.format("Период: %s - %s\n",
                start.format(dateFormatter), end.format(dateFormatter)));
        report.append("══════════════════════════════════════════════\n\n");

        double income = getTotalIncome(start, end);
        double expense = getTotalExpense(start, end);
        double balance = income - expense;

        report.append("📊 ОСНОВНЫЕ ПОКАЗАТЕЛИ:\n");
        report.append(String.format("   Доходы:  %,.2f руб.\n", income));
        report.append(String.format("   Расходы: %,.2f руб.\n", expense));
        report.append(String.format("   Баланс:  %,.2f руб. ", balance));

        if (balance > 0) {
            report.append("✅\n");
        } else if (balance < 0) {
            report.append("⚠️\n");
        } else {
            report.append("⚖️\n");
        }

        long incomeCount = filterByPeriod(getAllTransactions(), start, end).stream()
                .filter(t -> t.getType() == Transaction.TransactionType.DEPOSIT)
                .filter(Transaction::isSuccess)
                .count();

        long expenseCount = filterByPeriod(getAllTransactions(), start, end).stream()
                .filter(t -> t.getType() == Transaction.TransactionType.WITHDRAWAL)
                .filter(Transaction::isSuccess)
                .count();

        report.append("\n📋 КОЛИЧЕСТВО ОПЕРАЦИЙ:\n");
        report.append(String.format("   Доходов:  %d\n", incomeCount));
        report.append(String.format("   Расходов: %d\n", expenseCount));

        if (incomeCount > 0) {
            report.append(String.format("   Средний доход: %,.2f руб.\n", income / incomeCount));
        }
        if (expenseCount > 0) {
            report.append(String.format("   Средний расход: %,.2f руб.\n", expense / expenseCount));
        }

        report.append("\n══════════════════════════════════════════════\n");

        return report.toString();
    }
}