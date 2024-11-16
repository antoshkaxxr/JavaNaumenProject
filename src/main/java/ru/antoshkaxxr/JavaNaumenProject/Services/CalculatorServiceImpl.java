package ru.antoshkaxxr.JavaNaumenProject.Services;


import ru.antoshkaxxr.JavaNaumenProject.Models.BMIData;
import ru.antoshkaxxr.JavaNaumenProject.Models.BMIRange;
import ru.antoshkaxxr.JavaNaumenProject.Models.BodyData;
import ru.antoshkaxxr.JavaNaumenProject.Models.CalculatorData;
import ru.antoshkaxxr.JavaNaumenProject.Models.CaloriesBounds;


/**
 * Реализация сервиса для вычисления данных о теле, включая индекс массы тела (ИМТ)
 * и необходимое количество калорий в зависимости от параметров пользователя.
 *
 * <p>Класс предоставляет методы для вычисления ИМТ и калорийности с использованием
 * формул Харриса-Бенедикта и Миффлина-Сент-Жоржа.</p>
 */
@SuppressWarnings("checkstyle:MagicNumber")
public class CalculatorServiceImpl {
    private CalculatorServiceImpl() {
    }

    /**
     * Вычисляет данные о теле на основе переданных параметров.
     *
     * @param calculatorData Данные для расчета, включая вес, рост, возраст и пол.
     * @return Объект BodyData, содержащий данные о калориях и ИМТ.
     */
    public static BodyData getCalculatedData(CalculatorData calculatorData) {
        var bodyMassIndex = getBodyMassIndex(calculatorData.weight(), calculatorData.heightInSm());
        var bmiRange = getBodyMassIndexRange(bodyMassIndex);
        var bmiData = new BMIData(bmiRange, bodyMassIndex);
        var caloriesData = getCaloriesData(calculatorData);
        return new BodyData(caloriesData, bmiData);
    }

    /**
     * Вычисляет индекс массы тела (ИМТ).
     * Формула: ИМТ = вес (кг) / (рост (м) * рост (м))
     *
     * @param weight Вес человека в килограммах.
     * @param heightInSm Рост человека в сантиметрах.
     * @return Значение индекса массы тела.
     */
    private static double getBodyMassIndex(double weight, double heightInSm) {
        var heightInMeters = heightInSm / 100;
        return weight / (heightInMeters * heightInMeters);
    }

    /**
     * Вычисляет диапазон калорий на основе данных калькулятора.
     *
     * @param calculatorData Данные для расчета калорийности.
     * @return Объект CaloriesBounds, содержащий минимальные, стабильные и максимальные значения калорий.
     */
    private static CaloriesBounds getCaloriesData(CalculatorData calculatorData) {
        var stable = switch (calculatorData.formula()) {
            case HARRIS_BENEDICT -> getCaloriesByHarrisBenedict(calculatorData);
            case MIFFLIN_ST_GEORGE -> getCaloriesByMifflinStGeorge(calculatorData);
        };
        var stableWithCoefficient = stable * calculatorData.physicalActivity().getCoefficient();
        return new CaloriesBounds(stableWithCoefficient * 1.3,
                stableWithCoefficient,
                stableWithCoefficient * 0.7);
    }

    /**
     * Вычисляет количество калорий по формуле Миффлина-Сент-Жоржа.
     * Формула:
     * Для мужчин: BMR = 10 * вес + 6.25 * рост - 5 * возраст + 5
     * Для женщин: BMR = 10 * вес + 6.25 * рост - 5 * возраст - 161
     *
     * @param calculatorData Данные для расчета калорийности.
     * @return Значение базового уровня метаболизма (BMR).
     */
    private static double getCaloriesByMifflinStGeorge(CalculatorData calculatorData) {
        var base = 10 * calculatorData.weight()
                + 6.25 * calculatorData.heightInSm()
                 - 5 * calculatorData.age();
        return switch (calculatorData.sex()) {
            case MALE -> base + 5;
            case FEMALE -> base - 161;
        };
    }

    /**
     * Вычисляет количество калорий по формуле Харриса-Бенедикта.
     *
     * @param calculatorData Данные для расчета калорийности.
     * @return Значение базового уровня метаболизма (BMR).
     */
    private static double getCaloriesByHarrisBenedict(CalculatorData calculatorData) {
        return switch (calculatorData.sex()) {
            case MALE -> getCaloriesByHarrisBenedictMen(calculatorData);
            case FEMALE -> getCaloriesByHarrisBenedictWomen(calculatorData);
        };
    }

    /**
     * Вычисляет количество калорий по формуле Харриса-Бенедикта для мужчин.
     * Формула: BMR = 13.397 * вес + 4.799 * рост - 5.677 * возраст + 88.362
     *
     * @param calculatorData Данные для расчета калорийности.
     * @return Значение базового уровня метаболизма (BMR).
     */
    private static double getCaloriesByHarrisBenedictMen(CalculatorData calculatorData) {
        return 13.397 * calculatorData.weight()
                + 4.799 * calculatorData.heightInSm()
                - 5.677 * calculatorData.age()
                + 88.362;
    }

    /**
     * Вычисляет количество калорий по формуле Харриса-Бенедикта для женщин.
     * Формула: BMR = 9.247 * вес + 3.098 * рост - 4.330 * возраст + 447.593
     *
     * @param calculatorData Данные для расчета калорийности.
     * @return Значение базового уровня метаболизма (BMR).
     */
    private static double getCaloriesByHarrisBenedictWomen(CalculatorData calculatorData) {
        return 9.247 * calculatorData.weight()
                + 3.098 * calculatorData.heightInSm()
                - 4.330 * calculatorData.age()
                + 447.593;
    }

    /**
     * Определяет диапазон индекса массы тела (ИМТ) на основе его значения.
     *
     * @param bodyMassIndex Значение индекса массы тела.
     * @return Диапазон ИМТ, соответствующий переданному значению.
     */
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
