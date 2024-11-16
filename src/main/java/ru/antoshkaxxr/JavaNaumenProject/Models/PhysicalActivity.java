package ru.antoshkaxxr.JavaNaumenProject.Models;

public enum PhysicalActivity {
    SEDENTARY("Сидячий образ жизни", 1.2),
    LIGHT("Лёгкая активность (упражнения 1–3 раза в неделю)", 1.375),
    MODERATE("Умеренная активность (упражнения 3–5 раз в неделю)", 1.55),
    HIGH("Высокая активность (упражнения 6–7 раз в неделю)", 1.725),
    VERY_HIGH("Очень высокая активность (упражнения каждый день или физическая работа)", 1.9);

    private final String russianName;
    private final double coefficient;

    private PhysicalActivity(String russianName, double coefficient) {
        this.russianName = russianName;
        this.coefficient = coefficient;
    }

    public String getRussianName() {
        return russianName;
    }

    public double getCoefficient() {
        return coefficient;
    }
}

