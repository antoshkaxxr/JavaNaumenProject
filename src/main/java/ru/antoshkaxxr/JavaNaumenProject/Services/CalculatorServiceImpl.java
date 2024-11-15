package ru.antoshkaxxr.JavaNaumenProject.Services;

import ru.antoshkaxxr.JavaNaumenProject.Dto.CalculatorData;
import ru.antoshkaxxr.JavaNaumenProject.Models.BodyData;
import ru.antoshkaxxr.JavaNaumenProject.Models.CaloriesBounds;

public class CalculatorServiceImpl implements CalculatorService {
    private CalculatorServiceImpl() {
    }

    public static BodyData getCalculatedData(CalculatorData calculatorData) {
        var bodyMassIndex = getBodyMassIndex(calculatorData.weight(), calculatorData.heightInSm());
        var caloriesData = getCaloriesData(calculatorData);
        return new BodyData(caloriesData, bodyMassIndex);
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    public static double getBodyMassIndex(double weight, double heightInSm) {
        var heightInMeters = heightInSm / 100;
        return weight / (heightInMeters * heightInMeters);
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    public static CaloriesBounds getCaloriesData(CalculatorData calculatorData) {
        var stable = switch (calculatorData.formula()) {
            case HARRIS_BENEDICT -> getCaloriesByHarrisBenedict(calculatorData);
            case MIFFLIN_ST_GEORGE -> getCaloriesByMifflinStGeorge(calculatorData);
        };
        return new CaloriesBounds(stable * 1.3, stable, stable * 0.7);
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    private static double getCaloriesByMifflinStGeorge(CalculatorData calculatorData) {
        var base = 10 * calculatorData.weight()
                + 6.25 * calculatorData.heightInSm()
                 - 5 * calculatorData.age();
        return switch (calculatorData.sex()) {
            case MALE -> base + 5;
            case FEMALE -> base - 161;
        };
    }

    private static double getCaloriesByHarrisBenedict(CalculatorData calculatorData) {
        return switch (calculatorData.sex()) {
            case MALE -> getCaloriesByHarrisBenedictMen(calculatorData);
            case FEMALE -> getCaloriesByHarrisBenedictWomen(calculatorData);
        };
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    private static double getCaloriesByHarrisBenedictMen(CalculatorData calculatorData) {
        return 13.397 * calculatorData.weight()
                + 4.799 * calculatorData.heightInSm()
                - 5.677 * calculatorData.age()
                + 88.362;
    }

    @SuppressWarnings("checkstyle:MagicNumber")
    private static double getCaloriesByHarrisBenedictWomen(CalculatorData calculatorData) {
        return 9.247 * calculatorData.weight()
                + 3.098 * calculatorData.heightInSm()
                - 4.330 * calculatorData.age()
                + 447.593;
    }
}
