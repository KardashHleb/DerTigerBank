package org.example.ui.commands;

import org.example.service.AnalyticsService;
import org.example.service.Bank;
import org.example.ui.ConsoleIO;
import java.time.LocalDate;

public class AnalyticsCommand implements MenuCommand {
    private final AnalyticsService analytics;
    private final ConsoleIO io;

    public AnalyticsCommand(Bank bank, ConsoleIO io) {
        this.analytics = new AnalyticsService(bank);
        this.io = io;
    }

    @Override
    public String getDescription() {
        return "Аналитика доходов и расходов";
    }

    @Override
    public void execute() {
        io.printTitle("ФИНАНСОВАЯ АНАЛИТИКА");

        System.out.println("1. Текущий месяц");
        System.out.println("2. Прошлый месяц");
        System.out.println("3. Произвольный период");
        System.out.println("0. Назад");

        int choice = io.getInt("Выберите: ");

        LocalDate start, end = LocalDate.now();

        switch (choice) {
            case 0: return;
            case 1: start = LocalDate.now().withDayOfMonth(1); break;
            case 2:
                start = LocalDate.now().minusMonths(1).withDayOfMonth(1);
                end = LocalDate.now().withDayOfMonth(1).minusDays(1);
                break;
            case 3:
                start = io.getDate("Начальная дата (дд.мм.гггг): ");
                end = io.getDate("Конечная дата (дд.мм.гггг): ");
                break;
            default:
                System.out.println("Неверный выбор");
                return;
        }

        System.out.println(analytics.generateReport(start, end));
    }
}