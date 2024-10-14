package ru.antoshkaxxr.JavaNaumenProject.Entities;

import jakarta.persistence.*;

@Entity
@Table(name = "rating")
public class Rating {
    @Id
    @GeneratedValue
    private Long recordId;

    @ManyToOne
    private Customer customer;

    @ManyToOne
    private Product product;

    @Column(nullable = false)
    private Integer tasteAssessment;

    @Column(nullable = false)
    private Integer usefulnessAssessment;

    @Column(nullable = false)
    private String annotation;

    public Rating(Customer customer, Product product, Integer tasteAssessment, Integer usefulnessAssessment, String annotation) {
        this.customer = customer;
        this.product = product;
        this.tasteAssessment = tasteAssessment;
        this.usefulnessAssessment = usefulnessAssessment;
        this.annotation = annotation;
    }

    public Rating() {

    }

    public Long getRecordId() {
        return recordId;
    }

    public void setRecordId(Long recordId) {
        this.recordId = recordId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getTasteAssessment() {
        return tasteAssessment;
    }

    public void setTasteAssessment(Integer tasteAssessment) {
        this.tasteAssessment = tasteAssessment;
    }

    public Integer getUsefulnessAssessment() {
        return usefulnessAssessment;
    }

    public void setUsefulnessAssessment(Integer usefulnessAssessment) {
        this.usefulnessAssessment = usefulnessAssessment;
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(String annotation) {
        this.annotation = annotation;
    }
}
