package ru.antoshkaxxr.JavaNaumenProject.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;

import java.time.LocalDate;

@Entity
@Table(name = "eaten_products")
public class EatenProduct {
    @Id
    @GeneratedValue
    private Long eatenProductId;

    @ManyToOne
    private Product product;

    @Column(nullable = false)
    private LocalDate eatingDate;

    public EatenProduct(Product product, LocalDate eatingDate) {
        this.product = product;
        this.eatingDate = eatingDate;
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

    public LocalDate getEatingDate() {
        return eatingDate;
    }

    public void setEatingDate(LocalDate eatingDate) {
        this.eatingDate = eatingDate;
    }
}
