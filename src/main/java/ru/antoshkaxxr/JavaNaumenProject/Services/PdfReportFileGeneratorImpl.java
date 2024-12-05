package ru.antoshkaxxr.JavaNaumenProject.Services;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import org.springframework.stereotype.Service;
import ru.antoshkaxxr.JavaNaumenProject.Entities.EatenProduct;
import ru.antoshkaxxr.JavaNaumenProject.Entities.FoodDiaryEntry;
import ru.antoshkaxxr.JavaNaumenProject.Entities.FoodDiaryReport;

/**
 * Сервис для генерации отчетов в формате PDF.
 */
@Service
public class PdfReportFileGeneratorImpl implements ReportFileGenerator {

    private static Path fontPath = Paths.get("fonts", "ofont.ru_Arial.ttf");

    /**
     * Генерирует PDF-файл для отчета по дневнику питания.
     *
     * @param report                        Отчет, для которого создается файл.
     * @param sortedFoodDiaryBetweenReportDates Список записей дневника питания, отсортированных по дате.
     * @return Байтовый массив, представляющий содержимое PDF-файла.
     */
    @Override
    public byte[] generateFile(FoodDiaryReport report, List<FoodDiaryEntry> sortedFoodDiaryBetweenReportDates) {
        var byteArrayOutputStream = new ByteArrayOutputStream();
        var document = new Document();
        try {
            PdfWriter.getInstance(document, byteArrayOutputStream);
            document.open();
            PdfPTable table = new PdfPTable(TABLE_HEADERS.size());
            BaseFont baseFont = BaseFont.createFont(fontPath.toString(),
                    BaseFont.IDENTITY_H, BaseFont.EMBEDDED);
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

    /**
     * Добавляет заголовок таблицы в PDF-документ.
     *
     * @param table Таблица, в которую добавляется заголовок.
     * @param font  Шрифт, используемый для текста заголовка.
     */
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

    /**
     * Добавляет строки с данными в таблицу PDF-документа.
     *
     * @param table                           Таблица, в которую добавляются строки.
     * @param font                            Шрифт, используемый для текста.
     * @param sortedFoodDiaryBetweenReportDates Список записей дневника питания, отсортированных по дате.
     */
    private void addRows(PdfPTable table, Font font, List<FoodDiaryEntry> sortedFoodDiaryBetweenReportDates) {
        for (var entry : sortedFoodDiaryBetweenReportDates) {
            addEatenProductInfoInRow(table, font, entry.getEatenProduct());
        }
    }

    /**
     * Добавляет данные о съеденном продукте в строку таблицы.
     *
     * @param table        Таблица, в которую добавляются данные.
     * @param font         Шрифт, используемый для текста.
     * @param eatenProduct Информация о съеденном продукте.
     */
    @SuppressWarnings("checkstyle:MagicNumber")
    private void addEatenProductInfoInRow(PdfPTable table, Font font, EatenProduct eatenProduct) {
        addCell(table, font, eatenProduct.getProduct().getName());
        addCell(table, font, eatenProduct.getEatingDate().toString());
        addCell(table, font, eatenProduct.getEatenAmount().toString());
        var perHundredCalorie = eatenProduct.getProduct().getCaloriesNumberHundred();
        addCell(table, font, perHundredCalorie.toString());
        var totalCalories = perHundredCalorie * eatenProduct.getEatenAmount() / 100;
        addCell(table, font, String.valueOf(totalCalories));
    }

    /**
     * Добавляет ячейку в таблицу с указанным текстом.
     *
     * @param table Таблица, в которую добавляется ячейка.
     * @param font  Шрифт, используемый для текста.
     * @param data  Текст, который добавляется в ячейку.
     */
    private void addCell(PdfPTable table, Font font, String data) {
        var cell = new PdfPCell();
        cell.setPhrase(new Phrase(data, font));
        table.addCell(cell);
    }
}
