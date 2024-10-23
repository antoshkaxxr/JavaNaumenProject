package ru.antoshkaxxr.JavaNaumenProject.Service;

import ru.antoshkaxxr.JavaNaumenProject.Entities.Customer;

/**
 * Сервис для работы с пользователями.
 * Предоставляет методы для управления данными о пользователях,
 * включая удаление пользователя по его идентификатору.
 */
public interface CustomerService {
    /**
     * Находит пользователя по имени
     * @param customerName имя пользователя
     */
    Customer findByCustomerName(String customerName);

    /**
     * Добавляет пользователя
     * @param customer пользователь
     */
    boolean addCustomer(Customer customer);

    /**
     * Удаляет пользователя
     * @param customerId имя пользователя
     */
    void deleteByCustomerId(Long customerId);
}
