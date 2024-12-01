package ru.antoshkaxxr.JavaNaumenProject.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.antoshkaxxr.JavaNaumenProject.Entities.Rating;
import ru.antoshkaxxr.JavaNaumenProject.Repositories.RatingRepository;

/**
 * Контроллер для работы с оценками продуктов.
 */
@RestController
@RequestMapping("ratings")
public class RatingController {
    private final RatingRepository ratingRepository;

    /**
     * Конструктор для внедрения зависимости RatingRepository.
     *
     * @param ratingRepository Репозиторий для работы с оценками
     */
    @Autowired
    public RatingController(RatingRepository ratingRepository) {
        this.ratingRepository = ratingRepository;
    }

    /**
     * Обрабатывает GET-запрос для поиска оценок по идентификатору продукта.
     *
     * @param productId Идентификатор продукта
     * @return Список оценок для указанного продукта
     */
    @GetMapping("/findByProductId")
    public List<Rating> findByProductId(@RequestParam Long productId) {
        return ratingRepository.findByProductId(productId);
    }

    /**
     * Обрабатывает GET-запрос для поиска оценок по идентификатору пользователя.
     *
     * @param customerId Идентификатор пользователя
     * @return Список оценок для указанного пользователя
     */
    @GetMapping("/findByCustomerId")
    public List<Rating> findByCustomerId(@RequestParam Long customerId) {
        return ratingRepository.findByCustomerId(customerId);
    }
}
