package ru.antoshkaxxr.JavaNaumenProject.Services;

import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import ru.antoshkaxxr.JavaNaumenProject.Entities.Product;
import ru.antoshkaxxr.JavaNaumenProject.Enums.ProductCategory;

import java.io.FileReader;

@Service
public class CsvDataLoaderService {

    private final BaseProductServiceImpl baseProductService;

    @Autowired
    public CsvDataLoaderService(BaseProductServiceImpl baseProductService) {
        this.baseProductService = baseProductService;
    }

    public void loadBaseProductsFromCsv() {
        try (CSVReader reader = new CSVReader(new FileReader(new ClassPathResource("base_products.csv").getFile()))) {
            String[] line;
            reader.readNext();
            while ((line = reader.readNext()) != null) {
                String name = line[0];
                ProductCategory category = ProductCategory.valueOf(line[1]);
                Double caloriesNumberHundred = Double.parseDouble(line[2]);
                baseProductService.addBaseProduct(new Product(name, category, caloriesNumberHundred));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
