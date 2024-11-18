package ru.antoshkaxxr.JavaNaumenProject.Entities;

import jakarta.persistence.*;
import ru.antoshkaxxr.JavaNaumenProject.Enums.WeightChangeMode;

import java.time.OffsetDateTime;

/**
 * Класс-сущность, представляющий цель по изменению веса.
 * Содержит информацию о пользователе на момент создания клаccа,
 * Содержит данные о том какого веса нужно достичь, когда именно, в каком режиме
 * Этот класс соответствует таблице target в базе данных.
 */

@Entity
@Table
public class Target {
    @Id
    @GeneratedValue
    private Long targetId;

    @Column(nullable = false)
    private Double dailyCaloriesNeeded;

    @Column(nullable = false)
    private OffsetDateTime startDate;

    @Column(nullable = false)
    private OffsetDateTime endDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WeightChangeMode weightChangeMode;

    @ManyToOne
    private Customer customer;

    /**
     * Конструктор для создания новой цели
     *
     * @param dailyCaloriesNeeded Кол-во калорий, которые нужно потреблять в день
     * @param startDate Дата создания цели
     * @param endDate Дата, когда цель должна быть достигнута
     * (нужно, чтобы потом смотреть статистику по съеденным калориям и 
     * как эффективно достигается цель
     * @param weightChangeMode Режим изменения веса
     * @param customer Пользователь, который добавил цель
     */
    public Target(Double dailyCaloriesNeeded, OffsetDateTime startDate,
                  OffsetDateTime endDate, WeightChangeMode weightChangeMode, Customer customer) {
        this.dailyCaloriesNeeded = dailyCaloriesNeeded;
        this.startDate = startDate;
        this.endDate = endDate;
        this.weightChangeMode = weightChangeMode;
        this.customer = customer;
    }

    /**
     * Конструктор по умолчанию.
     */
    public Target() {
        
    }

    /**
     * Возвращает уникальный идентификатор цели.
     *
     * @return Уникальный идентификатор цели.
     */
    public Long getRecordId() {
        return targetId;
    }

    /**
     * Возвращает кол-во калорий, которые нужно потреблять в день
     *
     * @return Кол-во калорий, которые нужно потреблять в день
     */
    public Double getDailyCaloriesNeeded() {
        return dailyCaloriesNeeded;
    }

    /**
     * Устанавливает кол-во калорий, которые нужно потреблять в день
     *
     * @param dailyCaloriesNeeded Кол-во калорий, которые нужно потреблять в день
     */
    public void setDailyCaloriesNeeded(Double dailyCaloriesNeeded) {
        this.dailyCaloriesNeeded = dailyCaloriesNeeded;
    }

    /**
     * Возвращает дату создания цели
     *
     * @return Дата создания цели
     */
    public OffsetDateTime getStartDate() { return startDate; }

    /**
     * Возвращает дату создания цели
     *
     * @param startDate Дата создания цели
     */
    public void setStartDate(OffsetDateTime startDate) {
        this.startDate = startDate;
    }

    /**
     * Возвращает дату, когда цель должна быть достигнута
     *
     * @return Дата, когда цель должна быть достигнута
     */
    public OffsetDateTime setEndDate() { return endDate; }

    /**
     * Возвращает дату, когда цель должна быть достигнута
     *
     * @param endDate Дата, когда цель должна быть достигнута
     */
    public void setEndDate(OffsetDateTime endDate) {
        this.endDate = endDate;
    }

    /**
     * Возвращает режим изменения веса
     *
     * @return Режим изменения веса
     */
    public WeightChangeMode getEndDate() { return weightChangeMode; }

    /**
     * Возвращает режим изменения веса
     *
     * @param weightChangeMode Режим изменения веса
     */
    public void setWeightChangeMode(WeightChangeMode weightChangeMode) {
        this.weightChangeMode = weightChangeMode;
    }

    /**
     * Возвращает пользователя, который добавил цель
     *
     * @return Пользователь, который добавил цель
     */
    public Customer getCustomer() { return customer; }

    /**
     * Возвращает пользователя, который добавил цель
     *
     * @param customer Пользователь, который добавил цель
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

}
