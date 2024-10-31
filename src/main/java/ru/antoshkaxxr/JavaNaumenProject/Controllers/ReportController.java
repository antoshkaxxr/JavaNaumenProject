package ru.antoshkaxxr.JavaNaumenProject.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.antoshkaxxr.JavaNaumenProject.Entities.Report;
import ru.antoshkaxxr.JavaNaumenProject.Enums.ReportStatus;
import ru.antoshkaxxr.JavaNaumenProject.Services.ReportServiceImpl;

/**
 * Контроллер для работы с отчетами.
 * Предоставляет методы для создания и получения отчетов.
 */
@RestController
@RequestMapping("/reports")
public class ReportController {
    private final ReportServiceImpl reportService;

    @Autowired
    public ReportController(ReportServiceImpl reportService) {
        this.reportService = reportService;
    }

    /**
     * Создает новый отчет.
     * Сначала создается отчет, затем он формируется асинхронно.
     *
     * @return ResponseEntity, содержащая ID созданного отчета и HTTP статус 201 (Created).
     */
    @PostMapping("/create")
    public ResponseEntity<Long> createReport() {
        Long reportId = reportService.createReport();
        reportService.generateReport(reportId);
        return new ResponseEntity<>(reportId, HttpStatus.CREATED);
    }

    /**
     * Получает содержимое отчета по его id
     *
     * @param id id отчета
     * @return ResponseEntity с содержимым отчета и соответствующим HTTP статусом.
     *         В случае, если статус отчета CREATED, возвращает статус 102 (Processing).
     *         В случае ошибки возвращает статус 500 (Internal Server Error).
     *         Если отчет не найден, возвращает статус 404 (Not Found).
     */
    @GetMapping("/report/{id}")
    public ResponseEntity<String> getReportContent(@PathVariable Long id) {
        try {
            Report report = reportService.getReport(id);
            if (report.getStatus() == ReportStatus.CREATED) {
                return new ResponseEntity<>("Отчет еще не сформирован", HttpStatus.PROCESSING);
            } else if (report.getStatus() == ReportStatus.ERROR) {
                return new ResponseEntity<>("Формирование отчета завершилось с ошибкой", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            return new ResponseEntity<>(report.getContent(), HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}
