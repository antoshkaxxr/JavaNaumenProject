package ru.antoshkaxxr.JavaNaumenProject.Enums;

/**
 * Перечисление для категорий продуктов
 */
public enum ProductCategory {
    MEAT("Мясо"),
    FISH("Рыба"),
    EGGS("Яичные продукты"),
    DAIRY("Молочные продукты"),
    SOY("Продукты, содержащие сою"),
    VEGETABLES("Овощи"),
    FRUITS("Фрукты"),
    GREENERY("Зелень"),
    MUSHROOMS("Грибы"),
    OILS("Масла"),
    NUTS("Орехи"),
    GRITS("Крупы"),
    SPICES("Специи"),
    FLOUR("Мучные изделия"),
    SWEETS("Сладости"),
    FAST_FOOD("Фастфуд"),
    SOUPS("Супы"),
    DRINKS("Напитки");

    private final String displayName;

    ProductCategory(String displayName) {
        this.displayName = displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }

    public static ProductCategory fromDisplayName(String displayName) {
        for (ProductCategory category : ProductCategory.values()) {
            if (category.displayName.equals(displayName)) {
                return category;
            }
        }
        return null;
    }
}
