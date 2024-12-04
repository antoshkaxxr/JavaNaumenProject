package ru.antoshkaxxr.JavaNaumenProject.Services;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.antoshkaxxr.JavaNaumenProject.Entities.FoodDiaryReport;
import ru.antoshkaxxr.JavaNaumenProject.Enums.FileType;
import ru.antoshkaxxr.JavaNaumenProject.Enums.ReportStatus;
import ru.antoshkaxxr.JavaNaumenProject.Repositories.FoodDiaryReportRepository;

@Service
public class FoodDiaryReportServiceImpl {

    private final FoodDiaryReportRepository foodDiaryReportRepository;
    private final CustomerServiceImpl customerService;

    @Autowired
    public FoodDiaryReportServiceImpl(FoodDiaryReportRepository foodDiaryReportRepository,
                                      CustomerServiceImpl customerService) {
        this.foodDiaryReportRepository = foodDiaryReportRepository;
        this.customerService = customerService;
    }

    public List<FoodDiaryReport> getAllByCustomerName(String customerName) {
        return foodDiaryReportRepository.getAllByCustomerName(customerName);
    }

    public FoodDiaryReport addNewReport(String customerName, byte[] file, FileType typeFile,
                             LocalDate startDate, LocalDate endDate) {
        var customer = customerService.findByCustomerName(customerName);
        if (customer == null) {
            throw new RuntimeException("User not found");
        }
        var report = new FoodDiaryReport(file, typeFile, startDate, endDate, customer, ReportStatus.CREATED);
        return foodDiaryReportRepository.save(report);
    }

    public void deleteReport(long reportId) {
        foodDiaryReportRepository.deleteById(reportId);
    }

    public FoodDiaryReport getReport(long reportId) {
        return foodDiaryReportRepository.findById(reportId)
                .orElseThrow();
    }

    public void updateStatus(FoodDiaryReport foodDiaryReport, ReportStatus status) {
        foodDiaryReport.setStatus(status);
        foodDiaryReportRepository.save(foodDiaryReport);
    }

    public void updateFile(FoodDiaryReport foodDiaryReport, byte[] file) {
        foodDiaryReport.setFile(file);
        foodDiaryReportRepository.save(foodDiaryReport);
    }
}
