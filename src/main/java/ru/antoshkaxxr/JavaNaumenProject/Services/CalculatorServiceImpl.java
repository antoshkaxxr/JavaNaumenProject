package ru.antoshkaxxr.JavaNaumenProject.Services;

import ru.antoshkaxxr.JavaNaumenProject.Models.*;

@SuppressWarnings("checkstyle:MagicNumber")
public class CalculatorServiceImpl {
    private CalculatorServiceImpl() {
    }

    public static BodyData getCalculatedData(CalculatorData calculatorData) {
        var bodyMassIndex = getBodyMassIndex(calculatorData.weight(), calculatorData.heightInSm());
        var bmiRange = getBodyMassIndexRange(bodyMassIndex);
        var bmiData = new BMIData(bmiRange, bodyMassIndex);
        var caloriesData = getCaloriesData(calculatorData);
        return new BodyData(caloriesData, bmiData);
    }

    public static double getBodyMassIndex(double weight, double heightInSm) {
        var heightInMeters = heightInSm / 100;
        return weight / (heightInMeters * heightInMeters);
    }

    public static CaloriesBounds getCaloriesData(CalculatorData calculatorData) {
        var stable = switch (calculatorData.formula()) {
            case HARRIS_BENEDICT -> getCaloriesByHarrisBenedict(calculatorData);
            case MIFFLIN_ST_GEORGE -> getCaloriesByMifflinStGeorge(calculatorData);
        };
        var stableWithCoefficient = stable * calculatorData.physicalActivity().getCoefficient();
        return new CaloriesBounds(stableWithCoefficient * 1.3,
                stableWithCoefficient,
                stableWithCoefficient * 0.7);
    }

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

    private static double getCaloriesByHarrisBenedictMen(CalculatorData calculatorData) {
        return 13.397 * calculatorData.weight()
                + 4.799 * calculatorData.heightInSm()
                - 5.677 * calculatorData.age()
                + 88.362;
    }

    private static double getCaloriesByHarrisBenedictWomen(CalculatorData calculatorData) {
        return 9.247 * calculatorData.weight()
                + 3.098 * calculatorData.heightInSm()
                - 4.330 * calculatorData.age()
                + 447.593;
    }

    private static BMIRange getBodyMassIndexRange(double bodyMassIndex) {
        var bmiRange = BMIRange.OBESE_CLASS_THREE;
        if (bodyMassIndex < 16.0) {
            bmiRange = BMIRange.SEVERE_THINNESS;
        } else if (bodyMassIndex < 17.0) {
            bmiRange = BMIRange.MODERATE_THINNESS;
        } else if (bodyMassIndex < 18.5) {
            bmiRange = BMIRange.MILD_THINNESS;
        } else if (bodyMassIndex < 25.0) {
            bmiRange = BMIRange.NORMAL;
        } else if (bodyMassIndex < 30.0) {
            bmiRange = BMIRange.OVERWEIGHT;
        } else if (bodyMassIndex < 35.0) {
            bmiRange = BMIRange.OBESE_CLASS_ONE;
        } else if (bodyMassIndex < 40.0) {
            bmiRange = BMIRange.OBESE_CLASS_TWO;
        }
        return bmiRange;
    }
}
