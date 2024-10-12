package ru.antoshkaxxr.JavaNaumenProject.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;

@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false)
    private Double caloriesNumberHundred;

    public Product(String name, Double caloriesNumberHundred) {
        this.name = name;
        this.caloriesNumberHundred = caloriesNumberHundred;
    }

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

    public Double getCaloriesNumber() {
        return caloriesNumberHundred;
    }

    public void setCaloriesNumber(Double caloriesNumber) {
        this.caloriesNumberHundred = caloriesNumber;
    }
}
