package ru.antoshkaxxr.JavaNaumenProject.Services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.antoshkaxxr.JavaNaumenProject.Entities.Product;
import ru.antoshkaxxr.JavaNaumenProject.Enums.ProductCategory;
import ru.antoshkaxxr.JavaNaumenProject.Repositories.ProductRepository;

/**
 * Реализация сервиса для работы с продуктами пользователя
 */
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    /**
     * Конструктор для инициализации сервиса
     *
     * @param productRepository Репозиторий для работы с продуктами пользователя
     */
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public boolean createProduct(String name, ProductCategory category, Double caloriesNumberHundred) {
        if (productRepository.findByName(name).isPresent()) {
            return false;
        }

        Product product = new Product(name, category, caloriesNumberHundred);
        productRepository.save(product);
        return true;
    }

    @Override
    public boolean deleteProduct(String name) {
        Optional<Product> productOptional = productRepository.findByName(name);
        if (productOptional.isPresent()) {
            productRepository.delete(productOptional.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean updateProduct(String name, Double newCaloriesNumberHundred) {
        Optional<Product> productOptional = productRepository.findByName(name);
        if (productOptional.isPresent()) {
            Product product = productOptional.get();
            product.setCaloriesNumberHundred(newCaloriesNumberHundred);
            productRepository.save(product);
            return true;
        }
        return false;
    }

    @Override
    public List<Product> findAllProducts() {
        return (List<Product>) productRepository.findAll();
    }
}
