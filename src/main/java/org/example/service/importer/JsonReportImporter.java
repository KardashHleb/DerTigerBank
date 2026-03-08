package org.example.service.importer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.example.service.DTO.ReportData;
import java.io.File;
import java.io.IOException;

public class JsonReportImporter extends BaseReportImporter {
    private final ObjectMapper mapper;

    public JsonReportImporter() {
        this.mapper = new ObjectMapper();
        this.mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Override
    protected ReportData parseFile(File file) throws IOException {
        return mapper.readValue(file, ReportData.class);
    }
}