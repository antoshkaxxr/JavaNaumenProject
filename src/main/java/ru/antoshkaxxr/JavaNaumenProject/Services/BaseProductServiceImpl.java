package ru.antoshkaxxr.JavaNaumenProject.Services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.antoshkaxxr.JavaNaumenProject.Entities.Product;
import ru.antoshkaxxr.JavaNaumenProject.Repositories.ProductRepository;

/**
 * Реализация сервиса для работы с базовыми продуктами
 */
@Service
public class BaseProductServiceImpl {

    private final ProductRepository productRepository;

    /**
     * Конструктор для инициализации сервиса
     *
     * @param productRepository Репозиторий для работы с продуктами
     */
    @Autowired
    public BaseProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Возвращает список всех базовых продуктов
     *
     * @return Список базовых продуктов
     */
    public List<Product> getAllBaseProducts() {
        return productRepository.findByCustomerIsNull();
    }
}
