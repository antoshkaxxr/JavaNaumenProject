package ru.antoshkaxxr.JavaNaumenProject.Repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import ru.antoshkaxxr.JavaNaumenProject.Entities.Rating;

import java.util.List;

public interface RatingRepository extends CrudRepository<Rating, Long> {
    /**
     * Находит все оценки пользователей по определенному продукту
     * @param productId название продукта
     */
    @Query("SELECT r FROM Rating r WHERE r.product.id = :productId")
    List<Rating> findByProductId(Long productId);

    /**
     * Находит все оценки определенного пользователя
     * @param customerId id пользователя
     */
    @Query("SELECT r FROM Rating r WHERE r.customer.id = :customerId")
    List<Rating> findByCustomerId(Long customerId);
}
