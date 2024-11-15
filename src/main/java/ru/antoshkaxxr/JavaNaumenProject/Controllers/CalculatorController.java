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
import ru.antoshkaxxr.JavaNaumenProject.Dto.CalculatorData;

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
        var newBlog = (CalculatorData) inputFlashMap.get("calculatorData");
        model.addAttribute("height", newBlog.height());
        model.addAttribute("weight", newBlog.weight());
        model.addAttribute("sex", newBlog.sex());
        model.addAttribute("type", newBlog.formula());
        return "calculationResults";
    }
}
