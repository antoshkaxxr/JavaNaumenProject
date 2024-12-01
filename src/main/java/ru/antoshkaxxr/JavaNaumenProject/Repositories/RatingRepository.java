package ru.antoshkaxxr.JavaNaumenProject.Repositories;

import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.antoshkaxxr.JavaNaumenProject.Entities.Rating;

/**
 * Репозиторий для работы с сущностью {@link Rating}.
 * Позволяет выполнять основные операции CRUD (Создание, Чтение, Обновление, Удаление)
 * с объектами класса {@link Rating}.
 */
@RepositoryRestResource(path = "ratings")
public interface RatingRepository extends CrudRepository<Rating, Long> {
    /**
     * Находит все оценки пользователей по определенному продукту.
     *
     * @param productId Идентификатор продукта.
     * @return Список объектов {@link Rating}, соответствующих указанному идентификатору продукта.
     */
    @Query("SELECT r FROM Rating r WHERE r.product.id = :productId")
    List<Rating> findByProductId(Long productId);

    /**
     * Находит все оценки определенного пользователя.
     *
     * @param customerId Идентификатор пользователя.
     * @return Список объектов {@link Rating}, соответствующих указанному идентификатору пользователя.
     */
    @Query("SELECT r FROM Rating r WHERE r.customer.id = :customerId")
    List<Rating> findByCustomerId(Long customerId);
}
