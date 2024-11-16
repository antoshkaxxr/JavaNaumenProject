package ru.antoshkaxxr.JavaNaumenProject.Models;

/**
 * Представляет данные для калькулятора, включающие возраст, пол, вес, рост,
 * уровень физической активности и формулу расчета.
 * @param age Возраст человека.
 * @param sex Пол (мужской или женский).
 * @param weight Вес человека.
 * @param heightInSm Рост человека в сантиметрах.
 * @param physicalActivity Уровень физической активности.
 * @param formula Формула для расчета калорий.
 */
public record CalculatorData(int age, Sex sex, double weight, double heightInSm,
                             PhysicalActivity physicalActivity, CalculationFormula formula) {
}
