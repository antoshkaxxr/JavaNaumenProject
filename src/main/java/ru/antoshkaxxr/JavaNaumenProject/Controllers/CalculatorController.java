package ru.antoshkaxxr.JavaNaumenProject.Controllers;

import jakarta.servlet.http.HttpServletRequest;
import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import ru.antoshkaxxr.JavaNaumenProject.Models.CalculatorData;
import ru.antoshkaxxr.JavaNaumenProject.Services.CalculatorServiceImpl;

/**
 * Контроллер для калькулятора калорий.
 */
@Controller
@RequestMapping("/calculator")
public class CalculatorController {

    private static final String INPUT_DATA_NAME = "calculatorData";

    /**
     * Обрабатывает GET-запрос для отображения формы калькулятора.
     *
     * @return Форма калькулятора
     */
    @GetMapping
    public String returnCalculatorForm() {
        return "calculatorForm";
    }

    /**
     * Обрабатывает POST-запрос отправки данных со страницы калькулятора и перенаправляет на страницу с результатами.
     *
     * @param data Объект данных, введеных пользователем
     * @param redirectAttributes Аттрибуты для редиректа на страницу с результатами.
     * @return Имя представления для перенаправления
     */
    @PostMapping("/evaluate")
    public String returnCalculatorForm(CalculatorData data,
                                       RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute(INPUT_DATA_NAME, data);
        return "redirect:/calculator/results";
    }

    /**
     * Обрабатывает GET-запрос для отображения результатов калькулятора.
     * Этот метод извлекает данные, переданные через атрибуты перенаправления,
     * вычисляет результаты на основе введенных данных и добавляет их в модель,
     * чтобы передать на страницу с результатами.
     *
     * @param request Объект HttpServletRequest, который содержит информацию о запросе.
     * @param model Объект Model, используемый для передачи данных на представление.
     * @return Имя представления для отображения результатов калькулятора или перенаправление на форму калькулятора,
     *         если входные данные отсутствуют.
     */
    @GetMapping("/results")
    public String returnResults(HttpServletRequest request,
                                Model model) {
        Map<String, ?> inputFlashMap =
                RequestContextUtils.getInputFlashMap(request);
        if (inputFlashMap == null) {
            return "redirect:/calculator";
        }
        var calculatorData = (CalculatorData) inputFlashMap.get(INPUT_DATA_NAME);
        var bodyData = CalculatorServiceImpl.getCalculatedData(calculatorData);
        model.addAttribute("height", calculatorData.heightInSm());
        model.addAttribute("weight", calculatorData.weight());
        model.addAttribute("age", calculatorData.age());
        model.addAttribute("sex", calculatorData.sex());
        model.addAttribute("type", calculatorData.formula());
        model.addAttribute("bmi", bodyData.bodyMassIndex().bmi());
        model.addAttribute("bmiRange", bodyData.bodyMassIndex().range());
        model.addAttribute("physicalActivity", calculatorData.physicalActivity());
        model.addAttribute("less", bodyData.bounds().less());
        model.addAttribute("stable", bodyData.bounds().stable());
        model.addAttribute("more", bodyData.bounds().more());
        return "calculationResults";
    }
}
