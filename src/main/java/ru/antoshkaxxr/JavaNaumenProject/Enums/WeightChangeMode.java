package ru.antoshkaxxr.JavaNaumenProject.Enums;

/**
 * Перечисление, представляющее режим изменения веса
 */
public enum WeightChangeMode {
    HARD_WEIGHT_LOSS("Хардкорный режим похудения", 0.7),
    COMFORT_WEIGHT_LOSS("Комфортный режим похудения", 0.85),
    COMFORT_WEIGHT_GAIN("Комфортный режим набора веса", 1.15),
    HARD_WEIGHT_GAIN("Хардкорный режим набора веса", 1.3);

    private final String russianName;
    private final Double coefficient;

    WeightChangeMode(String russianName, Double coefficient) {
        this.russianName = russianName;
        this.coefficient = coefficient;
    }

    /**
     * Возвращает русское название скорости похудения/набора веса.
     *
     * @return Русское название режима похудения/набора веса, соответствующее данному значению перечисления.
     */
    public String getRussianName() {
        return russianName;
    }

    /**
     * Возвращает коэффициент, соответствующий режиму режима похудения/набора веса
     *
     * @return Коэффициент, связанный с данным режимом похудения/набора веса.
     */
    public double getCoefficient() {
        return coefficient;
    }
}
