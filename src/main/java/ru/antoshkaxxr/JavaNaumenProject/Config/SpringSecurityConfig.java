package ru.antoshkaxxr.JavaNaumenProject.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Класс конфигурации для настройки безопасности Spring.
 */
@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
    /**
     * Создает и возвращает экземпляр PasswordEncoder, использующий BCrypt для хеширования паролей.
     *
     * @return Экземпляр PasswordEncoder
     */
    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Настраивает цепочку фильтров безопасности для обработки запросов.
     *
     * @param http Объект HttpSecurity для настройки правил безопасности
     * @return Настроенный объект SecurityFilterChain
     * @throws Exception Если произошла ошибка при настройке безопасности
     */
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((authz) -> authz
                        .requestMatchers("/registration", "/login",
                                "/logout", "/registration/admin")
                        .permitAll()
                        .requestMatchers("/swagger-ui/index.html", "/monitoring").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .csrf(csrf -> csrf.ignoringRequestMatchers("/reports/create"));
        return http.build();
    }
}
