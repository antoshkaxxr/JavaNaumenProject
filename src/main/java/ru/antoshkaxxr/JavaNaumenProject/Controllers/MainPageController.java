package ru.antoshkaxxr.JavaNaumenProject.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.antoshkaxxr.JavaNaumenProject.Enums.Role;
import ru.antoshkaxxr.JavaNaumenProject.Services.CustomerServiceImpl;

/**
 * Контроллер для выдачи главной страницы
 */
@Controller
@RequestMapping("/")
public class MainPageController {

    private final CustomerServiceImpl customerService;

    /**
     * Конструктор для инициализации контроллера.
     * @param customerService Сервис для работы с пользователями
     */
    public MainPageController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    /**
     * Обрабатывает запрос на отправку главной формы
     * @param model Объект Model, используемый для передачи данных на представление.
     * @return Имя представления для отображения.
     */
    @GetMapping
    public String mainPage(Model model) {
        var role = customerService.getCurrentLoggedInCustomer().getRole();
        if (role == Role.ADMIN) {
            model.addAttribute("isAdmin", "Yes");
        }
        return "mainForm";
    }
}
