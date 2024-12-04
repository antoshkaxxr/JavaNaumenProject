package ru.antoshkaxxr.JavaNaumenProject.Services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import ru.antoshkaxxr.JavaNaumenProject.Entities.EatenProduct;
import ru.antoshkaxxr.JavaNaumenProject.Entities.FoodDiaryEntry;
import ru.antoshkaxxr.JavaNaumenProject.Entities.FoodDiaryReport;

@Service
public class PdfReportFileGeneratorImpl implements ReportFileGenerator {
    @Override
    public byte[] generateFile(FoodDiaryReport report, List<FoodDiaryEntry> sortedFoodDiaryBetweenReportDates) {
        Workbook book = new XSSFWorkbook();
        var sheet = book.createSheet("Answers");
        for (var i = 0; i < sortedFoodDiaryBetweenReportDates.size(); i++) {
            var row = sheet.createRow(i);
            setEatenProductInfoInRow(row, sortedFoodDiaryBetweenReportDates.get(i).getEatenProduct());
        }

        return getBytesOfExcelFile(book);
    }

    private void setEatenProductInfoInRow(Row row, EatenProduct eatenProduct) {
        var productCell = row.createCell(0);
        productCell.setCellValue(eatenProduct.getProduct().getName());
        var dateCell = row.createCell(1);
        dateCell.setCellValue(eatenProduct.getEatingDate());
        var amountCell = row.createCell(2);
        amountCell.setCellValue(eatenProduct.getEatenAmount());
        var perHundredCalorie = eatenProduct.getProduct().getCaloriesNumberHundred();
        var perHundredCalorieCell = row.createCell(3);
        perHundredCalorieCell.setCellValue(perHundredCalorie);
        var totalCalorieCell = row.createCell(4);
        totalCalorieCell.setCellValue(perHundredCalorie * eatenProduct.getEatenAmount() / 100);
    }

    private byte[] getBytesOfExcelFile(Workbook workbook) {
        var outputStream = new ByteArrayOutputStream();
        try {
            workbook.write(outputStream);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return outputStream.toByteArray();
    }
}
