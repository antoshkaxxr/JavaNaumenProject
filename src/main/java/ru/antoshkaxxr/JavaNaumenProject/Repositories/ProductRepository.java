package ru.antoshkaxxr.JavaNaumenProject.Repositories;

import ru.antoshkaxxr.JavaNaumenProject.Entities.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {

}
