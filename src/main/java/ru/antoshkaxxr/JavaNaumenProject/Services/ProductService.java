package ru.antoshkaxxr.JavaNaumenProject.Services;

import java.util.List;
import ru.antoshkaxxr.JavaNaumenProject.Entities.Product;
import ru.antoshkaxxr.JavaNaumenProject.Enums.ProductCategory;

/**
 * Сервис для работы с продуктами, добавленными пользователем
 * Предоставляет методы для создания, удаления и обновления продуктов
 */
public interface ProductService {
    /**
     * Добавляет новый продукт в репозиторий продуктов пользователя
     *
     * @param name Название продукта
     * @param category Категория продукта
     * @param caloriesNumberHundred Число ккал на 100 граммов продукта
     * @param username Имя пользователя
     * @return Если такого продукта еще нет в репозитории, он будет успешно добавлен
     * и метод вернет true. Иначе - получим false и обработаем этот случай
     */
    boolean createProduct(String name, ProductCategory category,
                          Double caloriesNumberHundred, String username);

    /**
     * Удаляет продукт из репозитория по названию
     *
     * @param name Название продукта
     * @param username Имя пользователя
     * @return Булево значение об успешности операции удаления
     */
    boolean deleteProduct(String name, String username);

    /**
     * Обновляет информацию о калорийности продукта по его названию
     *
     * @param name Название продукта
     * @param newCaloriesNumberHundred Новое число ккал на 100 граммов продукта
     * @param username Имя пользователя
     * @return Булево значение об успешности операции обновления
     */
    boolean updateProduct(String name, Double newCaloriesNumberHundred, String username);

    /**
     * Находит все добавленные пользователем продукты
     *
     * @param username Имя пользователя
     * @return Список всех продуктов
     */
    List<Product> findAllProducts(String username);
}
