package ru.antoshkaxxr.JavaNaumenProject.Models;

import java.time.LocalDate;

/**
 * Представляет данные о статистике цели.
 */
public record DataStatistic(LocalDate[] labelsDate, Double[] valuesRealConsumption, Double[] valuesPlanConsumption) {
}
