package ru.antoshkaxxr.JavaNaumenProject.Models;

/**
 * Представляет данные о новом пользователе-админе и введённом ключе
 */
public record DataForRegistrationAdmin(String name, String email, Double weight,
                                       Double height, String password, String secretKey) {
}
