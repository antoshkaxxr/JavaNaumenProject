package ru.antoshkaxxr.JavaNaumenProject.Mappers;

import java.util.HashMap;
import java.util.Map;

import ru.antoshkaxxr.JavaNaumenProject.Enums.ProductCategory;

/**
 * Класс для сопоставления категорий продуктов с русскими названиями
 */
public class ProductCategoryMapper {

    private static final Map<ProductCategory, String> categoryMap = new HashMap<>();

    static {
        categoryMap.put(ProductCategory.MEAT, "Мясо");
        categoryMap.put(ProductCategory.FISH, "Рыба");
        categoryMap.put(ProductCategory.EGGS, "Яичные продукты");
        categoryMap.put(ProductCategory.DAIRY, "Молочные продукты");
        categoryMap.put(ProductCategory.SOY, "Продукты, содержащие сою");
        categoryMap.put(ProductCategory.VEGETABLES, "Овощи");
        categoryMap.put(ProductCategory.FRUITS, "Фрукты");
        categoryMap.put(ProductCategory.GREENERY, "Зелень");
        categoryMap.put(ProductCategory.MUSHROOMS, "Грибы");
        categoryMap.put(ProductCategory.OILS, "Масла");
        categoryMap.put(ProductCategory.NUTS, "Орехи");
        categoryMap.put(ProductCategory.GRITS, "Крупы");
        categoryMap.put(ProductCategory.SPICES, "Специи");
        categoryMap.put(ProductCategory.FLOUR, "Мучные изделия");
        categoryMap.put(ProductCategory.SWEETS, "Сладости");
        categoryMap.put(ProductCategory.FAST_FOOD, "Фастфуд");
        categoryMap.put(ProductCategory.SOUPS, "Супы");
        categoryMap.put(ProductCategory.DRINKS, "Напитки");
    }

    /**
     * Получить русское название категории
     *
     * @param category Категория продукта
     * @return Русское название категории
     */
    public static String getRussianName(ProductCategory category) {
        return categoryMap.get(category);
    }

    /**
     * Получить категорию по русскому названию
     *
     * @param russianName Русское название категории
     * @return Категория продукта
     */
    public static ProductCategory getCategoryByRussianName(String russianName) {
        for (Map.Entry<ProductCategory, String> entry : categoryMap.entrySet()) {
            if (entry.getValue().equals(russianName)) {
                return entry.getKey();
            }
        }
        return null;
    }

    /**
     * Получить сопоставление категорий с русскими названиями
     *
     * @return Сопоставление категорий с русскими названиями
     */
    public static Map<ProductCategory, String> getCategoryMap() {
        return categoryMap;
    }
}
