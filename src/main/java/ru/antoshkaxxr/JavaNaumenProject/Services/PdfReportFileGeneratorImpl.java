package ru.antoshkaxxr.JavaNaumenProject.Services;

import java.util.List;
import org.springframework.stereotype.Service;
import ru.antoshkaxxr.JavaNaumenProject.Entities.FoodDiaryEntry;
import ru.antoshkaxxr.JavaNaumenProject.Entities.FoodDiaryReport;

@Service
public class PdfReportFileGeneratorImpl implements ReportFileGenerator {
    @Override
    public byte[] generateFile(FoodDiaryReport report, List<FoodDiaryEntry> sortedFoodDiaryBetweenReportDates) {
        return new byte[0];
    }
}
