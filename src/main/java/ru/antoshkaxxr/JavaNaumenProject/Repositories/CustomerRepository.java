package ru.antoshkaxxr.JavaNaumenProject.Repositories;

import org.springframework.data.repository.CrudRepository;
import ru.antoshkaxxr.JavaNaumenProject.Entities.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

}
