package ru.antoshkaxxr.JavaNaumenProject.Controllers;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.antoshkaxxr.JavaNaumenProject.Entities.FoodDiaryReport;
import ru.antoshkaxxr.JavaNaumenProject.Enums.FileType;
import ru.antoshkaxxr.JavaNaumenProject.Services.BaseReportFileGenerator;
import ru.antoshkaxxr.JavaNaumenProject.Services.FoodDiaryReportServiceImpl;

@Controller
@RequestMapping("/foodDiaryReports")
public class FoodDiaryReportController {

    private final FoodDiaryReportServiceImpl foodDiaryReportServiceImpl;
    private final BaseReportFileGenerator baseReportFileGenerator;
    private static final String FOOD_DIARY_REPORT_VIEW = "foodDiaryReports";
    private static final String REDIRECT_FOOD_DIARY_REPORT_VIEW = "redirect:/" + FOOD_DIARY_REPORT_VIEW;
    private static final String MESSAGE_ATTRIBUTE = "message";
    private String message = null;
    @Autowired
    public FoodDiaryReportController(FoodDiaryReportServiceImpl foodDiaryReportServiceImpl,
                                     BaseReportFileGenerator baseReportFileGenerator) {
        this.foodDiaryReportServiceImpl = foodDiaryReportServiceImpl;
        this.baseReportFileGenerator = baseReportFileGenerator;
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
            @RequestParam LocalDate startDate,
            @RequestParam LocalDate endDate,
            @RequestParam FileType type,
            Principal principal) {

        var report = foodDiaryReportServiceImpl.addNewReport(principal.getName(), null, type, startDate, endDate);
        baseReportFileGenerator.generateFile(report);
        return REDIRECT_FOOD_DIARY_REPORT_VIEW;
    }

    @PostMapping("/delete")
    public String deleteReport(@RequestParam Long id) {
        foodDiaryReportServiceImpl.deleteReport(id);
        return REDIRECT_FOOD_DIARY_REPORT_VIEW;
    }

    @GetMapping("/download")
    public ResponseEntity<byte[]> getReportData(@RequestParam Long id) {
        FoodDiaryReport report = foodDiaryReportServiceImpl.getReport(id);
        if (report.getFile() == null) {
            return ResponseEntity.notFound().build();
        }
        HttpHeaders headers = new HttpHeaders();
        switch (report.getTypeFile()) {
            case PDF -> {
                headers.add("Content-Disposition", "attachment; filename=sample.pdf");
                headers.add("Content-Type", "application/pdf");
            }
            case EXCEL -> {
                headers.add("Content-Disposition", "attachment; filename=sample.xlsx");
                headers.add("Content-Type", "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            }
            default -> throw new RuntimeException();
        }
        return new ResponseEntity<>(report.getFile(), headers, HttpStatus.OK);
    }
}
