package ru.antoshkaxxr.JavaNaumenProject.Services;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.springframework.stereotype.Service;
import ru.antoshkaxxr.JavaNaumenProject.Entities.FoodDiaryEntry;
import ru.antoshkaxxr.JavaNaumenProject.Entities.FoodDiaryReport;
import ru.antoshkaxxr.JavaNaumenProject.Enums.ReportStatus;

/**
 * Сервис для генерации файлов отчетов дневника питания.
 */
@Service
public class BaseReportFileGenerator {

    private final ExcelReportFileGeneratorImpl excelReportFileGenerator;
    private final PdfReportFileGeneratorImpl pdfReportFileGenerator;
    private final FoodDiaryServiceImpl foodDiaryService;

    private final FoodDiaryReportServiceImpl foodDiaryReportService;

    /**
     * Конструктор для инициализации сервиса.
     *
     * @param excelReportFileGenerator Генератор Excel-отчетов.
     * @param pdfReportFileGenerator   Генератор PDF-отчетов.
     * @param foodDiaryService         Сервис для работы с записями дневника питания.
     * @param foodDiaryReportService   Сервис для работы с отчетами дневника питания.
     */
    public BaseReportFileGenerator(ExcelReportFileGeneratorImpl excelReportFileGenerator,
                                   PdfReportFileGeneratorImpl pdfReportFileGenerator,
                                   FoodDiaryServiceImpl foodDiaryService,
                                   FoodDiaryReportServiceImpl foodDiaryReportService) {
        this.excelReportFileGenerator = excelReportFileGenerator;
        this.pdfReportFileGenerator = pdfReportFileGenerator;
        this.foodDiaryService = foodDiaryService;
        this.foodDiaryReportService = foodDiaryReportService;
    }

    /**
     * Метод для генерации файла отчета асинхронно.
     *
     * @param report Отчет, для которого необходимо создать файл.
     */
    public void generateFile(FoodDiaryReport report) {
        CompletableFuture.runAsync(() -> {
            var sortedFoodDiaryBetweenReportDates = getSortedFoodDiaryBetweenReportDates(report);
            try {
                var file = switch (report.getTypeFile()) {
                    case PDF -> pdfReportFileGenerator.generateFile(report, sortedFoodDiaryBetweenReportDates);
                    case EXCEL -> excelReportFileGenerator.generateFile(report, sortedFoodDiaryBetweenReportDates);
                };
                foodDiaryReportService.updateFile(report, file);
                foodDiaryReportService.updateStatus(report, ReportStatus.COMPLETED);
            } catch (Exception e) {
                foodDiaryReportService.updateStatus(report, ReportStatus.ERROR);
                throw new RuntimeException(e);
            }
        });
    }

    /**
     * Извлекает и сортирует записи дневника питания в указанном диапазоне дат.
     *
     * @param report Отчет, содержащий параметры диапазона дат.
     * @return Список записей дневника питания, отсортированный по дате потребления продукта.
     */
    private List<FoodDiaryEntry> getSortedFoodDiaryBetweenReportDates(FoodDiaryReport report) {
        var entries = foodDiaryService.getFoodDiaryEntriesBetweenDates(report.getCustomer().getId(),
                report.getStartDate(), report.getEndDate());
        entries.sort(Comparator.comparing(x -> x.getEatenProduct().getEatingDate()));
        return entries;
    }
}
