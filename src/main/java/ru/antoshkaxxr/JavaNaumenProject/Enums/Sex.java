package ru.antoshkaxxr.JavaNaumenProject.Enums;

/**
 * Перечисление, представляющее пол (мужской или женский) и их русские обозначения.
 */
public enum Sex {
    MALE("М"),
    FEMALE("Ж");

    private final String russianName;

    Sex(String russianName) {
        this.russianName = russianName;
    }

    /**
     * Возвращает русское название пола.
     *
     * @return Русское название пола, соответствующее данному значению перечисления.
     */
    public String getRussianName() {
        return russianName;
    }
}
