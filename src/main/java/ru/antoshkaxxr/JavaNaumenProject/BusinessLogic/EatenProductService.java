package ru.antoshkaxxr.JavaNaumenProject.BusinessLogic;

import ru.antoshkaxxr.JavaNaumenProject.Entities.EatenProduct;

import java.util.Date;
import java.util.List;

public interface EatenProductService {
    boolean addEatenProduct(String name);

    boolean addEatenProduct(String name, Date eatingDate);

    List<EatenProduct> getProductsFromDate(Date date);

    List<EatenProduct> getProductsFromInterval(Date from, Date to);
}
