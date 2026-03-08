package org.example.services.importer;

import org.example.service.DTO.ReportData;
import org.example.service.importer.BaseReportImporter;
import org.example.service.importer.ImporterFactory;
import org.example.service.importer.StorageService;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StorageServiceTest {

    @Test
    void loadReport_ShouldReturnReportData() throws IOException {
        // 1. Создаём мок импортёра
        BaseReportImporter mockImporter = mock(BaseReportImporter.class);
        ReportData expectedData = new ReportData();

        // 2. Мокаем статический метод фабрики
        try (var mockedStatic = mockStatic(ImporterFactory.class)) {
            mockedStatic.when(() -> ImporterFactory.createImporter("test.json"))
                    .thenReturn(mockImporter);

            when(mockImporter.importReport("test.json")).thenReturn(expectedData);

            // 3. Тестируем
            StorageService service = new StorageService();
            ReportData result = service.loadReport("test.json");

            // 4. Проверяем
            assertSame(expectedData, result);
            verify(mockImporter).importReport("test.json");
        }
    }
}
