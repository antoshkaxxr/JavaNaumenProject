package ru.antoshkaxxr.JavaNaumenProject.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.antoshkaxxr.JavaNaumenProject.Entities.Customer;
import ru.antoshkaxxr.JavaNaumenProject.Service.CustomerServiceImpl;

/**
 * Контроллер для обработки регистрации пользователей
 */
@Controller
public class RegistrationController {
    private final CustomerServiceImpl customerService;

    @Autowired
    public RegistrationController(CustomerServiceImpl customerService) {
        this.customerService = customerService;
    }

    /**
     * Метод для отображения формы регистрации
     */
    @GetMapping("/registration")
    public String registration() {
        return "registrationForm";
    }

    /**
     * Метод для добавления нового клиента
     * @param customer объект клиента, который нужно добавить
     * @param model модель для передачи данных в представление
     */
    @PostMapping("/registration")
    public String addCustomer(Customer customer, Model model) {
        if (customerService.addCustomer(customer)) {
            return "redirect:/login";
        }

        model.addAttribute("message", "User exists");
        return "registrationForm";
    }
}
