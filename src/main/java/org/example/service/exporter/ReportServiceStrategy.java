package org.example.service.exporter;

import org.example.model.*;
import org.example.service.DTO.ReportData;

import java.io.IOException;
import java.util.Collection;
import java.util.Map;
import java.util.HashMap;

public class ReportServiceStrategy {
    private final Map<String, ReportExporter> exporters = new HashMap<>();

    public ReportServiceStrategy() {
        registerExporter(new org.example.service.exporter.JsonExporter());
        registerExporter(new org.example.service.exporter.YamlExporter());
        registerExporter(new org.example.service.exporter.CsvExporter());
    }

    public void registerExporter(ReportExporter exporter) {
        exporters.put(exporter.getFormat().toLowerCase(), exporter);
    }

    public void exportReport(ReportData data, String format, String filePath) throws IOException {
        ReportExporter exporter = exporters.get(format.toLowerCase());
        if (exporter == null) {
            throw new IllegalArgumentException(
                    String.format("Формат '%s' не поддерживается. Доступные форматы: %s",
                            format, String.join(", ", exporters.keySet()))
            );
        }
        exporter.export(data, filePath);
    }

    public ReportData calculateReportData(int customerCount, Collection<Account> accounts) {
        ReportData data = new ReportData();

        data.setCustomerCount(customerCount);
        data.setTotalAccounts(accounts.size());
        data.setTotalBalance(accounts.stream().mapToDouble(Account::getBalance).sum());

        data.setDebitCount(accounts.stream().filter(acc -> acc instanceof DebitAccount).count());
        data.setCreditCount(accounts.stream().filter(acc -> acc instanceof CreditAccount).count());

        double creditUsed = accounts.stream()
                .filter(acc -> acc instanceof CreditAccount)
                .mapToDouble(acc -> ((CreditAccount) acc).getCreditUsed())
                .sum();
        data.setTotalCreditUsed(creditUsed);

        return data;
    }

    public String formatToText(ReportData data) {
        StringBuilder report = new StringBuilder();
        report.append("=== ОТЧЁТ по ТИГР-БАНКИНГУ ===\n");
        report.append(String.format("Клиентов: %d, Счетов: %d%n",
                data.getCustomerCount(), data.getTotalAccounts()));
        report.append(String.format("Дебетовых: %d, Кредитных: %d%n",
                data.getDebitCount(), data.getCreditCount()));
        report.append(String.format("Общий баланс: %.2f%n",
                data.getTotalBalance()));
        report.append(String.format("Использовано кредитов: %.2f%n",
                data.getTotalCreditUsed()));
        return report.toString();
    }

}