package org.example.service.importer;

import java.io.IOException;

public class ImporterFactory {

    public static BaseReportImporter createImporter(String filePath) throws IOException {
        String extension = getFileExtension(filePath);

        return switch (extension.toLowerCase()) {
            case "json" -> new JsonReportImporter();
            case "yaml", "yml" -> new YamlReportImporter();
            case "csv" -> new CsvReportImporter();
            default -> throw new IOException("Неподдерживаемый формат: " + extension);
        };
    }

    private static String getFileExtension(String filePath) {
        int lastDot = filePath.lastIndexOf('.');
        return (lastDot == -1) ? "" : filePath.substring(lastDot + 1);
    }
}