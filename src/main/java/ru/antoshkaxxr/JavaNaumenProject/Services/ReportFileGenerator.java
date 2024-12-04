package ru.antoshkaxxr.JavaNaumenProject.Services;

import java.util.List;
import ru.antoshkaxxr.JavaNaumenProject.Entities.FoodDiaryEntry;
import ru.antoshkaxxr.JavaNaumenProject.Entities.FoodDiaryReport;

public interface ReportFileGenerator {

    List<String> TABLE_HEADERS = List.of("Название продукта", "Дата",
            "Количество", "Ккал на 100 гр.", "Всего ккал");

    byte[] generateFile(FoodDiaryReport report, List<FoodDiaryEntry> sortedFoodDiaryBetweenReportDates);
}
