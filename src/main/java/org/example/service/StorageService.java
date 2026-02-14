
package org.example.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import java.io.*;

public class StorageService {
    private final ObjectMapper jsonMapper;
    private final ObjectMapper yamlMapper;

    public StorageService() {
        // JSON mapper
        this.jsonMapper = new ObjectMapper();
        this.jsonMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.jsonMapper.enable(SerializationFeature.INDENT_OUTPUT);

        // YAML mapper
        this.yamlMapper = new ObjectMapper(new YAMLFactory());
        this.yamlMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    // --- ЗАГРУЗКА ОТЧЕТА (цифр) ---

    public ReportData loadReport(String filePath) throws IOException {
        String extension = getFileExtension(filePath);
        File file = new File(filePath);

        if (!file.exists()) {
            throw new IOException("Файл не найден: " + filePath);
        }

        return switch (extension.toLowerCase()) {
            case "json" -> jsonMapper.readValue(file, ReportData.class);
            case "yaml", "yml" -> yamlMapper.readValue(file, ReportData.class);
            case "csv" -> loadReportFromCsv(file);
            default -> throw new IOException("Неподдерживаемый формат файла: " + extension);
        };
    }

    private ReportData loadReportFromCsv(File file) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            boolean isFirstLine = true;

            while ((line = reader.readLine()) != null) {
                if (isFirstLine) {
                    isFirstLine = false;
                    continue;
                }

                if (line.trim().isEmpty()) continue;

                String[] parts = line.split(",");
                if (parts.length >= 4) {
                    try {
                        int totalCustomers = Integer.parseInt(parts[0].trim());
                        int totalAccounts = Integer.parseInt(parts[1].trim());
                        double totalBalance = Double.parseDouble(parts[2].trim());
                        double averageBalance = Double.parseDouble(parts[3].trim());

                        return new ReportData(totalCustomers, totalAccounts, totalBalance, averageBalance);
                    } catch (NumberFormatException e) {
                        throw new IOException("Ошибка парсинга CSV: " + e.getMessage());
                    }
                }
            }
            throw new IOException("CSV файл пуст или имеет неверный формат");
        }
    }

    private String getFileExtension(String filePath) {
        int lastDotIndex = filePath.lastIndexOf('.');
        if (lastDotIndex == -1 || lastDotIndex == filePath.length() - 1) {
            return "";
        }
        return filePath.substring(lastDotIndex + 1);
    }
}