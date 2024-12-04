package ru.antoshkaxxr.JavaNaumenProject;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import ru.antoshkaxxr.JavaNaumenProject.Entities.*;
import ru.antoshkaxxr.JavaNaumenProject.Enums.*;
import ru.antoshkaxxr.JavaNaumenProject.Models.DataForCreatingGoal;
import ru.antoshkaxxr.JavaNaumenProject.Repositories.*;
import ru.antoshkaxxr.JavaNaumenProject.Services.GoalServiceImpl;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class GoalTest {

    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final EatenProductRepository eatenProductRepository;
    private final FoodDiaryEntryRepository foodDiaryEntryRepository;
    private final GoalRepository goalRepository;
    private final GoalServiceImpl goalService;

    @Autowired
    public GoalTest(CustomerRepository customerRepository, ProductRepository productRepository,
                    EatenProductRepository eatenProductRepository, FoodDiaryEntryRepository foodDiaryEntryRepository,
                    GoalRepository goalRepository, GoalServiceImpl goalService) {
        this.customerRepository = customerRepository;
        this.productRepository = productRepository;
        this.eatenProductRepository = eatenProductRepository;
        this.foodDiaryEntryRepository = foodDiaryEntryRepository;
        this.goalRepository = goalRepository;
        this.goalService = goalService;
    }


    /**
     * Тестирование поиска целей определенного пользователя
     */
    @Test
    @Transactional
    @Rollback
    void testFindByCustomerId() {
        var customer1 = new Customer("Lucy", "mililuc@yandex.ru", 70.0, 170.0);
        customerRepository.save(customer1);
        var nameGoal1 = "Цель1";
        var goal1 = new Goal(
                nameGoal1, 2000.0,
                LocalDate.of(2024, 10, 12),
                500.0, 2000.0,
                WeightChangeMode.COMFORT_WEIGHT_GAIN, customer1
        );
        var nameGoal2 = "Цель2";
        var goal2 = new Goal(
                nameGoal2, 2000.0,
                LocalDate.of(2024, 10, 13),
                500.0, 1000.0,
                WeightChangeMode.COMFORT_WEIGHT_GAIN, customer1
        );
        goalRepository.save(goal1);
        goalRepository.save(goal2);

        var goalEntries = goalRepository.findByCustomerId(customer1.getId());
        var nameGoalOutDB1 = goalEntries.getFirst().getName();
        var nameGoalOutDB2 = goalEntries.getLast().getName();
        Assertions.assertEquals(2, goalEntries.size());
        Assertions.assertEquals(nameGoalOutDB1, nameGoal1);
        Assertions.assertEquals(nameGoalOutDB2, nameGoal2);
    }

    /**
     * Тестирование правильности сбора статистики
     */
    @Test
    @Transactional
    @Rollback
    void testFindStatisticFoodDiary() {
        var customer1 = new Customer("Lucy", "mililuc@yandex.ru", 70.0, 170.0);
        customerRepository.save(customer1);

        var product1 = new Product("apple", ProductCategory.FRUITS, 50.0);
        var product2 = new Product("fish", ProductCategory.FISH, 500.0);
        productRepository.save(product1);
        productRepository.save(product2);

        var eatenProduct1 = new EatenProduct(product1, LocalDate.of(2024, 10, 12), 2.0);
        var eatenProduct2 = new EatenProduct(product1, LocalDate.of(2024, 10, 13), 1.0);
        var eatenProduct3 = new EatenProduct(product2, LocalDate.of(2024, 10, 14), 0.5);
        var eatenProduct4 = new EatenProduct(product2, LocalDate.of(2024, 10, 13), 1.5);
        eatenProductRepository.save(eatenProduct1);
        eatenProductRepository.save(eatenProduct2);
        eatenProductRepository.save(eatenProduct3);
        eatenProductRepository.save(eatenProduct4);

        var foodDiaryEntry1 = new FoodDiaryEntry(customer1, eatenProduct1);
        var foodDiaryEntry2 = new FoodDiaryEntry(customer1, eatenProduct2);
        var foodDiaryEntry3 = new FoodDiaryEntry(customer1, eatenProduct3);
        var foodDiaryEntry4 = new FoodDiaryEntry(customer1, eatenProduct4);
        foodDiaryEntryRepository.save(foodDiaryEntry1);
        foodDiaryEntryRepository.save(foodDiaryEntry2);
        foodDiaryEntryRepository.save(foodDiaryEntry3);
        foodDiaryEntryRepository.save(foodDiaryEntry4);

        var goal = new Goal(
                "Цель", -1000.0,
                LocalDate.of(2024, 10, 12),
                -400.0, 2000.0,
                WeightChangeMode.COMFORT_WEIGHT_GAIN, customer1
        );
        goalRepository.save(goal);

        var goalEntries = goalRepository.findByCustomerId(customer1.getId());
        var goalOutDB = goalEntries.getFirst();
        var goalStatistic = goalService.getStatisticGoal(
                goalOutDB.getGoalId(),
                LocalDate.of(2024, 10, 12),
                LocalDate.of(2024, 10, 14)
        );

        LocalDate[] trueDates = {
                LocalDate.of(2024, 10, 12),
                LocalDate.of(2024, 10, 13),
                LocalDate.of(2024, 10, 14)
        };
        Double[] trueReallyConsumption = {50.0 * 2.0, 50.0 + 1.5 * 500.0, 0.5 * 500};
        Double[] truePlanConsumption = {1600.0, 1600.0, 1800.0};

        Assertions.assertArrayEquals(goalStatistic.labelsDate(), trueDates);
        Assertions.assertArrayEquals(goalStatistic.valuesRealConsumption(), trueReallyConsumption);
        Assertions.assertArrayEquals(goalStatistic.valuesPlanConsumption(), truePlanConsumption);
    }

    /**
     * Тестирование правильности составления цели по введённым данным
     */
    @Test
    @Transactional
    @Rollback
    void testCreateNewGoal() {
        var customer1 = new Customer("Lucy", "mililuc@yandex.ru", 70.0, 170.0);
        customerRepository.save(customer1);

        var dataForCreatingGoal = new DataForCreatingGoal(
                "Цель", 20, Sex.MALE, 90.0, 75.0, 180.0,
                PhysicalActivity.HIGH, CalculationFormula.MIFFLIN_ST_GEORGE, WeightChangeSpeed.COMFORT_SPEED
        );
        var goal = goalService.createGoal(dataForCreatingGoal, customer1);

        var stableAmountCalories = 3329.0;
        var caloriesNeedChange = (75.0 - 90.0) * 7700;
        var changeCaloriesPerDay = stableAmountCalories * (WeightChangeMode.COMFORT_WEIGHT_LOSS.getCoefficient() - 1);

        assertEquals(WeightChangeMode.COMFORT_WEIGHT_LOSS, goal.getWeightChangeMode());
        assertTrue(goal.getCaloriesNeedChange() >= caloriesNeedChange - 3 &&
                goal.getCaloriesNeedChange() <= caloriesNeedChange + 3);
        assertTrue(goal.getCaloriesChangeToPlanPerDay() >= changeCaloriesPerDay - 3 &&
                goal.getCaloriesChangeToPlanPerDay() <= changeCaloriesPerDay + 3);
        assertTrue(goal.getCaloriesStablePerDay() >= stableAmountCalories - 3 &&
                goal.getCaloriesStablePerDay() <= stableAmountCalories + 3);
    }
}
