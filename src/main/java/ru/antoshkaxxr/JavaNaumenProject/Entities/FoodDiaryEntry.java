package ru.antoshkaxxr.JavaNaumenProject.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.ManyToOne;

/**
 * Класс-сущность, представляющий запись в дневнике питания.
 * Содержит информацию о клиенте, съеденном продукте и количестве съеденного.
 * Этот класс соответствует таблице "food_diary" в базе данных.
 */
@Entity
@Table
public class FoodDiaryEntry {
    @Id
    @GeneratedValue
    private Long recordId;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private EatenProduct eatenProduct;

    public FoodDiaryEntry(Customer customer, EatenProduct eatenProduct) {
        this.customer = customer;
        this.eatenProduct = eatenProduct;
    }

    public FoodDiaryEntry() {

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
}
