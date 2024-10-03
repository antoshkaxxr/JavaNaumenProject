package ru.antoshkaxxr.task3.BusinessLogic;

import ru.antoshkaxxr.task3.Entity.Product;

public interface ProductService {
    boolean createProduct(String name, Integer caloriesNumber);

    boolean deleteProduct(String name);

    boolean updateProduct(String name, Integer caloriesNumber);
}
