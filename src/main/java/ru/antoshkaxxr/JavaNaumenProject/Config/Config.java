package ru.antoshkaxxr.JavaNaumenProject.Config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Config {
    @Bean
    public CommandLineRunner commandScanner() {
        return args -> {
            try {
                System.out.println("Введите команду: ");
                System.out.println("1) create <productName> <caloriesNumber>");
                System.out.println("2) delete <productName>");
                System.out.println("3) update <productName> <newCaloriesNumber>");
                System.out.println("4) eat <productName> (<eatingDate>)?");
                System.out.println("5) statistic <date> OR statistic <date1> <date2>");
                System.out.println("6) exit");
                System.out.println("* date format: YYYY-MM-DD");
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
    }
}
