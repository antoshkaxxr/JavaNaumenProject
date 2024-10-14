package ru.antoshkaxxr.JavaNaumenProject.Repositories;

import ru.antoshkaxxr.JavaNaumenProject.Entities.Product;
import org.springframework.data.repository.CrudRepository;

/**
 * Репозиторий для работы с сущностью {@link Product}.
 * Позволяет выполнять основные операции CRUD (Создание, Чтение, Обновление, Удаление)
 * с объектами класса {@link Product}.
 */
public interface ProductRepository extends CrudRepository<Product, Long> {

}
