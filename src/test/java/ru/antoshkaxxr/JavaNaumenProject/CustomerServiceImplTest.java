package ru.antoshkaxxr.JavaNaumenProject;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataAccessException;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import ru.antoshkaxxr.JavaNaumenProject.Entities.*;
import ru.antoshkaxxr.JavaNaumenProject.Repositories.*;
import ru.antoshkaxxr.JavaNaumenProject.Service.CustomerServiceImpl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
public class CustomerServiceImplTest {

    @Mock
    private FoodDiaryEntryRepository foodDiaryEntryRepository;

    @Mock
    private RatingRepository ratingRepository;

    @Mock
    private PlatformTransactionManager transactionManager;

    @Mock
    private TransactionStatus transactionStatus;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private ProductRepository productRepository;

    @Mock
    private EatenProductRepository eatenProductRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @Transactional
    @Rollback
    void testDeleteByCustomerIdTransactionSuccess() {
        Customer customer1 = new Customer("Lucy", "mililuc@yandex.ru", 70.0, 170.0);
        customerRepository.save(customer1);

        Product product1 = new Product("apple", 50.0);
        productRepository.save(product1);

        EatenProduct eatenProduct1 = new EatenProduct(product1, LocalDate.of(2024, 10, 12), 2.0);
        eatenProductRepository.save(eatenProduct1);

        FoodDiaryEntry foodDiaryEntry1 = new FoodDiaryEntry(customer1, eatenProduct1);
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

    @Test
    @Transactional
    @Rollback
    public void testDeleteByCustomerIdTransactionFailure() {
        Customer customer1 = new Customer("Lucy", "mililuc@yandex.ru", 70.0, 170.0);
        Product product1 = new Product("apple", 50.0);
        EatenProduct eatenProduct1 = new EatenProduct(product1, LocalDate.of(2024, 10, 12), 2.0);
        FoodDiaryEntry foodDiaryEntry1 = new FoodDiaryEntry(customer1, eatenProduct1);
        Rating rating1 = new Rating(customer1, product1, 8, 9, "tasty");

        Long customerId = 1L;
        List<FoodDiaryEntry> entries = List.of(foodDiaryEntry1);
        List<Rating> ratings = List.of(rating1);

        when(foodDiaryEntryRepository.findByCustomerId(customerId)).thenReturn(entries);
        when(ratingRepository.findByCustomerId(customerId)).thenReturn(ratings);
        when(transactionManager.getTransaction(any())).thenReturn(transactionStatus);

        doThrow(new DataAccessException("Simulated DataAccessException") {}).when(foodDiaryEntryRepository).deleteAll(entries);

        try {
            customerService.deleteByCustomerId(customerId);
        } catch (DataAccessException ex) {
            verify(transactionManager, times(1)).rollback(transactionStatus);
            verify(transactionManager, never()).commit(transactionStatus);
        }
    }
}
