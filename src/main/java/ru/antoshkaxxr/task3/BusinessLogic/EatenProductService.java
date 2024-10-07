package ru.antoshkaxxr.task3.BusinessLogic;

import ru.antoshkaxxr.task3.Entities.EatenProduct;

import java.util.Date;
import java.util.List;

public interface EatenProductService {
    boolean addEatenProduct(String name);

    boolean addEatenProduct(String name, Date eatingDate);

    List<EatenProduct> getProductsFromDate(Date date);

    List<EatenProduct> getProductsFromInterval(Date from, Date to);
}
