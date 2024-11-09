package ru.antoshkaxxr.JavaNaumenProject.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import ru.antoshkaxxr.JavaNaumenProject.Enums.ReportStatus;

@Entity
@Table
public class Report {
    @Id
    @GeneratedValue
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
