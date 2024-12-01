package ru.antoshkaxxr.JavaNaumenProject.Models;

/**
 * Представляет структуру для хранения границ калорийных значений.
 * @param more Количество калорий для набора веса.
 * @param stable Количество калорий для сохранения веса.
 * @param less Количество калорий для сброса веса.
 */
public record CaloriesBounds(double more, double stable, double less) {
}
