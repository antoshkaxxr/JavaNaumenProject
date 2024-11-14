package ru.antoshkaxxr.JavaNaumenProject.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.antoshkaxxr.JavaNaumenProject.Entities.Product;
import ru.antoshkaxxr.JavaNaumenProject.Enums.ProductCategory;
import ru.antoshkaxxr.JavaNaumenProject.Mappers.ProductCategoryMapper;
import ru.antoshkaxxr.JavaNaumenProject.Services.ProductService;

/**
 * Контроллер для работы с продуктами пользователя.
 * Предоставляет методы для отображения списка продуктов, добавления, удаления и обновления продуктов.
 */
@Controller
public class ProductController {
    private static final String PRODUCTS_VIEW = "products";
    private static final String REDIRECT_PRODUCTS = "redirect:/" + PRODUCTS_VIEW;
    private static final String MESSAGE_ATTRIBUTE = "message";
    private static final String INVALID_CATEGORY_MESSAGE = "Неверная категория";
    private static final String PRODUCT_ADDED_MESSAGE = "Продукт успешно добавлен";
    private static final String PRODUCT_EXISTS_MESSAGE = "Продукт с таким именем уже существует";
    private static final String PRODUCT_DELETED_MESSAGE = "Продукт успешно удален";
    private static final String PRODUCT_NOT_FOUND_MESSAGE = "Продукт не найден";
    private static final String PRODUCT_UPDATED_MESSAGE = "Информация о продукте успешно обновлена";

    private final ProductService productService;

    /**
     * Конструктор для инициализации контроллера.
     *
     * @param productService Сервис для работы с продуктами пользователя.
     */
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Отображает страницу со списком всех продуктов.
     *
     * @param model Модель для передачи данных в представление.
     * @return Имя представления для отображения.
     */
    @GetMapping("/products")
    public String getAllProducts(Model model) {
        List<Product> products = productService.findAllProducts();
        model.addAttribute(PRODUCTS_VIEW, products);
        model.addAttribute("categories", ProductCategory.values());
        model.addAttribute("categoryMap", ProductCategoryMapper.getCategoryMap());
        return PRODUCTS_VIEW;
    }

    /**
     * Обрабатывает запрос на добавление нового продукта.
     *
     * @param name Название продукта.
     * @param category Категория продукта (русское название).
     * @param caloriesNumberHundred Число ккал на 100 граммов продукта.
     * @param model Модель для передачи данных в представление.
     * @return Имя представления для отображения.
     */
    @PostMapping("/products/add")
    public String addProduct(
            @RequestParam String name,
            @RequestParam String category,
            @RequestParam Double caloriesNumberHundred,
            Model model) {
        ProductCategory productCategory = ProductCategoryMapper.getCategoryByRussianName(category);
        if (productCategory == null) {
            model.addAttribute(MESSAGE_ATTRIBUTE, INVALID_CATEGORY_MESSAGE);
            return REDIRECT_PRODUCTS;
        }

        boolean success = productService.createProduct(name, productCategory, caloriesNumberHundred);
        if (success) {
            model.addAttribute(MESSAGE_ATTRIBUTE, PRODUCT_ADDED_MESSAGE);
        } else {
            model.addAttribute(MESSAGE_ATTRIBUTE, PRODUCT_EXISTS_MESSAGE);
        }
        return REDIRECT_PRODUCTS;
    }

    /**
     * Обрабатывает запрос на удаление продукта.
     *
     * @param name Название продукта.
     * @param model Модель для передачи данных в представление.
     * @return Имя представления для отображения.
     */
    @PostMapping("/products/delete")
    public String deleteProduct(@RequestParam String name, Model model) {
        boolean success = productService.deleteProduct(name);
        if (success) {
            model.addAttribute(MESSAGE_ATTRIBUTE, PRODUCT_DELETED_MESSAGE);
        } else {
            model.addAttribute(MESSAGE_ATTRIBUTE, PRODUCT_NOT_FOUND_MESSAGE);
        }
        return REDIRECT_PRODUCTS;
    }

    /**
     * Обрабатывает запрос на обновление информации о продукте.
     *
     * @param name Название продукта.
     * @param newCaloriesNumberHundred Новое число ккал на 100 граммов продукта.
     * @param model Модель для передачи данных в представление.
     * @return Имя представления для отображения.
     */
    @PostMapping("/products/update")
    public String updateProduct(
            @RequestParam String name,
            @RequestParam Double newCaloriesNumberHundred,
            Model model) {
        boolean success = productService.updateProduct(name, newCaloriesNumberHundred);
        if (success) {
            model.addAttribute(MESSAGE_ATTRIBUTE, PRODUCT_UPDATED_MESSAGE);
        } else {
            model.addAttribute(MESSAGE_ATTRIBUTE, PRODUCT_NOT_FOUND_MESSAGE);
        }
        return REDIRECT_PRODUCTS;
    }
}
