package ru.antoshkaxxr.JavaNaumenProject.Service;

/**
 * Сервис для работы с пользователями.
 * Предоставляет методы для управления данными о пользователях,
 * включая удаление пользователя по его идентификатору.
 */
public interface CustomerService {
    /**
     * Удаляет пользователя
     * @param customerId имя пользователя
     */
    void deleteByCustomerId(Long customerId);
}
