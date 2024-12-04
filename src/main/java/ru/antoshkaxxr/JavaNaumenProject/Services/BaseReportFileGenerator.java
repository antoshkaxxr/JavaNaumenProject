package ru.antoshkaxxr.JavaNaumenProject.Services;

import java.util.Comparator;
import java.util.List;
import org.springframework.stereotype.Service;
import ru.antoshkaxxr.JavaNaumenProject.Entities.FoodDiaryEntry;
import ru.antoshkaxxr.JavaNaumenProject.Entities.FoodDiaryReport;

@Service
public class BaseReportFileGenerator {

    private final ExcelReportFileGeneratorImpl excelReportFileGenerator;
    private final PdfReportFileGeneratorImpl pdfReportFileGenerator;
    private final FoodDiaryServiceImpl foodDiaryService;

    public BaseReportFileGenerator(ExcelReportFileGeneratorImpl excelReportFileGenerator,
                                   PdfReportFileGeneratorImpl pdfReportFileGenerator,
                                   FoodDiaryServiceImpl foodDiaryService) {
        this.excelReportFileGenerator = excelReportFileGenerator;
        this.pdfReportFileGenerator = pdfReportFileGenerator;
        this.foodDiaryService = foodDiaryService;
    }

    public byte[] generateFile(FoodDiaryReport report) {
        var sortedFoodDiaryBetweenReportDates = getSortedFoodDiaryBetweenReportDates(report);
        return switch (report.getTypeFile()) {
            case PDF -> pdfReportFileGenerator.generateFile(report, sortedFoodDiaryBetweenReportDates);
            case EXCEL -> excelReportFileGenerator.generateFile(report, sortedFoodDiaryBetweenReportDates);
        };
    }

    private List<FoodDiaryEntry> getSortedFoodDiaryBetweenReportDates(FoodDiaryReport report) {
        var entries = foodDiaryService.getFoodDiaryEntriesBetweenDates(report.getCustomer().getId(),
                report.getStartDate(), report.getEndDate());
        entries.sort(Comparator.comparing(x -> x.getEatenProduct().getEatingDate()));
        return entries;
    }
}
