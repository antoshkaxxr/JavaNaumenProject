package ru.antoshkaxxr.JavaNaumenProject.Enums;

/**
 * Перечисление, представляющее различные статусы формирования отчета.
 */
public enum ReportStatus {

    /**
     * Отчет создан.
     */
    CREATED("Отчет формируется"),

    /**
     * Отчет сформирован.
     */
    COMPLETED("Отчет сформирован"),

    /**
     * Формирование отчета завершилось с ошибкой.
     */
    ERROR("Произошла ошибка");

    private final String russianName;

    ReportStatus(String russianName) {
        this.russianName = russianName;
    }

    /**
     * Возвращает русское название статуса.
     *
     * @return Русское название статуса, соответствующее данному значению перечисления.
     */
    public String getRussianName() {
        return russianName;
    }
}
