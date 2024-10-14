package ru.antoshkaxxr.JavaNaumenProject.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import ru.antoshkaxxr.JavaNaumenProject.Entities.FoodDiaryEntry;
import ru.antoshkaxxr.JavaNaumenProject.Entities.Rating;
import ru.antoshkaxxr.JavaNaumenProject.Repositories.CustomerRepository;
import ru.antoshkaxxr.JavaNaumenProject.Repositories.FoodDiaryEntryRepository;
import ru.antoshkaxxr.JavaNaumenProject.Repositories.RatingRepository;

import java.util.List;

/**
 * Реализация сервиса для работы с пользователями.
 * Обрабатывает операции, связанные с управлением данными пользователей,
 * включая удаление пользователей, их записей в журнале питания и оценок.
 */
@Service
public class CustomerServiceImpl implements CustomerService {
    private final FoodDiaryEntryRepository foodDiaryEntryRepository;
    private final RatingRepository ratingRepository;
    private final CustomerRepository customerRepository;
    private final PlatformTransactionManager transactionManager;

    @Autowired
    public CustomerServiceImpl(FoodDiaryEntryRepository foodDiaryEntryRepository, RatingRepository ratingRepository, CustomerRepository customerRepository, PlatformTransactionManager transactionManager) {
        this.foodDiaryEntryRepository = foodDiaryEntryRepository;
        this.ratingRepository = ratingRepository;
        this.customerRepository = customerRepository;
        this.transactionManager = transactionManager;
    }

    @Override
    public void deleteByCustomerId(Long customerId) {
        TransactionStatus transactionStatus = this.transactionManager.getTransaction(new DefaultTransactionDefinition());
        try {
            List<FoodDiaryEntry> entries = foodDiaryEntryRepository.findByCustomerId(customerId);
            foodDiaryEntryRepository.deleteAll(entries);

            List<Rating> ratings = ratingRepository.findByCustomerId(customerId);
            ratingRepository.deleteAll(ratings);

            customerRepository.deleteById(customerId);

            transactionManager.commit(transactionStatus);
        } catch (DataAccessException ex) {
            transactionManager.rollback(transactionStatus);
            throw ex;
        }
    }
}
