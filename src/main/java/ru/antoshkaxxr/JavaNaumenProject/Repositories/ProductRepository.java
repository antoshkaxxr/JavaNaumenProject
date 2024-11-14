package ru.antoshkaxxr.JavaNaumenProject.Repositories;

import java.util.Optional;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.antoshkaxxr.JavaNaumenProject.Entities.Product;

/**
 * Репозиторий для работы с сущностью {@link Product}.
 * Позволяет выполнять основные операции CRUD (Создание, Чтение, Обновление, Удаление)
 * с объектами класса {@link Product}.
 */
@RepositoryRestResource(path = "products")
public interface ProductRepository extends CrudRepository<Product, Long> {
    /**
     * Поиск продукта по имени.
     *
     * @param name Название продукта.
     * @return Optional, содержащий продукт, если он найден, иначе пустой Optional.
     */
    Optional<Product> findByName(String name);
}
