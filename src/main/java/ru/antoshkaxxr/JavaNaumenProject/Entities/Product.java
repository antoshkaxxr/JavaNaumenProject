package ru.antoshkaxxr.JavaNaumenProject.Entities;

import jakarta.persistence.*;
import ru.antoshkaxxr.JavaNaumenProject.Enums.ProductCategory;

/**
 * Класс-сущность, представляющий продукт.
 * Содержит информацию о названии продукта и количестве ккал на 100 граммов.
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

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductCategory category;

    @Column(nullable = false)
    private Double caloriesNumberHundred;

    /**
     * Конструктор для создания нового продукта с указанными параметрами.
     *
     * @param name Название продукта.
     * @param category Категория продукта.
     * @param caloriesNumberHundred Количество ккал на 100 граммов продукта.
     */
    public Product(String name, ProductCategory category, Double caloriesNumberHundred) {
        this.name = name;
        this.category = category;
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
     * Возвращает категорию продукта.
     *
     * @return Категория продукта.
     */
    public ProductCategory getCategory() {
        return category;
    }

    /**
     * Устанавливает категорию продукта.
     *
     * @param category Категория продукта.
     */
    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    /**
     * Возвращает количество ккал на 100 граммов продукта.
     *
     * @return Количество ккал на 100 граммов продукта.
     */
    public Double getCaloriesNumberHundred() {
        return caloriesNumberHundred;
    }

    /**
     * Устанавливает количество ккал на 100 граммов продукта.
     *
     * @param caloriesNumber Количество ккал на 100 граммов продукта.
     */
    public void setCaloriesNumberHundred(Double caloriesNumber) {
        this.caloriesNumberHundred = caloriesNumber;
    }
}
