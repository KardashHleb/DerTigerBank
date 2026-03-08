package org.example.service.exporter;


import org.example.service.DTO.ReportData;

import java.io.IOException;

public interface ReportExporter {
    void export(ReportData data, String filePath) throws IOException;
    String getFormat();
}