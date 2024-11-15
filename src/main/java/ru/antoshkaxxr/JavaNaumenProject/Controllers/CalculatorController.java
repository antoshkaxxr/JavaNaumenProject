package ru.antoshkaxxr.JavaNaumenProject.Controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.antoshkaxxr.JavaNaumenProject.Dto.CalculatorData;

@Controller
@RequestMapping("/calculator")
public class CalculatorController {

    @GetMapping
    public String returnCalculatorForm() {
        return "calculatorForm";
    }

    @ResponseBody
    @PostMapping("/evaluate")
    public String returnCalculatorForm(CalculatorData data) {
        return data.toString();
    }
}
