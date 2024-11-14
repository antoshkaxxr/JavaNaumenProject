package ru.antoshkaxxr.JavaNaumenProject.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.antoshkaxxr.JavaNaumenProject.Entities.Product;
import ru.antoshkaxxr.JavaNaumenProject.Enums.ProductCategory;
import ru.antoshkaxxr.JavaNaumenProject.Mappers.ProductCategoryMapper;
import ru.antoshkaxxr.JavaNaumenProject.Services.ProductService;

import java.util.List;

/**
 * Контроллер для работы с продуктами пользователя
 */
@Controller
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String getAllProducts(Model model) {
        List<Product> products = productService.findAllProducts();
        model.addAttribute("products", products);
        model.addAttribute("categories", ProductCategory.values());
        model.addAttribute("categoryMap", ProductCategoryMapper.getCategoryMap());
        return "products";
    }

    @PostMapping("/products/add")
    public String addProduct(
            @RequestParam String name,
            @RequestParam String category,
            @RequestParam Double caloriesNumberHundred,
            Model model) {
        ProductCategory productCategory = ProductCategoryMapper.getCategoryByRussianName(category);
        if (productCategory == null) {
            model.addAttribute("message", "Неверная категория");
            return "redirect:/products";
        }

        boolean success = productService.createProduct(name, productCategory, caloriesNumberHundred);
        if (success) {
            model.addAttribute("message", "Продукт успешно добавлен");
        } else {
            model.addAttribute("message", "Продукт с таким именем уже существует");
        }
        return "redirect:/products";
    }

    @PostMapping("/products/delete")
    public String deleteProduct(@RequestParam String name, Model model) {
        boolean success = productService.deleteProduct(name);
        if (success) {
            model.addAttribute("message", "Продукт успешно удален");
        } else {
            model.addAttribute("message", "Продукт не найден");
        }
        return "redirect:/products";
    }

    @PostMapping("/products/update")
    public String updateProduct(
            @RequestParam String name,
            @RequestParam Double newCaloriesNumberHundred,
            Model model) {
        boolean success = productService.updateProduct(name, newCaloriesNumberHundred);
        if (success) {
            model.addAttribute("message", "Информация о продукте успешно обновлена");
        } else {
            model.addAttribute("message", "Продукт не найден");
        }
        return "redirect:/products";
    }
}