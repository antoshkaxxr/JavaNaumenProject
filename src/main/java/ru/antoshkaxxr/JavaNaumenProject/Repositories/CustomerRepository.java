package ru.antoshkaxxr.JavaNaumenProject.Repositories;

import org.springframework.data.repository.CrudRepository;
import ru.antoshkaxxr.JavaNaumenProject.Entities.Customer;

/**
 * Репозиторий для работы с сущностью {@link Customer}.
 * Позволяет выполнять основные операции CRUD (Создание, Чтение, Обновление, Удаление)
 * с объектами класса {@link Customer}.
 */
public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
