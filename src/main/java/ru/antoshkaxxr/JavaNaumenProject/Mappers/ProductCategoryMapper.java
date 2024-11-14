package ru.antoshkaxxr.JavaNaumenProject.Mappers;

import java.util.HashMap;
import java.util.Map;
import ru.antoshkaxxr.JavaNaumenProject.Enums.ProductCategory;

/**
 * Класс для сопоставления категорий продуктов с русскими названиями
 */
public final class ProductCategoryMapper {

    private static final Map<ProductCategory, String> CATEGORY_MAP = new HashMap<>();

    static {
        CATEGORY_MAP.put(ProductCategory.MEAT, "Мясо");
        CATEGORY_MAP.put(ProductCategory.FISH, "Рыба");
        CATEGORY_MAP.put(ProductCategory.EGGS, "Яичные продукты");
        CATEGORY_MAP.put(ProductCategory.DAIRY, "Молочные продукты");
        CATEGORY_MAP.put(ProductCategory.SOY, "Продукты, содержащие сою");
        CATEGORY_MAP.put(ProductCategory.VEGETABLES, "Овощи");
        CATEGORY_MAP.put(ProductCategory.FRUITS, "Фрукты");
        CATEGORY_MAP.put(ProductCategory.GREENERY, "Зелень");
        CATEGORY_MAP.put(ProductCategory.MUSHROOMS, "Грибы");
        CATEGORY_MAP.put(ProductCategory.OILS, "Масла");
        CATEGORY_MAP.put(ProductCategory.NUTS, "Орехи");
        CATEGORY_MAP.put(ProductCategory.GRITS, "Крупы");
        CATEGORY_MAP.put(ProductCategory.SPICES, "Специи");
        CATEGORY_MAP.put(ProductCategory.FLOUR, "Мучные изделия");
        CATEGORY_MAP.put(ProductCategory.SWEETS, "Сладости");
        CATEGORY_MAP.put(ProductCategory.FAST_FOOD, "Фастфуд");
        CATEGORY_MAP.put(ProductCategory.SOUPS, "Супы");
        CATEGORY_MAP.put(ProductCategory.DRINKS, "Напитки");
    }

    /**
     * Приватный конструктор для предотвращения создания экземпляров класса.
     */
    private ProductCategoryMapper() {
        throw new UnsupportedOperationException("Utility class");
    }

    /**
     * Получить русское название категории
     *
     * @param category Категория продукта
     * @return Русское название категории
     */
    public static String getRussianName(ProductCategory category) {
        return CATEGORY_MAP.get(category);
    }

    /**
     * Получить категорию по русскому названию
     *
     * @param russianName Русское название категории
     * @return Категория продукта
     */
    public static ProductCategory getCategoryByRussianName(String russianName) {
        for (Map.Entry<ProductCategory, String> entry : CATEGORY_MAP.entrySet()) {
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
        return CATEGORY_MAP;
    }
}
