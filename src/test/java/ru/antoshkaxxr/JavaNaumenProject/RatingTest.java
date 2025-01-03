package ru.antoshkaxxr.JavaNaumenProject;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import ru.antoshkaxxr.JavaNaumenProject.Entities.Customer;
import ru.antoshkaxxr.JavaNaumenProject.Entities.Product;
import ru.antoshkaxxr.JavaNaumenProject.Entities.Rating;
import ru.antoshkaxxr.JavaNaumenProject.Enums.ProductCategory;
import ru.antoshkaxxr.JavaNaumenProject.Repositories.CustomerRepository;
import ru.antoshkaxxr.JavaNaumenProject.Repositories.ProductRepository;
import ru.antoshkaxxr.JavaNaumenProject.Repositories.RatingRepository;

import java.util.List;

@SpringBootTest
public class RatingTest {
    private final RatingRepository ratingRepository;
    private final ProductRepository productRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public RatingTest(RatingRepository ratingRepository, ProductRepository productRepository, CustomerRepository customerRepository) {
        this.ratingRepository = ratingRepository;
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }

    /**
     * Тестирование поиска всех оценок пользователей по определенному продукту
     */
    @Test
    @Transactional
    @Rollback
    void testFindByProductId() {
        Product product1 = new Product("soup", ProductCategory.SOUPS, 350.0);
        Product product2 = new Product("cheese", ProductCategory.DAIRY, 150.0);
        productRepository.save(product1);
        productRepository.save(product2);

        Customer customer1 = new Customer("Bob");
        Customer customer2 = new Customer("Alice");
        customerRepository.save(customer1);
        customerRepository.save(customer2);

        Rating rating1 = new Rating(customer1, product1, 8, 9, "tasty");
        Rating rating2 = new Rating(customer2, product2, 2, 9, "I hate diary products");
        Rating rating3 = new Rating(customer1, product2, 5, 7, "for an amateur");
        Rating rating4 = new Rating(customer2, product1, 9, 10, "delicious");
        ratingRepository.save(rating1);
        ratingRepository.save(rating2);
        ratingRepository.save(rating3);
        ratingRepository.save(rating4);

        List<Rating> ratings1 = ratingRepository.findByProductId(product1.getId());
        String first1 = ratings1.getFirst().getCustomer().getName();
        String second1 = ratings1.get(1).getCustomer().getName();
        Assertions.assertEquals(2, ratings1.size());
        Assertions.assertEquals(customer1.getName(), first1);
        Assertions.assertEquals(customer2.getName(), second1);

        List<Rating> ratings2 = ratingRepository.findByProductId(product2.getId());
        String first2 = ratings2.getFirst().getCustomer().getName();
        String second2 = ratings2.get(1).getCustomer().getName();
        Assertions.assertEquals(2, ratings2.size());
        Assertions.assertEquals(customer2.getName(), first2);
        Assertions.assertEquals(customer1.getName(), second2);
    }

    /**
     * Тестирование поиска всех оценок определенного пользователя
     */
    @Test
    @Transactional
    @Rollback
    void testFindByCustomerId() {
        Product product1 = new Product("cabbage", ProductCategory.VEGETABLES,150.0);
        Product product2 = new Product("pumpkin", ProductCategory.VEGETABLES, 400.0);
        Product product3 = new Product("coconut oil", ProductCategory.OILS,90.0);
        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);

        Customer customer1 = new Customer("Maria");
        Customer customer2 = new Customer("Tom");
        customerRepository.save(customer1);
        customerRepository.save(customer2);

        Rating rating1 = new Rating(customer2, product2, 1, 1, "very strange thing");
        Rating rating2 = new Rating(customer1, product1, 8, 9, "tasty");
        Rating rating3 = new Rating(customer2, product3, 10, 9, "my favourite");
        ratingRepository.save(rating1);
        ratingRepository.save(rating2);
        ratingRepository.save(rating3);

        List<Rating> ratings1 = ratingRepository.findByCustomerId(customer1.getId());
        Assertions.assertEquals(1, ratings1.size());
        Assertions.assertEquals(product1, ratings1.getFirst().getProduct());

        List<Rating> ratings2 = ratingRepository.findByCustomerId(customer2.getId());
        Assertions.assertEquals(2, ratings2.size());
        Assertions.assertEquals(product2, ratings2.getFirst().getProduct());
        Assertions.assertEquals(product3, ratings2.get(1).getProduct());
    }
}
