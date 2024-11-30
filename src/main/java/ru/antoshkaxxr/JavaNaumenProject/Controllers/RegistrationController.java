package ru.antoshkaxxr.JavaNaumenProject.Controllers;

import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.antoshkaxxr.JavaNaumenProject.Entities.Customer;
import ru.antoshkaxxr.JavaNaumenProject.Enums.Role;
import ru.antoshkaxxr.JavaNaumenProject.Models.DataForRegistrationAdmin;
import ru.antoshkaxxr.JavaNaumenProject.Services.CustomerServiceImpl;

/**
 * Контроллер для обработки регистрации пользователей
 */
@Controller
public class RegistrationController {
    private final CustomerServiceImpl customerService;
    @Value("${app.env.secret_key}")
    private String secretKey;
    private static final String REGISTRATION_FORM_VIEW = "registrationForm";
    private static final String REGISTRATION_FORM_ADMIN_VIEW = "registrationAdminForm";
    private static final String LOGIN_FORM_REDIRECT = "redirect:/login";

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
     * Метод отображения формы регистрации для админа
     *
     * @return Имя представления формы регистрации для админа
     */
    @GetMapping("/registration/admin")
    public String registrationAdmin() {
        return REGISTRATION_FORM_ADMIN_VIEW;
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
        customer.setRole(Role.USER);
        if (customerService.addCustomer(customer)) {
            return LOGIN_FORM_REDIRECT;
        }

        model.addAttribute("message", "User exists");
        return REGISTRATION_FORM_VIEW;
    }

    /**
     * Метод для добавления нового пользователя-админа.
     *
     * @param customerData Объект пользователя и секретный ключ, который нужно добавить
     * @param model Модель для передачи данных в представление
     * @return Имя представления для перенаправления или формы регистрации с сообщением
     */
    @PostMapping("/registration/admin")
    public String addCustomerAdmin(DataForRegistrationAdmin customerData, Model model) {
        if (secretKey == null) {
            throw new IllegalArgumentException("Делой переменную окружения SECRET_KEY и перезагружайся");
        }
        if (!Objects.equals(customerData.secretKey(), secretKey)) {
            throw new SecurityException("чо нада");
        }
        var newCustomer = new Customer(customerData.name(), customerData.email(),
                customerData.weight(), customerData.height());
        newCustomer.setPassword(customerData.password());
        newCustomer.setRole(Role.ADMIN);
        if (customerService.addCustomer(newCustomer)) {
            return LOGIN_FORM_REDIRECT;
        }

        model.addAttribute("messageForAdmin", "Admin exists");
        return REGISTRATION_FORM_ADMIN_VIEW;
    }
}
