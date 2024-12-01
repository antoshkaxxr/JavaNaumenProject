package ru.antoshkaxxr.JavaNaumenProject.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.antoshkaxxr.JavaNaumenProject.Entities.Product;
import ru.antoshkaxxr.JavaNaumenProject.Repositories.ProductRepository;

import java.util.List;

@Service
public class BaseProductServiceImpl {

    private final ProductRepository productRepository;

    @Autowired
    public BaseProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAllBaseProducts() {
        return productRepository.findByCustomerIsNull();
    }
}
