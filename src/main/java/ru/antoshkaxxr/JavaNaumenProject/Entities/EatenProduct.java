package ru.antoshkaxxr.JavaNaumenProject.Entities;

import java.util.Date;

public class EatenProduct extends Product {
    private Date eatingDate;

    public Date getEatingDate() {
        return eatingDate;
    }

    public void setEatingDate(Date eatingDate) {
        this.eatingDate = eatingDate;
    }
}
