package ru.antoshkaxxr.JavaNaumenProject.Entities;

import jakarta.persistence.*;
import ru.antoshkaxxr.JavaNaumenProject.Enums.WeightChangeMode;

import java.time.LocalDate;

/**
 * Класс-сущность, представляющий цель по изменению веса.
 * Содержит информацию о пользователе на момент создания клаccа,
 * Содержит данные о том какого веса нужно достичь, когда именно, в каком режиме
 * Этот класс соответствует таблице Goal в базе данных.
 */

@Entity
@Table
public class Goal {
    @Id
    @GeneratedValue
    private Long goalId;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Double caloriesNeedChange;

    @Column(nullable = false)
    private Double caloriesChangeToPlanPerDay;

    @Column(nullable = false)
    private Double caloriesStablePerDay;

    @Column(nullable = false)
    private LocalDate startDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private WeightChangeMode weightChangeMode;

    @ManyToOne
    private Customer customer;

    /**
     * Конструктор для создания новой цели
     *
     * @param name Название цели
     * @param caloriesNeedChange Кол-во калорий, которое нужно сжечь, чтобы прийти к цели
     * @param startDate Дата создания цели
     * @param caloriesChangeToPlanPerDay  Кол-во калорий, которое будут сжигаться/набираться в день
     * @param caloriesStablePerDay  Кол-во калорий, необходимое, чтобы вес не менялся
     * @param weightChangeMode Режим изменения веса
     * @param customer Пользователь, который добавил цель
     */
    public Goal(String name, Double caloriesNeedChange, LocalDate startDate, Double caloriesChangeToPlanPerDay,
                Double caloriesStablePerDay, WeightChangeMode weightChangeMode, Customer customer) {
        this.name = name;
        this.caloriesNeedChange = caloriesNeedChange;
        this.startDate = startDate;
        this.caloriesChangeToPlanPerDay = caloriesChangeToPlanPerDay;
        this.caloriesStablePerDay = caloriesStablePerDay;
        this.weightChangeMode = weightChangeMode;
        this.customer = customer;
    }

    /**
     * Конструктор по умолчанию.
     */
    public Goal() {
        
    }

    /**
     * Возвращает уникальный идентификатор цели.
     *
     * @return Уникальный идентификатор цели.
     */
    public Long getGoalId() {
        return goalId;
    }

    /**
     * Возвращает название цели.
     * @return Название цели (String). Не может быть null.
     */
    public String getName() {
        return name;
    }

    /**
     * Устанавливает название цели.
     * @param name Новое название цели. Не может быть null или пустым.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Возвращает кол-во калорий, которое нужно сжечь, чтобы прийти к цели
     *
     * @return Кол-во калорий, которое нужно сжечь, чтобы прийти к цели
     */
    public Double getCaloriesNeedChange() {
        return caloriesNeedChange;
    }

    /**
     * Устанавливает кол-во калорий, которое нужно сжечь, чтобы прийти к цели
     *
     * @param caloriesNeedChange Кол-во калорий, которое нужно сжечь, чтобы прийти к цели
     */
    public void setCaloriesNeedChange(Double caloriesNeedChange) {
        this.caloriesNeedChange = caloriesNeedChange;
    }

    /**
     * Возвращает дату создания цели
     *
     * @return Дата создания цели
     */
    public LocalDate getStartDate() { return startDate; }

    /**
     * Возвращает дату создания цели
     *
     * @param startDate Дата создания цели
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * Возвращает кол-во калорий, которое будут сжигаться/набираться в день
     *
     * @return Кол-во калорий, которое будут сжигаться/набираться в день
     */
    public Double getCaloriesChangeToPlanPerDay() { return caloriesChangeToPlanPerDay; }

    /**
     * Возвращает кол-во калорий, которое будут сжигаться/набираться в день
     *
     * @param caloriesChangeToPlanPerDay Кол-во калорий, которое будут сжигаться/набираться в день
     */
    public void setCaloriesChangeToPlanPerDay(Double caloriesChangeToPlanPerDay) {
        this.caloriesChangeToPlanPerDay = caloriesChangeToPlanPerDay;
    }

    /**
     * Возвращает кол-во калорий, необходимое, чтобы вес не менялся
     *
     * @return Кол-во калорий, необходимое, чтобы вес не менялся
     */
    public Double getCaloriesStablePerDay() { return caloriesStablePerDay; }

    /**
     * Возвращает кол-во калорий, необходимое, чтобы вес не менялся
     *
     * @param caloriesStablePerDay Кол-во калорий, необходимое, чтобы вес не менялся
     */
    public void setCaloriesStablePerDay(Double caloriesStablePerDay) {
        this.caloriesStablePerDay = caloriesStablePerDay;
    }

    /**
     * Возвращает режим изменения веса
     *
     * @return Режим изменения веса
     */
    public WeightChangeMode getWeightChangeMode() { return weightChangeMode; }

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
