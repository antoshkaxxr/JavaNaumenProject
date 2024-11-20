package ru.antoshkaxxr.JavaNaumenProject.Models;

import java.lang.reflect.Array;
import java.time.OffsetDateTime;
import java.util.List;

public record DataStatistic(OffsetDateTime[] labelsDate, Double[] valuesRealConsumption, Double[] valuesPlanConsumption) {
}
