package ru.antoshkaxxr.JavaNaumenProject.Models;

/**
 * Перечисление, представляющее формулы для расчета калорий и их русские названия.
 */
public enum CalculationFormula {

    MIFFLIN_ST_GEORGE("Миффлина - Сан Жеора"),
    HARRIS_BENEDICT("Харриса-Бенедикта");

    private final String russianName;

    CalculationFormula(String russianName) {
        this.russianName = russianName;
    }

    /**
     * Возвращает русское название формулы для расчета калорий.
     *
     * @return Русское название формулы для расчета калорий, соответствующее данному значению перечисления.
     */
    public String getRussianName() {
        return russianName;
    }
}
