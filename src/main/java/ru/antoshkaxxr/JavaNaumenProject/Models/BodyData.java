package ru.antoshkaxxr.JavaNaumenProject.Models;

/**
 * Представляет данные о теле, включая границы калорийных значений и индекс массы тела (BMI).
 * @param bounds Границы калорийных значений.
 * @param bodyMassIndex Информация о индексе массы тела (BMI).
 */
public record BodyData(CaloriesBounds bounds, BMIData bodyMassIndex) {
}
