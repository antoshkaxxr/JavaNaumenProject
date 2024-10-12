package ru.antoshkaxxr.JavaNaumenProject.Service;

public interface CustomerService {
    /**
     * Удаляет пользователя
     * @param customerId имя пользователя
     */
    void deleteByCustomerId(Long customerId);
}
