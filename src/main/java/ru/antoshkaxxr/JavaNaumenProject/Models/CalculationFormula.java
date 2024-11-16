package ru.antoshkaxxr.JavaNaumenProject.Models;

public enum CalculationFormula {

    MIFFLIN_ST_GEORGE("Миффлина - Сан Жеора"),
    HARRIS_BENEDICT("Харриса-Бенедикта");

    private final String russianName;

    private CalculationFormula(String russianName) {
        this.russianName = russianName;
    }

    public String getRussianName() {
        return russianName;
    }
}
