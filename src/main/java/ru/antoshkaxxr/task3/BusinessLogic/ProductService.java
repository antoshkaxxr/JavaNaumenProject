package ru.antoshkaxxr.task3.BusinessLogic;

public interface ProductService {
    boolean createProduct(String name, Integer caloriesNumber);

    boolean deleteProduct(String name);

    boolean updateProduct(String name, Integer caloriesNumber);
}
