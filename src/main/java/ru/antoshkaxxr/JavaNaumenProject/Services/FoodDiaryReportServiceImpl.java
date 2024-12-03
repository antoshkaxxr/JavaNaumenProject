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

    public void addNewReport(String customerName, byte[] file, FileType typeFile,
                             LocalDate startDate, LocalDate endDate) {
        var customer = customerService.findByCustomerName(customerName);
        if (customer == null) {
            throw new RuntimeException("User not found");
        }
        var report = new FoodDiaryReport(file, typeFile, startDate, endDate, customer, ReportStatus.CREATED);
        foodDiaryReportRepository.save(report);
    }

    public void deleteReport(long reportId) {
        foodDiaryReportRepository.deleteById(reportId);
    }
}
