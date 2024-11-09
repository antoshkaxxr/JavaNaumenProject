package ru.antoshkaxxr.JavaNaumenProject.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Класс-сущность, представляющий продукт.
 * Содержит информацию о названии продукта и количестве калорий на 100 грамм.
 * Этот класс соответствует таблице products в базе данных.
 */
@Entity
@Table
public class Product {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double caloriesNumberHundred;

    /**
     * Конструктор для создания нового продукта с указанными параметрами.
     *
     * @param name Название продукта.
     * @param caloriesNumberHundred Количество калорий на 100 граммов продукта.
     */
    public Product(String name, Double caloriesNumberHundred) {
        this.name = name;
        this.caloriesNumberHundred = caloriesNumberHundred;
    }

    /**
     * Конструктор по умолчанию.
     */
    public Product() {
    }

    /**
     * Возвращает уникальный идентификатор продукта.
     *
     * @return Уникальный идентификатор продукта.
     */
    public Long getId() {
        return id;
    }

    /**
     * Устанавливает уникальный идентификатор продукта.
     *
     * @param id Уникальный идентификатор продукта.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Возвращает название продукта.
     *
     * @return Название продукта.
     */
    public String getName() {
        return name;
    }

    /**
     * Устанавливает название продукта.
     *
     * @param name Название продукта.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Возвращает количество калорий на 100 граммов продукта.
     *
     * @return Количество калорий на 100 граммов продукта.
     */
    public Double getCaloriesNumberHundred() {
        return caloriesNumberHundred;
    }

    /**
     * Устанавливает количество калорий на 100 граммов продукта.
     *
     * @param caloriesNumber Количество калорий на 100 граммов продукта.
     */
    public void setCaloriesNumberHundred(Double caloriesNumber) {
        this.caloriesNumberHundred = caloriesNumber;
    }
}
