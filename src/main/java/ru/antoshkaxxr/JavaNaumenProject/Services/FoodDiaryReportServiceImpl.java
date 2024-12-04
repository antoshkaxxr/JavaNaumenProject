package ru.antoshkaxxr.JavaNaumenProject.Services;

import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.antoshkaxxr.JavaNaumenProject.Entities.FoodDiaryReport;
import ru.antoshkaxxr.JavaNaumenProject.Enums.FileType;
import ru.antoshkaxxr.JavaNaumenProject.Enums.ReportStatus;
import ru.antoshkaxxr.JavaNaumenProject.Repositories.FoodDiaryReportRepository;

/**
 * Реализация сервиса для работы с отчетами дневника питания.
 * Обрабатывает операции, связанные с созданием, получением, удалением и обновлением отчетов.
 */
@Service
public class FoodDiaryReportServiceImpl {

    private final FoodDiaryReportRepository foodDiaryReportRepository;
    private final CustomerServiceImpl customerService;

    /**
     * Конструктор для инициализации сервиса.
     *
     * @param foodDiaryReportRepository Репозиторий для работы с отчетами дневника питания.
     * @param customerService Сервис для работы с пользователями.
     */
    @Autowired
    public FoodDiaryReportServiceImpl(FoodDiaryReportRepository foodDiaryReportRepository,
                                      CustomerServiceImpl customerService) {
        this.foodDiaryReportRepository = foodDiaryReportRepository;
        this.customerService = customerService;
    }

    /**
     * Возвращает список всех отчетов для заданного пользователя.
     *
     * @param customerName Имя пользователя.
     * @return Список отчетов, связанных с указанным пользователем.
     */
    public List<FoodDiaryReport> getAllByCustomerName(String customerName) {
        return foodDiaryReportRepository.getAllByCustomerName(customerName);
    }

    /**
     * Создает и добавляет новый отчет в базу данных.
     *
     * @param customerName Имя пользователя, для которого создается отчет.
     * @param file Байтовый массив, представляющий содержимое файла отчета.
     * @param typeFile Тип файла отчета.
     * @param startDate Начальная дата отчетного периода.
     * @param endDate Конечная дата отчетного периода.
     * @return Созданный отчет.
     * @throws RuntimeException Если пользователь не найден.
     */
    public FoodDiaryReport addNewReport(String customerName, byte[] file, FileType typeFile,
                             LocalDate startDate, LocalDate endDate) {
        var customer = customerService.findByCustomerName(customerName);
        if (customer == null) {
            throw new RuntimeException("User not found");
        }
        var report = new FoodDiaryReport(file, typeFile, startDate, endDate, customer, ReportStatus.CREATED);
        return foodDiaryReportRepository.save(report);
    }

    /**
     * Удаляет отчет из базы данных по идентификатору.
     *
     * @param reportId Идентификатор отчета.
     */
    public void deleteReport(long reportId) {
        foodDiaryReportRepository.deleteById(reportId);
    }

    /**
     * Возвращает отчет по его идентификатору.
     *
     * @param reportId Идентификатор отчета.
     * @return Отчет с заданным идентификатором.
     * @throws RuntimeException Если отчет с указанным идентификатором не найден.
     */
    public FoodDiaryReport getReport(long reportId) {
        return foodDiaryReportRepository.findById(reportId)
                .orElseThrow();
    }

    /**
     * Обновляет статус указанного отчета.
     *
     * @param foodDiaryReport Отчет, для которого обновляется статус.
     * @param status Новый статус отчета.
     */
    public void updateStatus(FoodDiaryReport foodDiaryReport, ReportStatus status) {
        foodDiaryReport.setStatus(status);
        foodDiaryReportRepository.save(foodDiaryReport);
    }

    /**
     * Обновляет файл, связанный с отчетом.
     *
     * @param foodDiaryReport Отчет, для которого обновляется файл.
     * @param file Новый файл отчета в виде байтового массива.
     */
    public void updateFile(FoodDiaryReport foodDiaryReport, byte[] file) {
        foodDiaryReport.setFile(file);
        foodDiaryReportRepository.save(foodDiaryReport);
    }
}
