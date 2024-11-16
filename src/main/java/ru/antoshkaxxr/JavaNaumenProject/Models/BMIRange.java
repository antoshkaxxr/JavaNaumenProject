package ru.antoshkaxxr.JavaNaumenProject.Models;

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

    private BMIRange(String russianName) {
        this.russianName = russianName;
    }

    public String getRussianName() {
        return russianName;
    }
}
