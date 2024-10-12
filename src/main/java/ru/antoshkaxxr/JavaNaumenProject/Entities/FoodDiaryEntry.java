package ru.antoshkaxxr.JavaNaumenProject.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;

@Entity
@Table(name = "food_diary")
public class FoodDiaryEntry {
    @Id
    @GeneratedValue
    private Long recordId;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private EatenProduct eatenProduct;

    @Column(nullable = false)
    private Double eatenAmount;

    public FoodDiaryEntry(Customer customer, EatenProduct eatenProduct, Double eatenAmount) {
        this.customer = customer;
        this.eatenProduct = eatenProduct;
        this.eatenAmount = eatenAmount;
    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public EatenProduct getEatenProduct() {
        return eatenProduct;
    }

    public void setEatenProduct(EatenProduct eatenProduct) {
        this.eatenProduct = eatenProduct;
    }

    public Double getEatenAmount() {
        return eatenAmount;
    }

    public void setEatenAmount(Double eatenAmount) {
        this.eatenAmount = eatenAmount;
    }
}
