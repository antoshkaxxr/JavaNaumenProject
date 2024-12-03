package ru.antoshkaxxr.JavaNaumenProject.Controllers;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.antoshkaxxr.JavaNaumenProject.Entities.Product;
import ru.antoshkaxxr.JavaNaumenProject.Models.EatenProductData;
import ru.antoshkaxxr.JavaNaumenProject.Services.BaseProductServiceImpl;
import ru.antoshkaxxr.JavaNaumenProject.Services.CustomerServiceImpl;
import ru.antoshkaxxr.JavaNaumenProject.Services.FoodDiaryServiceImpl;
import ru.antoshkaxxr.JavaNaumenProject.Services.ProductServiceImpl;

/**
 * Контроллер для добавления новых приёмов пищи
 */
@Controller
@RequestMapping("/foodDiary")
public class FoodDiaryController {

    private static final String FOOD_DIARY_VIEW_REDIRECT = "redirect:/foodDiary";
    private static final String NEW_FOOD_DIARY_FORM = "newFoodDiary";
    private static final String AVAILABLE_PRODUCTS = "availableProducts";
    private final CustomerServiceImpl customerServiceImpl;
    private final FoodDiaryServiceImpl foodDiaryServiceImpl;
    private final ProductServiceImpl productServiceImpl;
    private final BaseProductServiceImpl baseProductServiceImpl;

    /**
     * Конструктор для инициализации контроллера.
     *
     * @param customerServiceImpl Сервис для работы с пользователями
     * @param foodDiaryServiceImpl Сервис для работы с приёмами пищи
     * @param productServiceImpl Сервис для работы с продуктами
     * @param baseProductServiceImpl Сервис для работы с базовыми продуктами
     */
    public FoodDiaryController(CustomerServiceImpl customerServiceImpl, FoodDiaryServiceImpl foodDiaryServiceImpl,
                               ProductServiceImpl productServiceImpl, BaseProductServiceImpl baseProductServiceImpl) {
        this.customerServiceImpl = customerServiceImpl;
        this.foodDiaryServiceImpl = foodDiaryServiceImpl;
        this.productServiceImpl = productServiceImpl;
        this.baseProductServiceImpl = baseProductServiceImpl;
    }

    /**
     * Объединяет продукты пользователя с базовыми продуктами
     *
     * @param principal Объект текущего аутентифицированного пользователя.
     * @return Список продуктов пользователя и базовых продуктов
     */
    public List<Product> getAvailableProducts(Principal principal) {
        List<Product> products = productServiceImpl.findAllProducts(principal.getName());
        List<Product> baseProducts = baseProductServiceImpl.getAllBaseProducts();
        List<Product> availableProducts = new ArrayList<>(products);
        availableProducts.addAll(baseProducts);
        return availableProducts;
    }

    /**
     * Обрабатывает Get-запрос для отображения всех приёмов пищи текущего пользователя
     *
     * @param model Объект Model, используемый для передачи данных на представление.
     * @return Форма с отображением всех приёмов пищи
     */
    @GetMapping
    public String getMyFoodDiary(Model model) {
        var customer = customerServiceImpl.getCurrentLoggedInCustomer();
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
        model.addAttribute(AVAILABLE_PRODUCTS, getAvailableProducts(principal));
        return NEW_FOOD_DIARY_FORM;
    }

    /**
     * Обрабатывает Post-запрос создание нового приёма пищи
     *
     * @param eatenProductData Данные об употреблённом продукте на основе которого создаться приём пищи
     * @param bindingResult Данные об успешности преобразование контроллером объекта из запроса в EatenProductData
     * @param model Объект Model, используемый для передачи данных на представление.
     * @param principal Объект текущего аутентифицированного пользователя.
     * @return Форма с приёмами пищами пользователя
     */
    @PostMapping("/add")
    public String saveNewFoodDiary(EatenProductData eatenProductData, BindingResult bindingResult,
                                   Model model, Principal principal) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("error", "Введены некорректные данные");
            model.addAttribute(AVAILABLE_PRODUCTS, getAvailableProducts(principal));
            return NEW_FOOD_DIARY_FORM;
        }
        foodDiaryServiceImpl.save(eatenProductData);
        return FOOD_DIARY_VIEW_REDIRECT;
    }

    /**
     * Обрабатывает Post-запрос на удаление по id цели
     *
     * @param foodDiaryId id цели
     * @return Форма с приёмами пищами пользователя
     */
    @PostMapping("/delete/{foodDiaryId}")
    public String deleteFoodDiary(@PathVariable("foodDiaryId") Long foodDiaryId) {
        foodDiaryServiceImpl.delete(foodDiaryId);
        return FOOD_DIARY_VIEW_REDIRECT;
    }
}
