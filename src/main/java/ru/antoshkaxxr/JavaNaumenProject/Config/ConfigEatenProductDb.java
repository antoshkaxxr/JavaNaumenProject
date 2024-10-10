package ru.antoshkaxxr.JavaNaumenProject.Config;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import ru.antoshkaxxr.JavaNaumenProject.Entities.EatenProduct;
import ru.antoshkaxxr.JavaNaumenProject.IdGenerator;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class ConfigEatenProductDb {
    @Bean
    @Scope(value = BeanDefinition.SCOPE_SINGLETON)
    public Map<Long, EatenProduct> eatenProductContainer() {
        return new HashMap<>();
    }

    @Bean
    @Scope(value = BeanDefinition.SCOPE_SINGLETON)
    public IdGenerator idGenerator() {
        return new IdGenerator();
    }
}
