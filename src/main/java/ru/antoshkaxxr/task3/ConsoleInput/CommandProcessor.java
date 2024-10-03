package ru.antoshkaxxr.task3.ConsoleInput;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.antoshkaxxr.task3.BusinessLogic.EatenProductService;
import ru.antoshkaxxr.task3.BusinessLogic.ProductService;
import ru.antoshkaxxr.task3.Entity.EatenProduct;
import ru.antoshkaxxr.task3.Entity.Product;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Component
public class CommandProcessor {
    private final ProductService productService;
    private final EatenProductService eatenProductService;

    @Autowired
    public CommandProcessor(ProductService productService, EatenProductService eatenProductService) {
        this.productService = productService;
        this.eatenProductService = eatenProductService;
    }

    public void processCommand(String input) {
        String[] cmd = input.split(" ");
        switch (cmd[0]) {
            case "create" -> {
                if (cmd.length < 3) {
                    System.out.println("Недостаточно аргументов для команды 'create'.");
                    break;
                }

                try {
                    String productName = cmd[1];
                    Integer caloriesNumber = Integer.valueOf(cmd[2]);
                    if (productService.createProduct(productName, caloriesNumber)) {
                        System.out.printf("Продукт %s калорийностью %d успешно добавлен.%n", productName, caloriesNumber);
                    } else {
                        System.out.printf("Продукт %s уже существует в каталоге!%n", productName);
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Количество калорий должно быть числом.");
                }
            }

            case "delete" -> {
                if (cmd.length < 2) {
                    System.out.println("Недостаточно аргументов для команды 'delete'.");
                    break;
                }

                String productName = cmd[1];
                if (productService.deleteProduct(productName)) {
                    System.out.printf("Продукт %s успешно удален.%n", productName);
                } else {
                    System.out.printf("Продукт %s отсутствует в каталоге, удаление невозможно.%n", productName);
                }
            }

            case "update" -> {
                if (cmd.length < 3) {
                    System.out.println("Недостаточно аргументов для команды 'update'.");
                    break;
                }

                try {
                    String productName = cmd[1];
                    Integer caloriesNumber = Integer.valueOf(cmd[2]);
                    if (productService.updateProduct(productName, caloriesNumber)) {
                        System.out.printf("Продукт %s успешно обновлен: теперь его калорийность равна %d.%n", productName, caloriesNumber);
                    } else {
                        System.out.printf("Продукт %s отсутствует в каталоге: обновление невозможно.%n", productName);
                    }

                } catch (NumberFormatException e) {
                    System.out.println("Новое число калорий должно быть числом.");
                }
            }

            case "eat" -> {
                if (cmd.length < 2) {
                    System.out.println("Недостаточно аргументов для команды 'eat'.");
                    break;
                }

                String productName = cmd[1];
                if (cmd.length == 2) {
                    if (eatenProductService.addEatenProduct(productName)) {
                        System.out.printf("Информация о съеденном продукте %s внесена в журнал с указанием текущего времени %n", productName);
                    } else {
                        System.out.printf("Продукт %s отсутствует в каталоге: невозможно занести информацию в журнал.%n", productName);
                    }
                } else {
                    String dateString = cmd[2];
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    try {
                        Date eatingDate = dateFormat.parse(dateString);
                        if (eatenProductService.addEatenProduct(productName, eatingDate)) {
                            System.out.printf("Информация о съеденном продукте %s внесена в журнал с указанием даты %s %n", productName, dateString);
                        } else {
                            System.out.printf("Продукт %s отсутствует в каталоге: невозможно занести информацию в журнал.%n", productName);
                        }
                    } catch (ParseException e) {
                        System.out.println("Неверный формат даты. Ожидается: yyyy-MM-dd");
                    }
                }
            }

            case "statistic" -> {
                if (cmd.length < 2) {
                    System.out.println("Недостаточно аргументов для команды 'statistic'.");
                    break;
                }

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                List<EatenProduct> eatenProducts;
                try {
                    String date1String = cmd[1];
                    Date date1 = dateFormat.parse(date1String);

                    if (cmd.length == 2) {
                        eatenProducts = eatenProductService.getProductsFromDate(date1);
                    } else {
                        String date2String = cmd[2];
                        Date date2 = dateFormat.parse(date2String);
                        eatenProducts = eatenProductService.getProductsFromInterval(date1, date2);
                    }

                    if (eatenProducts.isEmpty()) {
                        System.out.println("В журнале отсутствует информация о съеденных продуктах за указанный период.");
                        break;
                    }

                    Integer overallCalories = 0;
                    for (EatenProduct eatenProduct : eatenProducts) {
                        Product product = eatenProduct.getProduct();
                        String formattedDate = dateFormat.format(eatenProduct.getEatingDate());
                        overallCalories += product.getCaloriesNumber();
                        System.out.printf("%s %s %d cal.%n", formattedDate, product.getName(), product.getCaloriesNumber());
                    }

                    System.out.printf("Общее число калорий за период: %d cal.%n", overallCalories);
                } catch (ParseException e) {
                    System.out.println("Неверный формат даты. Ожидается: yyyy-MM-dd");
                }
            }

            default -> System.out.println("Введена неизвестная команда...");
        }
    }
}