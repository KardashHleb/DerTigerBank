package org.example.service.importer;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import org.example.service.DTO.ReportData;
import java.io.File;
import java.io.IOException;

public class YamlReportImporter extends BaseReportImporter {
    private final ObjectMapper mapper;

    public YamlReportImporter() {
        this.mapper = new ObjectMapper(new YAMLFactory());
        this.mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    @Override
    protected ReportData parseFile(File file) throws IOException {
        return mapper.readValue(file, ReportData.class);
    }
}