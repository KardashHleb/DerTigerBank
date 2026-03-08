package org.example.service.importer;

import org.example.service.DTO.ReportData;

import java.io.IOException;

public class StorageService {

    public ReportData loadReport(String filePath) throws IOException {
        // Фабрика создаёт нужный импортёр
        BaseReportImporter importer = ImporterFactory.createImporter(filePath);

        // Шаблонный метод импорта
        return importer.importReport(filePath);
    }
}