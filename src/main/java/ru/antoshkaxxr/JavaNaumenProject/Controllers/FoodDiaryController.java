package ru.antoshkaxxr.JavaNaumenProject.Controllers;

import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.antoshkaxxr.JavaNaumenProject.Models.EatenProductData;
import ru.antoshkaxxr.JavaNaumenProject.Repositories.EatenProductRepository;
import ru.antoshkaxxr.JavaNaumenProject.Services.CustomerServiceImpl;
import ru.antoshkaxxr.JavaNaumenProject.Services.FoodDiaryServiceImpl;
import ru.antoshkaxxr.JavaNaumenProject.Services.ProductServiceImpl;

import java.security.Principal;

/**
 * Контроллер для добавления новых приёмов пищи
 */
@Controller
@RequestMapping("/foodDiary")
public class FoodDiaryController {

    private final CustomerServiceImpl customerServiceImpl;
    private final FoodDiaryServiceImpl foodDiaryServiceImpl;
    private final ProductServiceImpl productServiceImpl;

    /**
     * Конструктор для инициализации контроллера.
     *
     * @param customerServiceImpl Сервис для работы с пользователями
     * @param foodDiaryServiceImpl Сервис для работы с приёмами пищи
     * @param productServiceImpl Сервис для работы с продуктами
     */
    public FoodDiaryController(CustomerServiceImpl customerServiceImpl, FoodDiaryServiceImpl foodDiaryServiceImpl,
                               ProductServiceImpl productServiceImpl) {
        this.customerServiceImpl = customerServiceImpl;
        this.foodDiaryServiceImpl = foodDiaryServiceImpl;
        this.productServiceImpl = productServiceImpl;
    }

    /**
     * Обрабатывает Get-запрос для отображения всех приёмов пищи текущего пользователя
     *
     * @param model Объект Model, используемый для передачи данных на представление.
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
     * @param model Объект Model, используемый для передачи данных на представление.
     * @param principal Объект текущего аутентифицированного пользователя.
     * @return Форма для создания нового приёма пищи пользователем
     */
    @GetMapping("/addForm")
    public String getNewFoodDiaryForm(Model model, Principal principal) {
        var products = productServiceImpl.findAllProducts(principal.getName());
        model.addAttribute("eatenProduct", new EatenProductData());
        model.addAttribute("availableProducts", products);
        return "newFoodDiary";
    }

    /**
     * Обрабатывает Post-запрос создание нового приёма пищи
     *
     * @param eatenProductData Данные об употреблённом продукте на основе которого создаться приём пищи
     * @return Форма с приёмами пищами пользователя
     */
    @PostMapping("/add")
    public String saveNewFoodDiary(@Valid @ModelAttribute("eatenProduct") EatenProductData eatenProductData) {
        foodDiaryServiceImpl.save(eatenProductData);
        return "redirect:/foodDiary";
    }

    @PostMapping("/delete/{foodDiaryId}")
    public String deleteFoodDiary(@PathVariable("foodDiaryId") Long foodDiaryId) {
        foodDiaryServiceImpl.delete(foodDiaryId);
        return "redirect:/foodDiary";
    }

}
