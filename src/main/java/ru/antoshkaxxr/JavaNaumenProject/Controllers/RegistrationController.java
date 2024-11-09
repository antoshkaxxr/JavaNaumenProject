package ru.antoshkaxxr.JavaNaumenProject.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.antoshkaxxr.JavaNaumenProject.Entities.Customer;
import ru.antoshkaxxr.JavaNaumenProject.Services.CustomerServiceImpl;

/**
 * Контроллер для обработки регистрации пользователей
 */
@Controller
public class RegistrationController {
    private final CustomerServiceImpl customerService;
    private static final String REGISTRATION_FORM_VIEW = "registrationForm";

    /**
     * Конструктор для внедрения зависимости CustomerServiceImpl.
     *
     * @param customerService Сервис для работы с пользователями
     */
    @Autowired
    public RegistrationController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    /**
     * Метод для отображения формы регистрации.
     *
     * @return Имя представления для формы регистрации
     */
    @GetMapping("/registration")
    public String registration() {
        return REGISTRATION_FORM_VIEW;
    }

    /**
     * Метод для добавления нового пользователя.
     *
     * @param customer Объект пользователя, который нужно добавить
     * @param model Модель для передачи данных в представление
     * @return Имя представления для перенаправления или формы регистрации с сообщением
     */
    @PostMapping("/registration")
    public String addCustomer(Customer customer, Model model) {
        if (customerService.addCustomer(customer)) {
            return "redirect:/login";
        }

        model.addAttribute("message", "User exists");
        return REGISTRATION_FORM_VIEW;
    }
}
