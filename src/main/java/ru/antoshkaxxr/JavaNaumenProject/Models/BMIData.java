package ru.antoshkaxxr.JavaNaumenProject.Models;

import ru.antoshkaxxr.JavaNaumenProject.Enums.BMIRange;

/**
 * Записывает данные о индексе массы тела (ИМТ) и соответствующем диапазоне.
 * Этот класс представляет собой неизменяемую запись, содержащую информацию о
 * значении индекса массы тела и диапазоне, к которому это значение относится.
 *
 * @param range Диапазон, к которому принадлежит значение ИМТ.
 * @param bmi Значение индекса массы тела.
 */
public record BMIData(BMIRange range, double bmi) {
}
