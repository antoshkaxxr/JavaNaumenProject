package ru.antoshkaxxr.JavaNaumenProject.Models;

import java.time.LocalDate;
import java.time.OffsetDateTime;

/**
 * Представляет данные о съеденном продукте.
 */
public class EatenProductData {

    /**
     * Уникальный идентификатор записи о съеденном продукте.
     */
    private Long id;

    /**
     * Дата и время приёма пищи.  Включает информацию о часовом поясе.
     */
    private LocalDate data;

    /**
     * Количество съеденного продукта.
     */
    private Double amount;

    /**
     * Конструктор класса EatenProductData.
     * @param id Уникальный идентификатор записи о съеденном продукте.
     * @param data Дата и время приёма пищи.
     * @param amount Количество съеденного продукта.
     */
    public EatenProductData(Long id, LocalDate data, Double amount) {
        this.id = id;
        this.data = data;
        this.amount = amount;
    }

    /**
     * Пустой конструктор
     */
    public EatenProductData() {
    }
    /**
     * Возвращает уникальный идентификатор записи о съеденном продукте.
     * @return Уникальный идентификатор записи о съеденном продукте.
     */
    public Long getId() {
        return id;
    }

    /**
     * Устанавливает уникальный идентификатор записи о съеденном продукте.
     * @param id Уникальный идентификатор записи о съеденном продукте.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Возвращает дату и время приёма пищи.
     * @return Дата и время приёма пищи.
     */
    public LocalDate getData() {
        return data;
    }

    /**
     * Устанавливает дату и время приёма пищи.
     * @param data Дата и время приёма пищи.
     */
    public void setData(LocalDate data) {
        this.data = data;
    }

    /**
     * Возвращает количество съеденного продукта.
     * @return Количество съеденного продукта.
     */
    public Double getAmount() {
        return amount;
    }

    /**
     * Устанавливает количество съеденного продукта.
     * @param amount Количество съеденного продукта.
     */
    public void setAmount(Double amount) {
        this.amount = amount;
    }

}
