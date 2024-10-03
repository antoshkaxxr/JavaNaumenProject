package ru.antoshkaxxr.task3.Entity;

public class Product {
    private Long id;
    private String name;
    private Integer caloriesNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCaloriesNumber() {
        return caloriesNumber;
    }

    public void setCaloriesNumber(Integer caloriesNumber) {
        this.caloriesNumber = caloriesNumber;
    }
}
