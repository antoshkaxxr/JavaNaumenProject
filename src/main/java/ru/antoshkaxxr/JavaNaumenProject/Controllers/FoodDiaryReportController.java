package ru.antoshkaxxr.JavaNaumenProject.Controllers;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.antoshkaxxr.JavaNaumenProject.Entities.FoodDiaryReport;
import ru.antoshkaxxr.JavaNaumenProject.Enums.FileType;
import ru.antoshkaxxr.JavaNaumenProject.Services.FoodDiaryReportServiceImpl;

@Controller
@RequestMapping("/foodDiaryReports")
public class FoodDiaryReportController {

    private final FoodDiaryReportServiceImpl foodDiaryReportServiceImpl;
    private static final String FOOD_DIARY_REPORT_VIEW = "foodDiaryReports";
    private static final String REDIRECT_FOOD_DIARY_REPORT_VIEW = "redirect:/" + FOOD_DIARY_REPORT_VIEW;
    private static final String MESSAGE_ATTRIBUTE = "message";
    private String message = null;
    @Autowired
    public FoodDiaryReportController(FoodDiaryReportServiceImpl foodDiaryReportServiceImpl) {
        this.foodDiaryReportServiceImpl = foodDiaryReportServiceImpl;
    }

    @GetMapping
    public String getAllReports(Principal principal, Model model) {
        List<FoodDiaryReport> reports = foodDiaryReportServiceImpl.getAllByCustomerName(principal.getName());
        if (message != null) {
            model.addAttribute(MESSAGE_ATTRIBUTE, message);
            message = null;
        }
        model.addAttribute(FOOD_DIARY_REPORT_VIEW, reports);
        model.addAttribute("fileTypes", FileType.values());

        return FOOD_DIARY_REPORT_VIEW;
    }

    @PostMapping("/add")
    public String addNewReport(
            @RequestParam FileType type,
            Principal principal) {

        foodDiaryReportServiceImpl.addNewReport(principal.getName(), new byte[0], type, LocalDate.now(), LocalDate.now());
        return REDIRECT_FOOD_DIARY_REPORT_VIEW;
    }

    @PostMapping("/delete")
    public String deleteReport(@RequestParam Long id) {
        foodDiaryReportServiceImpl.deleteReport(id);
        return REDIRECT_FOOD_DIARY_REPORT_VIEW;
    }
}
