package ru.antoshkaxxr.JavaNaumenProject.Services;

import ru.antoshkaxxr.JavaNaumenProject.Entities.Report;

/**
 * Сервис для работы с отчетами.
 * Предоставляет методы для получения содержимого, создания
 * и формирования отчета
 */
public interface ReportService {
    /**
     * Возвращает отчет по его идентификатору.
     *
     * @param reportId Идентификатор отчета.
     * @return Объект {@link Report}, соответствующий указанному идентификатору.
     * @throws Exception Если отчет не найден или произошла ошибка при получении отчета.
     */
    Report getReport(Long reportId) throws Exception;

    /**
     * Создает новый объект отчета в базе данных и возвращает его идентификатор.
     *
     * @return Идентификатор созданного отчета.
     */
    Long createReport();

    /**
     * Асинхронный метод, формирующий отчет по указанному идентификатору.
     *
     * @param reportId Идентификатор отчета, который необходимо сформировать.
     */
    void generateReport(Long reportId);
}
