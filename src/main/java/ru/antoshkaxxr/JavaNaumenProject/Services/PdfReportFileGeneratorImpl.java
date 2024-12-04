package ru.antoshkaxxr.JavaNaumenProject.Services;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import org.springframework.stereotype.Service;
import ru.antoshkaxxr.JavaNaumenProject.Entities.EatenProduct;
import ru.antoshkaxxr.JavaNaumenProject.Entities.FoodDiaryEntry;
import ru.antoshkaxxr.JavaNaumenProject.Entities.FoodDiaryReport;

@Service
public class PdfReportFileGeneratorImpl implements ReportFileGenerator {
    @Override
    public byte[] generateFile(FoodDiaryReport report, List<FoodDiaryEntry> sortedFoodDiaryBetweenReportDates) {
        var byteArrayOutputStream = new ByteArrayOutputStream();
        var document = new Document();
        try {
            PdfWriter.getInstance(document, byteArrayOutputStream);
            document.open();
            PdfPTable table = new PdfPTable(5);
            BaseFont baseFont = BaseFont.createFont("fonts\\ofont.ru_Arial.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
            Font font = new Font(baseFont);
            addTableHeader(table, font);
            addRows(table, font, sortedFoodDiaryBetweenReportDates);
            document.add(table);
            document.close();
        } catch (DocumentException | IOException e) {
            throw new RuntimeException(e);
        }
        return byteArrayOutputStream.toByteArray();
    }

    private void addTableHeader(PdfPTable table, Font font) {
        TABLE_HEADERS
                .forEach(columnTitle -> {
                    PdfPCell header = new PdfPCell();
                    header.setBackgroundColor(BaseColor.LIGHT_GRAY);
                    header.setBorderWidth(2);
                    header.setPhrase(new Phrase(columnTitle, font));
                    table.addCell(header);
                });
    }

    private void addRows(PdfPTable table, Font font, List<FoodDiaryEntry> sortedFoodDiaryBetweenReportDates) {
        for (var entry : sortedFoodDiaryBetweenReportDates) {
            addEatenProductInfoInRow(table, font, entry.getEatenProduct());
        }
    }

    private void addEatenProductInfoInRow(PdfPTable table, Font font, EatenProduct eatenProduct) {
        addCell(table, font, eatenProduct.getProduct().getName());
        addCell(table, font, eatenProduct.getEatingDate().toString());
        addCell(table, font, eatenProduct.getEatenAmount().toString());
        var perHundredCalorie = eatenProduct.getProduct().getCaloriesNumberHundred();
        addCell(table, font, perHundredCalorie.toString());
        var totalCalories = perHundredCalorie * eatenProduct.getEatenAmount() / 100;
        addCell(table, font, String.valueOf(totalCalories));
    }

    private void addCell(PdfPTable table, Font font, String data) {
        var cell = new PdfPCell();
        cell.setPhrase(new Phrase(data, font));
        table.addCell(cell);
    }
}
