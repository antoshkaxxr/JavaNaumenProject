package ru.antoshkaxxr.JavaNaumenProject.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;

/**
 * Класс-сущность, представляющий клиента в приложении.
 * Содержит информацию об имени, электронной почте, весе и росте клиента.
 * Этот класс соответствует таблице "customers" в базе данных.
 */
@Entity
@Table
public class Customer {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Double weight;

    @Column(nullable = false)
    private Double height;

    public Customer(String name, String email, Double weight, Double height) {
        this.name = name;
        this.email = email;
        this.weight = weight;
        this.height = height;
    }

    public Customer() {

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }
}
