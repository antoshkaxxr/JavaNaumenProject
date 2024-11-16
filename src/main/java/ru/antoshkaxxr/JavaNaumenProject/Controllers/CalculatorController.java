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

@Controller
@RequestMapping("/calculator")
public class CalculatorController {

    @GetMapping
    public String returnCalculatorForm() {
        return "calculatorForm";
    }


    @PostMapping("/evaluate")
    public String returnCalculatorForm(CalculatorData data,
                                       RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("calculatorData", data);
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
        var calculatorData = (CalculatorData) inputFlashMap.get("calculatorData");
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
