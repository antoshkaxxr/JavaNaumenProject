package ru.antoshkaxxr.JavaNaumenProject.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

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

    /**
     * Конструктор для создания новой записи в дневнике питания с указанными параметрами.
     *
     * @param customer     Клиент, который сделал запись.
     * @param eatenProduct Съеденный продукт, который был записан.
     */
    public FoodDiaryEntry(Customer customer, EatenProduct eatenProduct) {
        this.customer = customer;
        this.eatenProduct = eatenProduct;
    }

    /**
     * Конструктор по умолчанию.
     */
    public FoodDiaryEntry() {
    }

    /**
     * Возвращает уникальный идентификатор записи в дневнике питания.
     *
     * @return Уникальный идентификатор записи.
     */
    public Long getRecordId() {
        return recordId;
    }

    /**
     * Устанавливает уникальный идентификатор записи в дневнике питания.
     *
     * @param recordId Уникальный идентификатор записи.
     */
    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    /**
     * Возвращает клиента, который сделал запись в дневнике питания.
     *
     * @return Клиент, который сделал запись.
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Устанавливает клиента, который сделал запись в дневнике питания.
     *
     * @param customer Клиент, который сделал запись.
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Возвращает съеденный продукт, который был записан в дневнике питания.
     *
     * @return Съеденный продукт, который был записан.
     */
    public EatenProduct getEatenProduct() {
        return eatenProduct;
    }

    /**
     * Устанавливает съеденный продукт, который был записан в дневнике питания.
     *
     * @param eatenProduct Съеденный продукт, который был записан.
     */
    public void setEatenProduct(EatenProduct eatenProduct) {
        this.eatenProduct = eatenProduct;
    }
}
