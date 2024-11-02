package ru.antoshkaxxr.JavaNaumenProject.Services;

import ru.antoshkaxxr.JavaNaumenProject.Entities.Report;

/**
 * Сервис для работы с отчетами.
 * Предоставляет методы для получения содержимого, создания
 * и формирования отчета
 */
public interface ReportService {
    /**
     * Возвращает отчет по его id
     * @param reportId id отчета
     */
    Report getReport(Long reportId) throws Exception;

    /**
     * Создает объект отчета в БД и возвращает id этого объекта
     */
    Long createReport();

    /**
     * Асинхронный метод, формирующий отчет
     * @param reportId id отчета
     */
    void generateReport(Long reportId);
}
