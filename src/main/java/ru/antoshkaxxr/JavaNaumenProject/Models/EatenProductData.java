package ru.antoshkaxxr.JavaNaumenProject.Models;

import java.time.LocalDate;

/**
 * Представляет данные о съеденном продукте.
 */
public record EatenProductData(Long id, LocalDate date, Double amount) {
}
