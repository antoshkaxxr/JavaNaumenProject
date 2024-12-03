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

    public FoodDiaryReport(byte[] file, FileType typeFile,
                           LocalDate startDate, LocalDate endDate,
                           Customer customer) {
        this.file = file;
        this.typeFile = typeFile;
        this.startDate = startDate;
        this.endDate = endDate;
        this.customer = customer;
    }

    public FoodDiaryReport() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public FileType getTypeFile() {
        return typeFile;
    }

    public void setTypeFile(FileType typeFile) {
        this.typeFile = typeFile;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
