package ru.antoshkaxxr.JavaNaumenProject.Models;

/**
 * Представляет данные о новом пользователе-админе и введённом ключе
 */
public record DataForRegistrationAdmin(String name, String password, String secretKey) {
}
