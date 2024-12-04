package ru.antoshkaxxr.JavaNaumenProject.Services;

import java.util.Comparator;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import org.springframework.stereotype.Service;
import ru.antoshkaxxr.JavaNaumenProject.Entities.FoodDiaryEntry;
import ru.antoshkaxxr.JavaNaumenProject.Entities.FoodDiaryReport;
import ru.antoshkaxxr.JavaNaumenProject.Enums.ReportStatus;

@Service
public class BaseReportFileGenerator {

    private final ExcelReportFileGeneratorImpl excelReportFileGenerator;
    private final PdfReportFileGeneratorImpl pdfReportFileGenerator;
    private final FoodDiaryServiceImpl foodDiaryService;

    private final FoodDiaryReportServiceImpl foodDiaryReportService;

    public BaseReportFileGenerator(ExcelReportFileGeneratorImpl excelReportFileGenerator,
                                   PdfReportFileGeneratorImpl pdfReportFileGenerator,
                                   FoodDiaryServiceImpl foodDiaryService,
                                   FoodDiaryReportServiceImpl foodDiaryReportService) {
        this.excelReportFileGenerator = excelReportFileGenerator;
        this.pdfReportFileGenerator = pdfReportFileGenerator;
        this.foodDiaryService = foodDiaryService;
        this.foodDiaryReportService = foodDiaryReportService;
    }

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

    private List<FoodDiaryEntry> getSortedFoodDiaryBetweenReportDates(FoodDiaryReport report) {
        var entries = foodDiaryService.getFoodDiaryEntriesBetweenDates(report.getCustomer().getId(),
                report.getStartDate(), report.getEndDate());
        entries.sort(Comparator.comparing(x -> x.getEatenProduct().getEatingDate()));
        return entries;
    }
}
