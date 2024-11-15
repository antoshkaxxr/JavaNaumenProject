package ru.antoshkaxxr.JavaNaumenProject.Dto;

public enum Sex {
    MALE("М"),
    FEMALE("Ж");

    private final String russianName;

    private Sex(String russianName) {
        this.russianName = russianName;
    }

    public String getRussianName() {
        return russianName;
    }
}
