package org.example.service.exporter;

import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import org.example.service.ReportData;

import java.io.File;
import java.io.IOException;

public class CsvExporter implements ReportExporter {
    private final CsvMapper mapper;

    public CsvExporter() {
        this.mapper = new CsvMapper();
    }

    @Override
    public void export(ReportData data, String filePath) throws IOException {
        CsvSchema schema = mapper.schemaFor(ReportData.class).withHeader();
        mapper.writer(schema).writeValue(new File(filePath), data);
    }

    @Override
    public String getFormat() {
        return "csv";
    }
}