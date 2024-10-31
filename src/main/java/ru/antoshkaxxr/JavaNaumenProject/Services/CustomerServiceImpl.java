package ru.antoshkaxxr.JavaNaumenProject.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
import ru.antoshkaxxr.JavaNaumenProject.Entities.Customer;
import ru.antoshkaxxr.JavaNaumenProject.Entities.FoodDiaryEntry;
import ru.antoshkaxxr.JavaNaumenProject.Entities.Rating;
import ru.antoshkaxxr.JavaNaumenProject.Enums.Role;
import ru.antoshkaxxr.JavaNaumenProject.Repositories.CustomerRepository;
import ru.antoshkaxxr.JavaNaumenProject.Repositories.FoodDiaryEntryRepository;
import ru.antoshkaxxr.JavaNaumenProject.Repositories.RatingRepository;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Реализация сервиса для работы с пользователями.
 * Обрабатывает операции, связанные с управлением данными пользователей,
 * включая удаление пользователей, их записей в журнале питания и оценок.
 */
@Service
public class CustomerServiceImpl implements CustomerService, UserDetailsService {
    private final FoodDiaryEntryRepository foodDiaryEntryRepository;
    private final RatingRepository ratingRepository;
    private final CustomerRepository customerRepository;
    private final PlatformTransactionManager transactionManager;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public CustomerServiceImpl(FoodDiaryEntryRepository foodDiaryEntryRepository,
                               RatingRepository ratingRepository,
                               CustomerRepository customerRepository,
                               PlatformTransactionManager transactionManager,
                               PasswordEncoder passwordEncoder) {
        this.foodDiaryEntryRepository = foodDiaryEntryRepository;
        this.ratingRepository = ratingRepository;
        this.customerRepository = customerRepository;
        this.transactionManager = transactionManager;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Customer findByCustomerName(String customerName) {
        return customerRepository.findByName(customerName);
    }

    @Override
    public boolean addCustomer(Customer customer) {
        Customer repoCustomer = customerRepository.findByName(customer.getName());
        if (repoCustomer != null) {
            return false;
        }
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        customer.setRole(Role.USER);
        customerRepository.save(customer);
        return true;
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer = findByCustomerName(username);
        return new User(customer.getName(), customer.getPassword(),
                mapRoleToAuthority(customer.getRole()));
    }

    private Collection<GrantedAuthority> mapRoleToAuthority(Role role) {
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + role.name()));
    }
}
