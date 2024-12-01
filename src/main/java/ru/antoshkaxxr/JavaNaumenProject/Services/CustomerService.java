package ru.antoshkaxxr.JavaNaumenProject.Services;

import ru.antoshkaxxr.JavaNaumenProject.Entities.Customer;

/**
 * Сервис для работы с пользователями.
 * Предоставляет методы для управления данными о пользователях,
 * включая удаление пользователя по его идентификатору.
 */
public interface CustomerService {
    /**
     * Находит пользователя по имени.
     *
     * @param customerName Имя пользователя.
     * @return Объект {@link Customer}, соответствующий указанному имени, или null, если пользователь не найден.
     */
    Customer findByCustomerName(String customerName);

    /**
     * Добавляет нового пользователя.
     *
     * @param customer Объект {@link Customer}, представляющий пользователя для добавления.
     * @return true, если пользователь успешно добавлен, иначе false.
     */
    boolean addCustomer(Customer customer);

    /**
     * Удаляет пользователя по его идентификатору.
     *
     * @param customerId Идентификатор пользователя.
     */
    void deleteByCustomerId(Long customerId);
}
