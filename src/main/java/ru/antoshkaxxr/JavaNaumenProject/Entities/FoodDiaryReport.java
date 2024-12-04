package ru.antoshkaxxr.JavaNaumenProject.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.time.LocalDate;
import ru.antoshkaxxr.JavaNaumenProject.Enums.FileType;
import ru.antoshkaxxr.JavaNaumenProject.Enums.ReportStatus;

/**
 * Сущность, представляющая отчет дневника питания.
 * Содержит информацию о файле отчёта, его расширении и статусе создания, о датах отчёта и о клиенте.
 */
@Entity
@Table
public class FoodDiaryReport {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "food_diary_report_seq")
    @SequenceGenerator(name = "food_diary_report_seq", sequenceName = "food_diary_report_seq", allocationSize = 1)
    private Long id;

    private byte[] file;

    private FileType typeFile;

    private LocalDate startDate;

    private LocalDate endDate;

    @ManyToOne
    private Customer customer;

    private ReportStatus status;

    /**
     * Конструктор для создания нового отчета с заданными параметрами.
     *
     * @param file      Файл отчета.
     * @param typeFile  Тип файла отчета.
     * @param startDate Начальная дата отчета.
     * @param endDate   Конечная дата отчета.
     * @param customer  Клиент, которому принадлежит отчет.
     * @param status    Статус отчета.
     */
    public FoodDiaryReport(byte[] file, FileType typeFile,
                           LocalDate startDate, LocalDate endDate,
                           Customer customer, ReportStatus status) {
        this.file = file;
        this.typeFile = typeFile;
        this.startDate = startDate;
        this.endDate = endDate;
        this.customer = customer;
        this.status = status;
    }

    /**
     * Конструктор без параметров для JPA.
     */
    public FoodDiaryReport() {
    }

    /**
     * Устанавливает идентификатор отчета.
     *
     * @param id Новый идентификатор отчета.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Возвращает идентификатор отчета.
     *
     * @return Идентификатор отчета.
     */
    public Long getId() {
        return id;
    }

    /**
     * Возвращает файл отчета.
     *
     * @return Файл отчета.
     */
    public byte[] getFile() {
        return file;
    }

    /**
     * Устанавливает файл отчета.
     *
     * @param file Новый файл отчета.
     */
    public void setFile(byte[] file) {
        this.file = file;
    }

    /**
     * Возвращает тип файла отчета.
     *
     * @return Тип файла отчета.
     */
    public FileType getTypeFile() {
        return typeFile;
    }

    /**
     * Устанавливает тип файла отчета.
     *
     * @param typeFile Новый тип файла отчета.
     */
    public void setTypeFile(FileType typeFile) {
        this.typeFile = typeFile;
    }

    /**
     * Возвращает начальную дату отчета.
     *
     * @return Начальная дата отчета.
     */
    public LocalDate getStartDate() {
        return startDate;
    }

    /**
     * Устанавливает начальную дату отчета.
     *
     * @param startDate Новая начальная дата отчета.
     */
    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    /**
     * Возвращает конечную дату отчета.
     *
     * @return Конечная дата отчета.
     */
    public LocalDate getEndDate() {
        return endDate;
    }

    /**
     * Устанавливает конечную дату отчета.
     *
     * @param endDate Новая конечная дата отчета.
     */
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    /**
     * Возвращает клиента, которому принадлежит отчет.
     *
     * @return Клиент отчета.
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Устанавливает клиента, которому принадлежит отчет.
     *
     * @param customer Новый клиент отчета.
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     * Возвращает статус отчета.
     *
     * @return Статус отчета.
     */
    public ReportStatus getStatus() {
        return status;
    }

    /**
     * Устанавливает статус отчета.
     *
     * @param status Новый статус отчета.
     */
    public void setStatus(ReportStatus status) {
        this.status = status;
    }
}
