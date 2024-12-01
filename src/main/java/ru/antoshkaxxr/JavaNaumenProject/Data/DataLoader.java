package ru.antoshkaxxr.JavaNaumenProject.Data;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import ru.antoshkaxxr.JavaNaumenProject.Services.CsvDataLoaderService;

@Component
public class DataLoader implements CommandLineRunner {

    private final CsvDataLoaderService csvDataLoaderService;

    @Autowired
    public DataLoader(CsvDataLoaderService csvDataLoaderService) {
        this.csvDataLoaderService = csvDataLoaderService;
    }

    @Override
    public void run(String... args) throws Exception {
        csvDataLoaderService.loadBaseProductsFromCsv();
    }
}
