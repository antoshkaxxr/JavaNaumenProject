package ru.antoshkaxxr.JavaNaumenProject.Services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import ru.antoshkaxxr.JavaNaumenProject.Entities.EatenProduct;
import ru.antoshkaxxr.JavaNaumenProject.Entities.FoodDiaryEntry;
import ru.antoshkaxxr.JavaNaumenProject.Entities.FoodDiaryReport;

/**
 * Сервис для генерации отчетов в формате Excel.
 */
@Service
public class ExcelReportFileGeneratorImpl implements ReportFileGenerator {

    /**
     * Генерирует Excel-файл для отчета по дневнику питания.
     *
     * @param report Отчет, для которого создается файл.
     * @param sortedFoodDiaryBetweenReportDates Список записей дневника питания, отсортированных по дате.
     * @return Байтовый массив, представляющий содержимое Excel-файла.
     */
    @Override
    public byte[] generateFile(FoodDiaryReport report, List<FoodDiaryEntry> sortedFoodDiaryBetweenReportDates) {
        Workbook book = new XSSFWorkbook();
        var sheet = book.createSheet("Отчёт");
        addTableHeader(sheet, 0);
        var startAfterHeader = 1;
        for (var i = startAfterHeader; i < sortedFoodDiaryBetweenReportDates.size() + startAfterHeader; i++) {
            var row = sheet.createRow(i);
            addEatenProductInfoInRow(row,
                    sortedFoodDiaryBetweenReportDates.get(i - startAfterHeader).getEatenProduct());
        }

        return getBytesOfExcelFile(book);
    }

    /**
     * Добавляет заголовок таблицы на указанный лист Excel.
     *
     * @param sheet       Лист Excel, на который добавляется заголовок.
     * @param headedIndex Индекс строки, на которой создается заголовок.
     */
    private void addTableHeader(Sheet sheet, int headedIndex) {
        var row = sheet.createRow(headedIndex);
        for (var i = 0; i < TABLE_HEADERS.size(); i++) {
            var currCell = row.createCell(i);
            currCell.setCellValue(TABLE_HEADERS.get(i));
        }
    }

    /**
     * Добавляет данные о съеденном продукте в строку таблицы.
     *
     * @param row          Строка, в которую добавляются данные.
     * @param eatenProduct Информация о съеденном продукте.
     */
    @SuppressWarnings("checkstyle:MagicNumber")
    private void addEatenProductInfoInRow(Row row, EatenProduct eatenProduct) {
        addCell(row, 0, eatenProduct.getProduct().getName());
        addCell(row, 1, eatenProduct.getEatingDate().toString());
        addCell(row, 2, eatenProduct.getEatenAmount().toString());
        var perHundredCalorie = eatenProduct.getProduct().getCaloriesNumberHundred();
        addCell(row, 3, perHundredCalorie.toString());
        var totalCalories = perHundredCalorie * eatenProduct.getEatenAmount() / 100;
        addCell(row, 4, String.valueOf(totalCalories));
    }

    /**
     * Добавляет значение в ячейку строки таблицы.
     *
     * @param row       Строка таблицы, в которую добавляется ячейка.
     * @param cellIndex Индекс ячейки в строке.
     * @param value     Значение, которое нужно записать в ячейку.
     */
    private void addCell(Row row, int cellIndex, String value) {
        var cell = row.createCell(cellIndex);
        cell.setCellValue(value);
    }

    /**
     * Преобразует объект Workbook в массив байтов.
     *
     * @param workbook Объект Excel-книги.
     * @return Байтовый массив, представляющий содержимое Excel-книги.
     */
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
