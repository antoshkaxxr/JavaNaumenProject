package ru.antoshkaxxr.JavaNaumenProject.Models;

/**
 * Перечисление, представляющее уровни физической активности, их русские названия и коэффициенты.
 */
public enum PhysicalActivity {
    SEDENTARY("Сидячий образ жизни", 1.2),
    LIGHT("Лёгкая активность (упражнения 1–3 раза в неделю)", 1.375),
    MODERATE("Умеренная активность (упражнения 3–5 раз в неделю)", 1.55),
    HIGH("Высокая активность (упражнения 6–7 раз в неделю)", 1.725),
    VERY_HIGH("Очень высокая активность (упражнения каждый день или физическая работа)", 1.9);

    private final String russianName;
    private final double coefficient;

    PhysicalActivity(String russianName, double coefficient) {
        this.russianName = russianName;
        this.coefficient = coefficient;
    }

    /**
     * Возвращает русское название уровня физической активности.
     *
     * @return Русское название уровня физической активности, соответствующее данному значению перечисления.
     */
    public String getRussianName() {
        return russianName;
    }

    /**
     * Возвращает коэффициент, соответствующий уровню физической активности.
     *
     * @return Коэффициент, связанный с данным уровнем физической активности.
     */
    public double getCoefficient() {
        return coefficient;
    }
}

