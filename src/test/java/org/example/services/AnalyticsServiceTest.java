package org.example.services;

import org.example.model.*;
import org.example.service.AnalyticsService;
import org.example.ui.commands.interfaces.AccountProvider;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AnalyticsServiceTest {

    @Test
    void getTotalIncome_ShouldReturnSumOfSuccessfulDeposits() {
        // 1. Создаём заглушку
        AccountProvider mockProvider = mock(AccountProvider.class);
        AnalyticsService service = new AnalyticsService(mockProvider);

        // 2. Создаём счёт через spy
        Customer customer = new Customer("Тест");
        Account account = new DebitAccount(customer);
        Account accountSpy = spy(account);

        // 3. Создаём транзакцию
        Transaction deposit = new Transaction(
                null,
                "ACC123",
                1000.0,
                TransactionType.DEPOSIT,
                true,
                "Пополнение"
        );

        // 4. Подменяем getTransactions()
        when(accountSpy.getTransactions()).thenReturn(List.of(deposit));

        // 5. Настраиваем заглушку
        when(mockProvider.getAllAccounts()).thenReturn(List.of(accountSpy));

        // 6. Вызываем метод
        double result = service.getTotalIncome(LocalDate.MIN, LocalDate.MAX);

        // 7. Проверяем
        assertEquals(1000.0, result);
        verify(mockProvider).getAllAccounts();
    }
}