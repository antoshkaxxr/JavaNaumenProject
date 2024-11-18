package ru.antoshkaxxr.JavaNaumenProject.Models;

import ru.antoshkaxxr.JavaNaumenProject.Enums.CalculationFormula;
import ru.antoshkaxxr.JavaNaumenProject.Enums.PhysicalActivity;
import ru.antoshkaxxr.JavaNaumenProject.Enums.Sex;
import ru.antoshkaxxr.JavaNaumenProject.Enums.WeightChangeSpeed;

/**
 * Представляет данные для калькулятора, включающие возраст, пол, вес, рост,
 * уровень физической активности и формулу расчета.
 * @param age Возраст человека.
 * @param sex Пол (мужской или женский).
 * @param currentWeight Текущий вес человека.
 * @param targetWeight Желаемый вес человекаю
 * @param heightInSm Рост человека в сантиметрах.
 * @param physicalActivity Уровень физической активности.
 * @param formula Формула для расчета калорий.
 * @param weightChangeSpeed Скорость изменения веса
 */
public record TargetData(int age, Sex sex, double currentWeight, double targetWeight,
                         double heightInSm, PhysicalActivity physicalActivity,
                         CalculationFormula formula, WeightChangeSpeed weightChangeSpeed) {
}
