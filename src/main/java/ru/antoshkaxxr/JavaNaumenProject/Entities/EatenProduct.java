package ru.antoshkaxxr.JavaNaumenProject.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.time.LocalDate;

/**
 * Класс-сущность, представляющий продукт, который был съеден клиентом.
 * Содержит информацию о продукте и дате его употребления.
 * Этот класс соответствует таблице "eaten_products" в базе данных.
 */
@Entity
@Table
public class EatenProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "eaten_product_seq")
    @SequenceGenerator(name = "eaten_product_seq", sequenceName = "eaten_product_seq", allocationSize = 1)
    private Long eatenProductId;

    @ManyToOne
    private Product product;

    @Column(nullable = false)
    private Double eatenAmount;

    @Column(nullable = false)
    private LocalDate eatingDate;

    /**
     * Конструктор для создания нового экземпляра съеденного продукта с указанными параметрами.
     *
     * @param product     Продукт, который был съеден.
     * @param eatingDate  Дата, когда продукт был съеден.
     * @param eatenAmount Количество съеденного продукта.
     */
    public EatenProduct(Product product, LocalDate eatingDate, Double eatenAmount) {
        this.product = product;
        this.eatingDate = eatingDate;
        this.eatenAmount = eatenAmount;
    }

    /**
     * Конструктор по умолчанию.
     */
    public EatenProduct() {
    }

    /**
     * Возвращает уникальный идентификатор съеденного продукта.
     *
     * @return Уникальный идентификатор съеденного продукта.
     */
    public Long getEatenProductId() {
        return eatenProductId;
    }

    /**
     * Устанавливает уникальный идентификатор съеденного продукта.
     *
     * @param eatenProductId Уникальный идентификатор съеденного продукта.
     */
    public void setEatenProductId(Long eatenProductId) {
        this.eatenProductId = eatenProductId;
    }

    /**
     * Возвращает продукт, который был съеден.
     *
     * @return Продукт, который был съеден.
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Устанавливает продукт, который был съеден.
     *
     * @param product Продукт, который был съеден.
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * Возвращает количество съеденного продукта.
     *
     * @return Количество съеденного продукта.
     */
    public Double getEatenAmount() {
        return eatenAmount;
    }

    /**
     * Устанавливает количество съеденного продукта.
     *
     * @param eatenAmount Количество съеденного продукта.
     */
    public void setEatenAmount(Double eatenAmount) {
        this.eatenAmount = eatenAmount;
    }

    /**
     * Возвращает дату, когда продукт был съеден.
     *
     * @return Дата, когда продукт был съеден.
     */
    public LocalDate getEatingDate() {
        return eatingDate;
    }

    /**
     * Устанавливает дату, когда продукт был съеден.
     *
     * @param eatingDate Дата, когда продукт был съеден.
     */
    public void setEatingDate(LocalDate eatingDate) {
        this.eatingDate = eatingDate;
    }
}
