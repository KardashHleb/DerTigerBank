package org.example.ui.commands.interfaces;

import java.io.IOException;

public interface BankReporting {
    String generateBankReport();
    String readReportFromFile(String filePath);
    void exportReport(String format, String filename) throws IOException;
}
