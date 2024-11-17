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

    /**
     * Конструктор для создания категории продукта с указанным отображаемым именем.
     *
     * @param displayName Отображаемое имя категории.
     */
    ProductCategory(String displayName) {
        this.displayName = displayName;
    }

    /**
     * Возвращает отображаемое имя категории.
     *
     * @return Отображаемое имя категории.
     */
    @Override
    public String toString() {
        return displayName;
    }

    /**
     * Возвращает категорию продукта по ее отображаемому имени.
     *
     * @param displayName Отображаемое имя категории.
     * @return Категория продукта, соответствующая отображаемому имени, или null, если категория не найдена.
     */
    public static ProductCategory fromDisplayName(String displayName) {
        for (ProductCategory category : ProductCategory.values()) {
            if (category.displayName.equals(displayName)) {
                return category;
            }
        }
        return null;
    }
}
