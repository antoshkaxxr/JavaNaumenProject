package ru.antoshkaxxr.JavaNaumenProject.Enums;

/**
 * Перечисление, представляющее диапазоны индекса массы тела (BMI) и их русские названия.
 */
public enum BMIRange {
    SEVERE_THINNESS("Сильная худоба"),
    MODERATE_THINNESS("Умеренная худоба"),
    MILD_THINNESS("Легкая худоба"),
    NORMAL("Норма"),
    OVERWEIGHT("Избыточный вес"),
    OBESE_CLASS_ONE("Ожирение первой степени"),
    OBESE_CLASS_TWO("Ожирение второй степени"),
    OBESE_CLASS_THREE("Ожирение третьей степени");

    private final String russianName;

    BMIRange(String russianName) {
        this.russianName = russianName;
    }

    /**
     * Возвращает русское название состояния тела (по массе тела).
     *
     * @return Русское название состояния тела, соответствующее данному значению перечисления.
     */
    public String getRussianName() {
        return russianName;
    }
}
