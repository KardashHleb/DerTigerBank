package org.example.service.importer;

import org.example.service.DTO.ReportData;
import java.io.*;

public abstract class BaseReportImporter {

    public final ReportData importReport(String filePath) throws IOException {
        File file = validateFile(filePath);
        ReportData data = parseFile(file);
        postProcess(data);
        logImport(filePath);

        return data;
    }

    protected File validateFile(String filePath) throws IOException {
        File file = new File(filePath);
        if (!file.exists()) {
            throw new IOException("Файл не найден: " + filePath);
        }
        if (!file.canRead()) {
            throw new IOException("Нет прав на чтение файла: " + filePath);
        }
        return file;
    }

    protected abstract ReportData parseFile(File file) throws IOException;

    protected void postProcess(ReportData data) {

    }

    private void logImport(String filePath) {
        System.out.println("📥 Импорт отчёта из файла: " + filePath);
    }
}