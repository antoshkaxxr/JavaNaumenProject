package ru.antoshkaxxr.JavaNaumenProject.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import ru.antoshkaxxr.JavaNaumenProject.Enums.Role;

/**
 * Класс-сущность, представляющий клиента в приложении.
 * Содержит информацию об имени, электронной почте, весе и росте клиента.
 */
@Entity
@Table
public class Customer {
    @Id
    @GeneratedValue
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private Double weight;

    @Column(nullable = false)
    private Double height;

    private String password;

    private Role role;

    /**
     * Конструктор для создания нового клиента с указанными параметрами.
     *
     * @param name   Имя клиента
     * @param email  Электронная почта клиента
     * @param weight Вес клиента
     * @param height Рост клиента
     */
    public Customer(String name, String email, Double weight, Double height) {
        this.name = name;
        this.email = email;
        this.weight = weight;
        this.height = height;
    }

    /**
     * Конструктор по умолчанию.
     */
    public Customer() {

    }

    /**
     * Возвращает уникальный идентификатор клиента.
     *
     * @return Уникальный идентификатор клиента.
     */
    public Long getId() {
        return id;
    }

    /**
     * Устанавливает уникальный идентификатор клиента.
     *
     * @param id Уникальный идентификатор клиента.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Возвращает имя клиента.
     *
     * @return Имя клиента.
     */
    public String getName() {
        return name;
    }

    /**
     * Устанавливает имя клиента.
     *
     * @param name Имя клиента.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Возвращает электронную почту клиента.
     *
     * @return Электронная почта клиента.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Устанавливает электронную почту клиента.
     *
     * @param email Электронная почта клиента.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Возвращает вес клиента.
     *
     * @return Вес клиента.
     */
    public Double getWeight() {
        return weight;
    }

    /**
     * Устанавливает вес клиента.
     *
     * @param weight Вес клиента.
     */
    public void setWeight(Double weight) {
        this.weight = weight;
    }

    /**
     * Возвращает рост клиента.
     *
     * @return Рост клиента.
     */
    public Double getHeight() {
        return height;
    }

    /**
     * Устанавливает рост клиента.
     *
     * @param height Рост клиента.
     */
    public void setHeight(Double height) {
        this.height = height;
    }

    /**
     * Возвращает пароль клиента.
     *
     * @return Пароль клиента.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Устанавливает пароль клиента.
     *
     * @param password Пароль клиента.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Возвращает роль клиента в системе.
     *
     * @return Роль клиента.
     */
    public Role getRole() {
        return role;
    }

    /**
     * Устанавливает роль клиента в системе.
     *
     * @param role Роль клиента.
     */
    public void setRole(Role role) {
        this.role = role;
    }
}
