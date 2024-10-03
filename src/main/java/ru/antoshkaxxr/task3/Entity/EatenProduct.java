package ru.antoshkaxxr.task3.Entity;

import java.util.Date;

public class EatenProduct {
    private Product product;
    private Date eatingDate;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Date getEatingDate() {
        return eatingDate;
    }

    public void setEatingDate(Date eatingDate) {
        this.eatingDate = eatingDate;
    }
}
