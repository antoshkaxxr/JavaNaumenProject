package ru.antoshkaxxr.JavaNaumenProject.Entities;

import jakarta.persistence.*;
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

    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
    }

    public ReportStatus getStatus() {
        return status;
    }

    public void setStatus(ReportStatus status) {
        this.status = status;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
