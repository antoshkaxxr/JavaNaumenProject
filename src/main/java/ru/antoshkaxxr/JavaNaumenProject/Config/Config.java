package ru.antoshkaxxr.JavaNaumenProject.Config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Класс конфигурации для инициализации интерфейса командной строки.
 */
@Configuration
public class Config {
    private static final Logger LOGGER = LoggerFactory.getLogger(Config.class);

    /**
     * Выводит доступные команды в консоль.
     *
     * @return Экземпляр CommandLineRunner
     */
    @Bean
    public CommandLineRunner commandScanner() {
        return args -> {
            try {
                LOGGER.info("Приложение успешно запущено");
            } catch (Exception e) {
                LOGGER.error("Произошла ошибка при инициализации интерфейса командной строки", e);
            }
        };
    }
}
