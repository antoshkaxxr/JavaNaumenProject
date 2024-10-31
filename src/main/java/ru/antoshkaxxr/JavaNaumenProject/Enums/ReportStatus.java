package ru.antoshkaxxr.JavaNaumenProject.Enums;

/**
 * Перечисление, представляющее различные статусы формирования отчета
 * {@link #CREATED} - отчет создан
 * {@link #COMPLETED} - отчет сформирован
 * {@link @ERROR} - формирование отчета завершилось с ошибкой
 */
public enum ReportStatus {
    CREATED,
    COMPLETED,
    ERROR
}
