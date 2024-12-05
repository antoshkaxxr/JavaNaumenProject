package ru.antoshkaxxr.JavaNaumenProject.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customer_seq")
    @SequenceGenerator(name = "customer_seq", sequenceName = "customer_seq", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String name;

    private String password;

    private Role role;

    /**
     * Конструктор для создания нового клиента с указанными параметрами.
     *
     * @param name   Имя клиента
     */
    public Customer(String name) {
        this.name = name;
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
