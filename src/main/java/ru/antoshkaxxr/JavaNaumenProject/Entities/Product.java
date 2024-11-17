package ru.antoshkaxxr.JavaNaumenProject.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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

    @ManyToOne
    private Customer customer;

    /**
     * Конструктор для создания нового продукта с указанными параметрами.
     *
     * @param name Название продукта.
     * @param category Категория продукта.
     * @param caloriesNumberHundred Количество ккал на 100 граммов продукта.
     * @param customer Пользователь, который добавил продукт.
     */
    public Product(String name, ProductCategory category,
                   Double caloriesNumberHundred, Customer customer) {
        this.name = name;
        this.category = category;
        this.caloriesNumberHundred = caloriesNumberHundred;
        this.customer = customer;
    }

    /**
     * Конструктор для создания продукта без привязки к пользователю.
     *
     * @param name Название продукта.
     * @param category Категория продукта.
     * @param caloriesNumberHundred Количество ккал на 100 граммов продукта.
     */
    public Product(String name, ProductCategory category, Double caloriesNumberHundred) {
        this(name, category, caloriesNumberHundred, null);
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

    /**
     * Возвращает пользователя, который добавил продукт.
     *
     * @return Пользователь, добавивший продукт.
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Устанавливает пользователя, который добавил продукт.
     *
     * @param customer Пользователь, добавивший продукт.
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
