package ru.antoshkaxxr.JavaNaumenProject.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;

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
    @GeneratedValue
    private Long eatenProductId;

    @ManyToOne
    private Product product;

    @Column(nullable = false)
    private Double eatenAmount;

    @Column(nullable = false)
    private LocalDate eatingDate;

    public EatenProduct(Product product, LocalDate eatingDate, Double eatenAmount) {
        this.product = product;
        this.eatingDate = eatingDate;
        this.eatenAmount = eatenAmount;
    }

    public EatenProduct() {

    }

    public Long getEatenProductId() {
        return eatenProductId;
    }

    public void setEatenProductId(Long eatenProductId) {
        this.eatenProductId = eatenProductId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Double getEatenAmount() {
        return eatenAmount;
    }

    public void setEatenAmount(Double eatenAmount) {
        this.eatenAmount = eatenAmount;
    }

    public LocalDate getEatingDate() {
        return eatingDate;
    }

    public void setEatingDate(LocalDate eatingDate) {
        this.eatingDate = eatingDate;
    }
}
