package ru.antoshkaxxr.JavaNaumenProject.Services;

import java.util.List;
import ru.antoshkaxxr.JavaNaumenProject.Entities.FoodDiaryEntry;
import ru.antoshkaxxr.JavaNaumenProject.Entities.FoodDiaryReport;

public interface ReportFileGenerator {

    List<String> TABLE_HEADERS = List.of("Название продукта", "Дата",
            "Количество", "Калориий на 100 гр.", "Всего калорий");

    byte[] generateFile(FoodDiaryReport report, List<FoodDiaryEntry> sortedFoodDiaryBetweenReportDates);
}
