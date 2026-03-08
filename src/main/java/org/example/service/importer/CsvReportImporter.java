package org.example.service.importer;

import org.example.service.DTO.ReportData;
import java.io.*;
import java.nio.file.Files;
import java.util.List;

public class CsvReportImporter extends BaseReportImporter {

    @Override
    protected ReportData parseFile(File file) throws IOException {
        List<String> lines = Files.readAllLines(file.toPath());

        if (lines.isEmpty()) {
            throw new IOException("CSV файл пуст");
        }

        // Проверяем заголовок (опционально)
        String header = lines.get(0).trim();
        String[] headerColumns = header.split(",");

        // Данные могут быть со 2-й строки или с 1-й, если нет заголовка
        int dataRowIndex = (lines.size() > 1 && isLikelyHeader(header)) ? 1 : 0;

        if (dataRowIndex >= lines.size()) {
            throw new IOException("Нет строки с данными");
        }

        String dataLine = lines.get(dataRowIndex).trim();
        String[] parts = dataLine.split(",");


        if (parts.length < 6) {
            throw new IOException("Недостаточно полей в CSV. Ожидается 6 полей");
        }

        try {
            int customerCount = Integer.parseInt(parts[0].trim());
            int totalAccounts = Integer.parseInt(parts[1].trim());
            double totalBalance = Double.parseDouble(parts[2].trim());


            // Вычисляем avgBalance для обратной совместимости
            double avgBalance = (totalAccounts > 0) ? totalBalance / totalAccounts : 0;

            // Используем конструктор со всеми полями
             return new ReportData(
                    customerCount,
                    totalAccounts,
                    totalBalance,
                    avgBalance
            );

        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new IOException("Ошибка парсинга CSV: " + e.getMessage());
        }
    }

    // Хелпер для определения, является ли строка заголовком
    private boolean isLikelyHeader(String line) {
        String lower = line.toLowerCase();
        return lower.contains("customer") ||
                lower.contains("count") ||
                lower.contains("balance") ||
                lower.contains("total");
    }

    @Override
    protected void postProcess(ReportData data) {
        System.out.println("📊 CSV импорт: проверка целостности данных...");
        // Дополнительная валидация
        if (data.getTotalAccounts() < 0) {
            System.out.println("⚠️ Предупреждение: отрицательное количество счетов");
        }
    }
}