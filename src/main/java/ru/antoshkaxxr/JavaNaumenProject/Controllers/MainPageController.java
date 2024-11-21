package ru.antoshkaxxr.JavaNaumenProject.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.antoshkaxxr.JavaNaumenProject.Repositories.GoalRepository;
import ru.antoshkaxxr.JavaNaumenProject.Services.CustomerServiceImpl;
import ru.antoshkaxxr.JavaNaumenProject.Services.GoalServiceImpl;

import java.security.Principal;

/**
 * Контроллер для выдачи главной страницы
 */
@Controller
@RequestMapping("/")
public class MainPageController {


    /**
     * Конструктор для инициализации контроллера.
     */
    public MainPageController() {
    }

    /**
     * Обрабатывает запрос на отправку главной формы
     *
     * @return Имя представления для отображения.
     */
    @GetMapping
    public String mainPage() {
        return "mainForm";
    }
}
