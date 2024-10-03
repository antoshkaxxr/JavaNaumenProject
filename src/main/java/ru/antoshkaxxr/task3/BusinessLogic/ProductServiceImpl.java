package ru.antoshkaxxr.task3.BusinessLogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.antoshkaxxr.task3.DataAccessLayer.ProductRepository;
import ru.antoshkaxxr.task3.Entity.Product;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public boolean createProduct(String name, Integer caloriesNumber) {
        Product product = new Product();
        product.setId((long) name.hashCode());
        product.setName(name);
        product.setCaloriesNumber(caloriesNumber);
        return productRepository.create(product);
    }

    @Override
    public boolean deleteProduct(String name) {
        return productRepository.delete((long) name.hashCode());
    }

    @Override
    public boolean updateProduct(String name, Integer newCaloriesNumber) {
        Product product = productRepository.read((long) name.hashCode());
        if (product == null) {
            return false;
        }
        product.setCaloriesNumber(newCaloriesNumber);
        return productRepository.update(product);
    }
}
