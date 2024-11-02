package ru.antoshkaxxr.JavaNaumenProject.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import ru.antoshkaxxr.JavaNaumenProject.Entities.FoodDiaryEntry;
import ru.antoshkaxxr.JavaNaumenProject.Entities.Report;
import ru.antoshkaxxr.JavaNaumenProject.Enums.ReportStatus;
import ru.antoshkaxxr.JavaNaumenProject.Repositories.CustomerRepository;
import ru.antoshkaxxr.JavaNaumenProject.Repositories.FoodDiaryEntryRepository;
import ru.antoshkaxxr.JavaNaumenProject.Repositories.ReportRepository;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

/**
 * Реализация сервиса для работы с отчетами
 */
@Service
public class ReportServiceImpl implements ReportService {
    private final ReportRepository reportRepository;
    private final CustomerRepository customerRepository;
    private final FoodDiaryEntryRepository foodDiaryEntryRepository;
    private final SpringTemplateEngine templateEngine;

    @Autowired
    public ReportServiceImpl(ReportRepository reportRepository, CustomerRepository customerRepository,
                             FoodDiaryEntryRepository foodDiaryEntryRepository, SpringTemplateEngine templateEngine) {
        this.reportRepository = reportRepository;
        this.customerRepository = customerRepository;
        this.foodDiaryEntryRepository = foodDiaryEntryRepository;
        this.templateEngine = templateEngine;
    }

    @Override
    public Report getReport(Long reportId) throws Exception {
        Report report = reportRepository.findById(reportId).orElse(null);
        if (report == null) {
            throw new Exception("Отчет не найден");
        }

        return report;
    }

    @Override
    public Long createReport() {
        Report report = new Report();
        reportRepository.save(report);
        return report.getReportId();
    }

    @Override
    public void generateReport(Long reportId) {
        CompletableFuture.runAsync(() -> {
            Report report;
            try {
                report = getReport(reportId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            AtomicLong customerCountTime = new AtomicLong();
            AtomicLong entriesTime = new AtomicLong();

            long startTime = System.currentTimeMillis();

            CompletableFuture<Long> customerCountFuture = CompletableFuture.supplyAsync(() -> {
                long start = System.currentTimeMillis();
                long count = customerRepository.count();
                customerCountTime.set(System.currentTimeMillis() - start);
                return count;
            });

            CompletableFuture<List<FoodDiaryEntry>> entriesFuture = CompletableFuture.supplyAsync(() -> {
                long start = System.currentTimeMillis();
                List<FoodDiaryEntry> entries = StreamSupport.stream(foodDiaryEntryRepository.findAll().spliterator(), false)
                        .collect(Collectors.toList());
                entriesTime.set(System.currentTimeMillis() - start);
                return entries;
            });

            try {
                Long customerCount = customerCountFuture.join();
                List<FoodDiaryEntry> objectList = entriesFuture.join();

                long elapsed = System.currentTimeMillis() - startTime;

                Context context = new Context();
                context.setVariable("totalTime", elapsed);
                context.setVariable("customerCount", customerCount);
                context.setVariable("customerCountTime", customerCountTime);
                context.setVariable("entriesTime", entriesTime);
                context.setVariable("foodDiaryEntries", objectList);

                String htmlContent = templateEngine.process("report", context);
                report.setContent(htmlContent);

                if (customerCount > 0 && objectList != null && !objectList.isEmpty()) {
                    report.setStatus(ReportStatus.COMPLETED);
                } else {
                    report.setStatus(ReportStatus.ERROR);
                }

            } catch (Exception e) {
                report.setStatus(ReportStatus.ERROR);
                report.setContent("Ошибка при генерации отчета: " + e.getMessage());
            }

            reportRepository.save(report);
        });
    }
}
