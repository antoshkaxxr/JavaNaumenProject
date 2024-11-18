package ru.antoshkaxxr.JavaNaumenProject.Controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;
import ru.antoshkaxxr.JavaNaumenProject.Models.CalculatorData;
import ru.antoshkaxxr.JavaNaumenProject.Models.TargetData;
import ru.antoshkaxxr.JavaNaumenProject.Services.CalculatorServiceImpl;

import java.util.Map;

/**
 * Контроллер для составления целей по похудению / набору веса.
 */
@Controller
@RequestMapping("/target")
public class TargetController {

    private static final String INPUT_DATA_NAME = "targetData";
    /**
     * Обрабатывает GET-запрос для отправки формы, в которой пользователь введёт
     * данные для составления новой цели
     *
     * @return Форма ввода данных для новой цели по похудению / набору веса
     */
    @GetMapping("/form")
    public String returnTargetForm() {
        return "newTargetForm";
    }

    /**
     * Обрабатывает POST-запрос отправки данных со страницы составления новой цели
     *
     * @param data Объект данных, введённых пользователем
     * @param redirectAttributes Аттрибуты для редиректа на страницу с результатами.
     * @return Имя представления для перенаправления
     */
    @PostMapping("/evaluate")
    public String returnCalculatorForm(TargetData data,
                                       RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute(INPUT_DATA_NAME, data);
        return "redirect:/calculator/results";
    }

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
