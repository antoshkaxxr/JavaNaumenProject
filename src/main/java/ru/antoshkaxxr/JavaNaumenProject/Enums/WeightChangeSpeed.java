package ru.antoshkaxxr.JavaNaumenProject.Enums;

/**
 * Перечисление, представляющее скорость похудения/набора веса
 */
public enum WeightChangeSpeed {
    COMFORT_SPEED("Комфортный режим"),
    HARD_SPEED("Хардкорный режим");

    private final String russianName;

    WeightChangeSpeed(String russianName) {
        this.russianName = russianName;
    }

    /**
     * Возвращает русское название скорости похудения/набора веса.
     *
     * @return Русское название скорости похудения/набора веса, соответствующее данному значению перечисления.
     */
    public String getRussianName() {
        return russianName;
    }
}
