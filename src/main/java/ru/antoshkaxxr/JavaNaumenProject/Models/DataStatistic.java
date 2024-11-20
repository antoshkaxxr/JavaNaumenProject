package ru.antoshkaxxr.JavaNaumenProject.Models;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.util.List;

public record DataStatistic(LocalDate[] labelsDate, Double[] valuesRealConsumption, Double[] valuesPlanConsumption) {
}
