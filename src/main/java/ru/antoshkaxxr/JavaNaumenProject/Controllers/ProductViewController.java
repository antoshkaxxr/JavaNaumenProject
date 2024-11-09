package ru.antoshkaxxr.JavaNaumenProject.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.antoshkaxxr.JavaNaumenProject.Entities.Product;
import ru.antoshkaxxr.JavaNaumenProject.Repositories.ProductRepository;

/**
 * Контроллер для отображения списка продуктов.
 */
@Controller
@RequestMapping("/products/view")
public class ProductViewController {
    private final ProductRepository productRepository;

    /**
     * Конструктор для внедрения зависимости ProductRepository.
     *
     * @param productRepository Репозиторий для работы с продуктами
     */
    @Autowired
    public ProductViewController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Обрабатывает GET-запрос для отображения списка продуктов.
     *
     * @param model Модель для передачи данных в представление
     * @return Имя представления для отображения списка продуктов
     */
    @GetMapping("/list")
    public String productsListView(Model model) {
        Iterable<Product> products = productRepository.findAll();
        model.addAttribute("products", products);
        return "productsList";
    }
}
