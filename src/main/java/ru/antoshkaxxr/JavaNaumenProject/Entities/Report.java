package ru.antoshkaxxr.JavaNaumenProject.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import ru.antoshkaxxr.JavaNaumenProject.Enums.ReportStatus;

/**
 * Класс-сущность, представляющий отчет.
 * Содержит информацию об уникальном идентификаторе отчета, статусе и содержании.
 * Этот класс соответствует таблице report в базе данных.
 */
@Entity
@Table
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "report_seq")
    @SequenceGenerator(name = "report_seq", sequenceName = "report_seq", allocationSize = 1)
    private Long reportId;

    @Column
    private ReportStatus status;

    @Column(columnDefinition = "TEXT")
    private String content;

    /**
     * Конструктор по умолчанию.
     * Устанавливает статус отчета в значение CREATED.
     */
    public Report() {
        setStatus(ReportStatus.CREATED);
    }

    /**
     * Возвращает уникальный идентификатор отчета.
     *
     * @return Уникальный идентификатор отчета.
     */
    public Long getReportId() {
        return reportId;
    }

    /**
     * Устанавливает уникальный идентификатор отчета.
     *
     * @param reportId Уникальный идентификатор отчета.
     */
    public void setReportId(Long reportId) {
        this.reportId = reportId;
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
     * @param status Статус отчета.
     */
    public void setStatus(ReportStatus status) {
        this.status = status;
    }

    /**
     * Возвращает содержание отчета.
     *
     * @return Содержание отчета.
     */
    public String getContent() {
        return content;
    }

    /**
     * Устанавливает содержание отчета.
     *
     * @param content Содержание отчета.
     */
    public void setContent(String content) {
        this.content = content;
    }
}
