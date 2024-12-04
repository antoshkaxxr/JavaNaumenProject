package ru.antoshkaxxr.JavaNaumenProject.Services;

import java.util.List;
import ru.antoshkaxxr.JavaNaumenProject.Entities.FoodDiaryEntry;
import ru.antoshkaxxr.JavaNaumenProject.Entities.FoodDiaryReport;

/**
 * Интерфейс для генерации отчетов в различных форматах.
 */
public interface ReportFileGenerator {

    List<String> TABLE_HEADERS = List.of("Название продукта", "Дата",
            "Количество", "Ккал на 100 гр.", "Всего ккал");

    /**
     * Генерирует файл отчета для дневника питания.
     *
     * @param report                        Отчет, для которого создается файл.
     * @param sortedFoodDiaryBetweenReportDates Список записей дневника питания, отсортированных по дате.
     * @return Байтовый массив, представляющий содержимое файла отчета.
     */
    byte[] generateFile(FoodDiaryReport report, List<FoodDiaryEntry> sortedFoodDiaryBetweenReportDates);
}
