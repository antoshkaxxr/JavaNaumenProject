package ru.antoshkaxxr.JavaNaumenProject;

import jakarta.transaction.Transactional;
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
import ru.antoshkaxxr.JavaNaumenProject.Repositories.FoodDiaryEntryRepository;
import ru.antoshkaxxr.JavaNaumenProject.Repositories.RatingRepository;
import ru.antoshkaxxr.JavaNaumenProject.Service.CustomerServiceImpl;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.Mockito.*;

@SpringBootTest
public class CustomerServiceImplNegativeTest {

    @Mock
    private FoodDiaryEntryRepository foodDiaryEntryRepository;

    @Mock
    private RatingRepository ratingRepository;

    @Mock
    private PlatformTransactionManager transactionManager;

    @Mock
    private TransactionStatus transactionStatus;

    @InjectMocks
    private CustomerServiceImpl customerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @Transactional
    @Rollback
    public void testDeleteByCustomerId_DataAccessException_Rollback() {
        Customer customer1 = new Customer("Lucy", "mililuc@yandex.ru", 70.0, 170.0);
        Product product1 = new Product("apple", 50.0);
        EatenProduct eatenProduct1 = new EatenProduct(product1, LocalDate.of(2024, 10, 12));
        FoodDiaryEntry foodDiaryEntry1 = new FoodDiaryEntry(customer1, eatenProduct1, 2.0);
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