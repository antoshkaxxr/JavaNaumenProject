package ru.antoshkaxxr.JavaNaumenProject.Services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.antoshkaxxr.JavaNaumenProject.Entities.Customer;
import ru.antoshkaxxr.JavaNaumenProject.Entities.Product;
import ru.antoshkaxxr.JavaNaumenProject.Enums.ProductCategory;
import ru.antoshkaxxr.JavaNaumenProject.Repositories.ProductRepository;

/**
 * Реализация сервиса для работы с продуктами пользователя
 */
@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CustomerServiceImpl customerService;

    /**
     * Конструктор для инициализации сервиса
     *
     * @param productRepository Репозиторий для работы с продуктами пользователя
     * @param customerService Сервис для работы с пользователями
     */
    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, CustomerServiceImpl customerService) {
        this.productRepository = productRepository;
        this.customerService = customerService;
    }

    /**
     * Создаёт в базе данных объект продукта на основе введённых данных
     *
     * @param name название продукта
     * @param category категория продукта
     * @param caloriesNumberHundred количество калорий продукта на 100г
     * @param username имя пользователя.
     * @return был ли создан продукт в базе данных
     */
    @Override
    public boolean createProduct(String name, ProductCategory category, Double caloriesNumberHundred, String username) {
        Customer customer = customerService.findByCustomerName(username);
        if (customer == null) {
            throw new RuntimeException("User not found");
        }

        Optional<Product> existingProduct = productRepository.findByNameAndCustomer(name, customer);
        if (existingProduct.isPresent()) {
            return false;
        }

        Product product = new Product(name, category, caloriesNumberHundred, customer);
        productRepository.save(product);
        return true;
    }

    @Override
    public boolean deleteProduct(String name, String username) {
        Customer customer = customerService.findByCustomerName(username);
        if (customer == null) {
            return false;
        }

        Optional<Product> productOptional = productRepository.findByNameAndCustomer(name, customer);
        if (productOptional.isEmpty()) {
            return false;
        }

        productRepository.delete(productOptional.get());
        return true;
    }

    @Override
    public boolean updateProduct(String name, Double newCaloriesNumberHundred, String username) {
        Customer customer = customerService.findByCustomerName(username);
        if (customer == null) {
            return false;
        }

        Optional<Product> productOptional = productRepository.findByNameAndCustomer(name, customer);
        if (productOptional.isEmpty()) {
            return false;
        }

        Product product = productOptional.get();
        product.setCaloriesNumberHundred(newCaloriesNumberHundred);
        productRepository.save(product);
        return true;
    }

    /**
     * Возвращает продукты, созданные пользователем с введённым именем
     *
     * @param username имя пользователя.
     * @return продукты, созданные пользователем с введённым именем
     */
    @Override
    public List<Product> findAllProducts(String username) {
        Customer customer = customerService.findByCustomerName(username);
        if (customer == null) {
            return List.of();
        }

        return productRepository.findByCustomer(customer);
    }

    /**
     * Возвращает продукт из базы данных по id
     *
     * @param productId id продукта.
     * @return продукт
     */
    public Product getProducts(Long productId) {
        var resultFind = productRepository.findById(productId);
        if (resultFind.isPresent()) {
            return resultFind.get();
        } else {
            throw new RuntimeException("Product not found");
        }

    }
}
