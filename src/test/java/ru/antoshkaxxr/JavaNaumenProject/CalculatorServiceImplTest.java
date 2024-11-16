package ru.antoshkaxxr.JavaNaumenProject;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import ru.antoshkaxxr.JavaNaumenProject.Models.*;
import ru.antoshkaxxr.JavaNaumenProject.Services.CalculatorServiceImpl;

public class CalculatorServiceImplTest {

    @Test
    void calculateBMIAndCaloriesBounds() {
        var calcData = new CalculatorData(20, Sex.MALE, 80, 180,
        PhysicalActivity.LIGHT, CalculationFormula.MIFFLIN_ST_GEORGE);
        var res = CalculatorServiceImpl.getCalculatedData(calcData);
        assertTrue(res.bodyMassIndex().bmi() >= 20 && res.bodyMassIndex().bmi() <= 25);
        assertEquals(BMIRange.NORMAL, res.bodyMassIndex().range());
        assertTrue(res.bounds().less() >= 1500 && res.bounds().less() <= 2500);
        assertTrue(res.bounds().stable() >= 2000 && res.bounds().stable() <= 3000);
        assertTrue(res.bounds().more() >= 2500 && res.bounds().more() <= 4000);
    }
}

