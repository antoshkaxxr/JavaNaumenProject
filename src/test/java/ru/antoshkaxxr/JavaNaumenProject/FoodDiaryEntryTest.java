package ru.antoshkaxxr.JavaNaumenProject;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import ru.antoshkaxxr.JavaNaumenProject.CriteriaApi.FoodDiaryEntryRepositoryImpl;
import ru.antoshkaxxr.JavaNaumenProject.Entities.Customer;
import ru.antoshkaxxr.JavaNaumenProject.Entities.EatenProduct;
import ru.antoshkaxxr.JavaNaumenProject.Entities.FoodDiaryEntry;
import ru.antoshkaxxr.JavaNaumenProject.Entities.Product;
import ru.antoshkaxxr.JavaNaumenProject.Repositories.CustomerRepository;
import ru.antoshkaxxr.JavaNaumenProject.Repositories.EatenProductRepository;
import ru.antoshkaxxr.JavaNaumenProject.Repositories.FoodDiaryEntryRepository;
import ru.antoshkaxxr.JavaNaumenProject.Repositories.ProductRepository;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class FoodDiaryEntryTest {
    private final FoodDiaryEntryRepositoryImpl foodDiaryEntryRepositoryImpl;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final EatenProductRepository eatenProductRepository;
    private final FoodDiaryEntryRepository foodDiaryEntryRepository;

    @Autowired
    public FoodDiaryEntryTest(FoodDiaryEntryRepositoryImpl foodDiaryEntryRepositoryImpl, CustomerRepository customerRepository, ProductRepository productRepository, EatenProductRepository eatenProductRepository, FoodDiaryEntryRepository foodDiaryEntryRepository) {
        this.foodDiaryEntryRepositoryImpl = foodDiaryEntryRepositoryImpl;
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.eatenProductRepository = eatenProductRepository;
        this.foodDiaryEntryRepository = foodDiaryEntryRepository;
    }

    /**
     * Тестирование поиска всех записей из дневника питания определенного пользователя
     */
    @Test
    @Transactional
    @Rollback
    void testFindByCustomerId() {
        Customer customer1 = new Customer("Lucy", "mililuc@yandex.ru", 70.0, 170.0);
        Customer customer2 = new Customer("Jack", "maxiluc@yandex.ru", 80.0, 179.0);
        customerRepository.save(customer1);
        customerRepository.save(customer2);

        Product product1 = new Product("apple", 50.0);
        productRepository.save(product1);

        EatenProduct eatenProduct1 = new EatenProduct(product1, LocalDate.of(2024, 10, 12), 2.0);
        EatenProduct eatenProduct2 = new EatenProduct(product1, LocalDate.of(2024, 10, 13), 1.0);
        eatenProductRepository.save(eatenProduct1);
        eatenProductRepository.save(eatenProduct2);

        FoodDiaryEntry foodDiaryEntry1 = new FoodDiaryEntry(customer1, eatenProduct1);
        FoodDiaryEntry foodDiaryEntry2 = new FoodDiaryEntry(customer2, eatenProduct2);
        foodDiaryEntryRepository.save(foodDiaryEntry1);
        foodDiaryEntryRepository.save(foodDiaryEntry2);

        List<FoodDiaryEntry> foodDiaryEntries1 = foodDiaryEntryRepositoryImpl.findByCustomerId(customer1.getId());
        List<FoodDiaryEntry> foodDiaryEntries2 = foodDiaryEntryRepositoryImpl.findByCustomerId(customer2.getId());

        String result1 = foodDiaryEntries1.getFirst().getCustomer().getName();
        String result2 = foodDiaryEntries2.getFirst().getCustomer().getName();
        Assertions.assertEquals(1, foodDiaryEntries1.size());
        Assertions.assertEquals(1, foodDiaryEntries2.size());
        Assertions.assertEquals(customer1.getName(), result1);
        Assertions.assertEquals(customer2.getName(), result2);
    }

    /**
     * Тестирование поиска записей из дневника питания за определенную дату
     */
    @Test
    @Transactional
    @Rollback
    void testFindByEatingDate() {
        Customer customer1 = new Customer("Lucy", "mililuc@yandex.ru", 70.0, 170.0);
        customerRepository.save(customer1);

        Product product1 = new Product("apple", 50.0);
        productRepository.save(product1);

        EatenProduct eatenProduct1 = new EatenProduct(product1, LocalDate.of(2024, 10, 12), 2.0);
        EatenProduct eatenProduct2 = new EatenProduct(product1, LocalDate.of(2024, 10, 13), 1.0);
        EatenProduct eatenProduct3 = new EatenProduct(product1, LocalDate.of(2024, 10, 13), 4.0);
        eatenProductRepository.save(eatenProduct1);
        eatenProductRepository.save(eatenProduct2);
        eatenProductRepository.save(eatenProduct3);

        FoodDiaryEntry foodDiaryEntry1 = new FoodDiaryEntry(customer1, eatenProduct1);
        FoodDiaryEntry foodDiaryEntry2 = new FoodDiaryEntry(customer1, eatenProduct2);
        FoodDiaryEntry foodDiaryEntry3 = new FoodDiaryEntry(customer1, eatenProduct3);
        foodDiaryEntryRepository.save(foodDiaryEntry1);
        foodDiaryEntryRepository.save(foodDiaryEntry2);
        foodDiaryEntryRepository.save(foodDiaryEntry3);

        List<FoodDiaryEntry> foodDiaryEntries = foodDiaryEntryRepositoryImpl.findByEatingDate(LocalDate.of(2024, 10, 13));
        Double result2 = foodDiaryEntries.get(1).getEatenProduct().getEatenAmount();
        Assertions.assertEquals(2, foodDiaryEntries.size());
        Assertions.assertEquals(4.0, result2);
    }
}
