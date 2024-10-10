package ru.antoshkaxxr.JavaNaumenProject.BusinessLogic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.antoshkaxxr.JavaNaumenProject.DataAccessLayer.EatenProductRepository;
import ru.antoshkaxxr.JavaNaumenProject.DataAccessLayer.ProductRepository;
import ru.antoshkaxxr.JavaNaumenProject.Entities.EatenProduct;
import ru.antoshkaxxr.JavaNaumenProject.Entities.Product;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Service
public class EatenProductServiceImpl implements EatenProductService {
    private final ProductRepository productRepository;
    private final EatenProductRepository eatenProductRepository;

    @Autowired
    public EatenProductServiceImpl(ProductRepository productRepository, EatenProductRepository eatenProductRepository) {
        this.productRepository = productRepository;
        this.eatenProductRepository = eatenProductRepository;
    }

    @Override
    public boolean addEatenProduct(String name) {
        LocalDateTime today = LocalDate.now().atStartOfDay();
        return addEatenProduct(name, Date.from(today.atZone(ZoneId.systemDefault()).toInstant()));
    }

    @Override
    public boolean addEatenProduct(String name, Date eatingDate) {
        Product product = productRepository.read((long) name.hashCode());
        if (product == null) {
            return false;
        }

        EatenProduct eatenProduct = new EatenProduct();
        eatenProduct.setId(product.getId());
        eatenProduct.setName(name);
        eatenProduct.setCaloriesNumber(product.getCaloriesNumber());
        eatenProduct.setEatingDate(eatingDate);
        eatenProductRepository.add(eatenProduct);
        return true;
    }

    @Override
    public List<EatenProduct> getProductsFromDate(Date date) {
        return eatenProductRepository.getInDate(date);
    }

    @Override
    public List<EatenProduct> getProductsFromInterval(Date from, Date to) {
        return eatenProductRepository.getInInterval(from, to);
    }
}
