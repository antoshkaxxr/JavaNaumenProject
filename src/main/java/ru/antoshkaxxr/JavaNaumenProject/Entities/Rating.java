package ru.antoshkaxxr.JavaNaumenProject.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

/**
 * Класс-сущность, представляющий оценку продукта.
 * Содержит информацию о клиенте, продукте и оценках, выставляемых клиентом.
 * Этот класс соответствует таблице rating в базе данных.
 */
@Entity
@Table
public class Rating {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "rating_seq")
    @SequenceGenerator(name = "rating_seq", sequenceName = "rating_seq", allocationSize = 1)
    private Long recordId;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Product product;

    @Column(nullable = false)
    private Integer tasteAssessment;

    @Column(nullable = false)
    private Integer usefulnessAssessment;

    @Column
    private String annotation;

    /**
     * Конструктор для создания новой оценки с указанными параметрами.
     *
     * @param customer             Клиент, который оставил оценку.
     * @param product              Продукт, который был оценен.
     * @param tasteAssessment      Оценка вкуса продукта.
     * @param usefulnessAssessment Оценка полезности продукта.
     * @param annotation           Примечание к оценке.
     */
    public Rating(Customer customer,
                  Product product,
                  Integer tasteAssessment,
                  Integer usefulnessAssessment,
                  String annotation) {
        this.customer = customer;
        this.product = product;
        this.tasteAssessment = tasteAssessment;
        this.usefulnessAssessment = usefulnessAssessment;
        this.annotation = annotation;
    }

    /**
     * Конструктор по умолчанию.
     */
    public Rating() {
    }

    /**
     * Возвращает уникальный идентификатор записи оценки.
     *
     * @return Уникальный идентификатор записи оценки.
     */
    public Long getRecordId() {
        return recordId;
    }

    /**
     * Устанавливает уникальный идентификатор записи оценки.
     *
     * @param recordId Уникальный идентификатор записи оценки.
     */
    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    /**
     * Возвращает клиента, который оставил оценку.
     *
     * @return Клиент, который оставил оценку.
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Устанавливает клиента, который оставил оценку.
     *
     * @param customer Клиент, который оставил оценку.
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Возвращает продукт, который был оценен.
     *
     * @return Продукт, который был оценен.
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Устанавливает продукт, который был оценен.
     *
     * @param product Продукт, который был оценен.
     */
    public void setProduct(Product product) {
        this.product = product;
    }

    /**
     * Возвращает оценку вкуса продукта.
     *
     * @return Оценка вкуса продукта.
     */
    public Integer getTasteAssessment() {
        return tasteAssessment;
    }

    /**
     * Устанавливает оценку вкуса продукта.
     *
     * @param tasteAssessment Оценка вкуса продукта.
     */
    public void setTasteAssessment(Integer tasteAssessment) {
        this.tasteAssessment = tasteAssessment;
    }

    /**
     * Возвращает оценку полезности продукта.
     *
     * @return Оценка полезности продукта.
     */
    public Integer getUsefulnessAssessment() {
        return usefulnessAssessment;
    }

    /**
     * Устанавливает оценку полезности продукта.
     *
     * @param usefulnessAssessment Оценка полезности продукта.
     */
    public void setUsefulnessAssessment(Integer usefulnessAssessment) {
        this.usefulnessAssessment = usefulnessAssessment;
    }

    /**
     * Возвращает примечание к оценке.
     *
     * @return Примечание к оценке.
     */
    public String getAnnotation() {
        return annotation;
    }

    /**
     * Устанавливает примечание к оценке.
     *
     * @param annotation Примечание к оценке.
     */
    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }
}
