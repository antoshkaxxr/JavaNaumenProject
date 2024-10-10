package ru.antoshkaxxr.JavaNaumenProject.BusinessLogic;

public interface ProductService {
    boolean createProduct(String name, Integer caloriesNumber);

    boolean deleteProduct(String name);

    boolean updateProduct(String name, Integer caloriesNumber);
}
