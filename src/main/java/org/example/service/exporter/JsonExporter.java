package org.example.service.exporter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.example.service.ReportData;


import java.io.File;
import java.io.IOException;

public class JsonExporter implements ReportExporter {
    private final ObjectMapper mapper;

    public JsonExporter() {
        this.mapper = new ObjectMapper();
        this.mapper.enable(SerializationFeature.INDENT_OUTPUT);
    }

    @Override
    public void export(ReportData data, String filePath) throws IOException {
        mapper.writeValue(new File(filePath), data);
    }

    @Override
    public String getFormat() {
        return "json";
    }
}