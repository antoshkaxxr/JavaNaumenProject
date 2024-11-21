package ru.antoshkaxxr.JavaNaumenProject.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
