package ru.antoshkaxxr.task3.Entities;

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
