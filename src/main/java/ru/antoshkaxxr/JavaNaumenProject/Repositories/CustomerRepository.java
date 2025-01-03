package ru.antoshkaxxr.JavaNaumenProject.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.antoshkaxxr.JavaNaumenProject.Entities.Customer;

/**
 * Репозиторий для работы с сущностью {@link Customer}.
 * Позволяет выполнять основные операции CRUD (Создание, Чтение, Обновление, Удаление)
 * с объектами класса {@link Customer}.
 */
@RepositoryRestResource(path = "customers")
public interface CustomerRepository extends CrudRepository<Customer, Long> {
    /**
     * Находит пользователя по имени.
     *
     * @param name Имя пользователя.
     * @return Объект {@link Customer}, соответствующий указанному имени, или null, если пользователь не найден.
     */
    Customer findByName(String name);

    /**
     * Возвращает общее количество пользователей в базе данных.
     *
     * @return Количество пользователей.
     */
    long count();
}
