package ru.antoshkaxxr.JavaNaumenProject;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import ru.antoshkaxxr.JavaNaumenProject.Entities.*;
import ru.antoshkaxxr.JavaNaumenProject.Repositories.*;
import ru.antoshkaxxr.JavaNaumenProject.Service.CustomerServiceImpl;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
public class CustomerServiceImplPositiveTest {
    private final CustomerServiceImpl customerService;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final EatenProductRepository eatenProductRepository;
    private final FoodDiaryEntryRepository foodDiaryEntryRepository;
    private final RatingRepository ratingRepository;

    @Autowired
    public CustomerServiceImplPositiveTest(CustomerServiceImpl customerService,
                                           CustomerRepository customerRepository, ProductRepository productRepository, EatenProductRepository eatenProductRepository,
                                           FoodDiaryEntryRepository foodDiaryEntryRepository,
                                           RatingRepository ratingRepository) {
        this.customerService = customerService;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.eatenProductRepository = eatenProductRepository;
        this.foodDiaryEntryRepository = foodDiaryEntryRepository;
        this.ratingRepository = ratingRepository;
    }

    @Test
    @Transactional
    @Rollback
    void testDeleteByCustomerId() {
        Customer customer1 = new Customer("Lucy", "mililuc@yandex.ru", 70.0, 170.0);
        customerRepository.save(customer1);

        Product product1 = new Product("apple", 50.0);
        productRepository.save(product1);

        EatenProduct eatenProduct1 = new EatenProduct(product1, LocalDate.of(2024, 10, 12));
        eatenProductRepository.save(eatenProduct1);

        FoodDiaryEntry foodDiaryEntry1 = new FoodDiaryEntry(customer1, eatenProduct1, 2.0);
        foodDiaryEntryRepository.save(foodDiaryEntry1);

        Rating rating1 = new Rating(customer1, product1, 8, 9, "tasty");
        ratingRepository.save(rating1);

        customerService.deleteByCustomerId(customer1.getId());

        Optional<Customer> foundCustomer = customerRepository.findById(customer1.getId());
        Assertions.assertTrue(foundCustomer.isEmpty());

        Optional<FoodDiaryEntry> foundFoodDiaryEntry = foodDiaryEntryRepository.findById(foodDiaryEntry1.getRecordId());
        Assertions.assertTrue(foundFoodDiaryEntry.isEmpty());

        Optional<Rating> foundRating = ratingRepository.findById(rating1.getRecordId());
        Assertions.assertTrue(foundRating.isEmpty());
    }
}
