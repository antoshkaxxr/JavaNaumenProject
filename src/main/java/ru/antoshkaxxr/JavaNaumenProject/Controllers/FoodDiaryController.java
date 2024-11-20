package ru.antoshkaxxr.JavaNaumenProject.Controllers;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.antoshkaxxr.JavaNaumenProject.Entities.EatenProduct;
import ru.antoshkaxxr.JavaNaumenProject.Entities.FoodDiaryEntry;
import ru.antoshkaxxr.JavaNaumenProject.Models.EatenProductData;
import ru.antoshkaxxr.JavaNaumenProject.Repositories.EatenProductRepository;
import ru.antoshkaxxr.JavaNaumenProject.Repositories.GoalRepository;
import ru.antoshkaxxr.JavaNaumenProject.Services.CustomerServiceImpl;
import ru.antoshkaxxr.JavaNaumenProject.Services.FoodDiaryServiceImpl;
import ru.antoshkaxxr.JavaNaumenProject.Services.GoalServiceImpl;
import ru.antoshkaxxr.JavaNaumenProject.Services.ProductServiceImpl;

import java.security.Principal;

/**
 * Контроллер для добавления новых приёмов пищи
 */
@Controller
@RequestMapping("/food-diary")
public class FoodDiaryController {

    private final CustomerServiceImpl customerServiceImpl;
    private final FoodDiaryServiceImpl foodDiaryServiceImpl;
    private final ProductServiceImpl productServiceImpl;
    private final EatenProductRepository eatenProductRepository;

    /**
     * Конструктор для инициализации контроллера.
     *
     * @param customerServiceImpl Сервис для работы с пользователями
     */
    public FoodDiaryController(CustomerServiceImpl customerServiceImpl, FoodDiaryServiceImpl foodDiaryServiceImpl,
                               ProductServiceImpl productServiceImpl, EatenProductRepository eatenProductRepository) {
        this.customerServiceImpl = customerServiceImpl;
        this.foodDiaryServiceImpl = foodDiaryServiceImpl;
        this.productServiceImpl = productServiceImpl;
        this.eatenProductRepository = eatenProductRepository;
    }

    /**
     * Обрабатывает Get-запрос для отображения всех приёмов пищи текущего пользователя
     *
     * @return Форма с отображением всех приёмов пищи
     */
    @GetMapping
    public String getMyFoodDiary(Model model) {
        var customer = customerServiceImpl.getCurentLoginedCustomer();
        var foodDiary = foodDiaryServiceImpl.getFoodDiaryEntries(customer.getId());
        model.addAttribute("foodDiary", foodDiary);
        return "myFoodDiary";
    }

    /**
     * Обрабатывает Get-запрос отправки формы для создания нового приёма пищи пользователем
     *
     * @return Форма для создания нового приёма пищи пользователем
     */
    @GetMapping("/add-form")
    public String getNewFoodDiaryForm(Model model, Principal principal) {
        var products = productServiceImpl.findAllProducts(principal.getName());
        model.addAttribute("eatenProduct", new EatenProductData());
        model.addAttribute("availableProducts", products);
        return "newFoodDiary";
    }

    @PostMapping("/add")
    public String saveNewFoodDiary(@Valid @ModelAttribute("eatenProduct") EatenProductData eatenProductData) {
        var foodDiary = new FoodDiaryEntry();
        var customer = customerServiceImpl.getCurentLoginedCustomer();

        var product = productServiceImpl.getProducts(eatenProductData.getId());
        var eatenProduct= new EatenProduct(product, eatenProductData.getData(), eatenProductData.getAmount());
        eatenProductRepository.save(eatenProduct);

        foodDiary.setCustomer(customer);
        foodDiary.setEatenProduct(eatenProduct);
        foodDiaryServiceImpl.save(foodDiary);

        return "redirect:/food-diary";
    }

}
