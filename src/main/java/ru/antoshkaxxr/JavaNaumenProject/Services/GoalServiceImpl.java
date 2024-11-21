package ru.antoshkaxxr.JavaNaumenProject.Services;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import ru.antoshkaxxr.JavaNaumenProject.Entities.Customer;
import ru.antoshkaxxr.JavaNaumenProject.Entities.FoodDiaryEntry;
import ru.antoshkaxxr.JavaNaumenProject.Entities.Goal;
import ru.antoshkaxxr.JavaNaumenProject.Enums.WeightChangeMode;
import ru.antoshkaxxr.JavaNaumenProject.Enums.WeightChangeSpeed;
import ru.antoshkaxxr.JavaNaumenProject.Models.CalculatorData;
import ru.antoshkaxxr.JavaNaumenProject.Models.DataForCreatingGoal;
import ru.antoshkaxxr.JavaNaumenProject.Models.DataStatistic;
import ru.antoshkaxxr.JavaNaumenProject.Repositories.FoodDiaryEntryRepository;
import ru.antoshkaxxr.JavaNaumenProject.Repositories.GoalRepository;


/**
 * Сервис для составления цели по изменению веса и статистику по достижению этой цели
 *
 * <p>Класс предоставляет метод createGoal,
 * который по данный от пользователя о цели
 * создаёт эту цель и возвращает её
 * Предоставляет getStatisticGoal , который возвращает статистику по цели
 * Предоставляет deleteGoal, который удаляет цель</p>
 */
@Service
@SuppressWarnings("checkstyle:MagicNumber")
public class GoalServiceImpl {
    private final GoalRepository goalRepository;
    private final FoodDiaryEntryRepository foodDiaryEntryRepository;

    /**
     * Конструктор для инициализации сервиса.
     * @param goalRepository репозиторий для работы с целями
     * @param foodDiaryEntryRepository репозиторий для работы с приёмами пищи
     */
    @Autowired
    public GoalServiceImpl(GoalRepository goalRepository, FoodDiaryEntryRepository foodDiaryEntryRepository) {
        this.goalRepository = goalRepository;
        this.foodDiaryEntryRepository = foodDiaryEntryRepository;
    }

    /**
     * По id цели удаляет её
     * @param idGoal id цели, которая удалится
     */
    public void deleteGoal(Long idGoal) {
        var currGoal = goalRepository.findById(idGoal);
        if (currGoal.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        goalRepository.delete(currGoal.get());
    }

    /**
     * По id и пользователю цели собирает все его приёмы пищи начиная с создания цели,
     * id которой мы передали
     * @param idGoal id цели, данные по которой мы ищем в базе
     * @param start время с которого мы смотрим статистику
     * @param end время по которое мы смотрим статистику
     * @return статистика по цели за запрошенный период
     */
    public DataStatistic getStatisticGoal(Long idGoal, LocalDate start, LocalDate end) {
        var dataCreateGoal = getDateCreateGoal(idGoal);

        var listAllFoodDiaries = getListAllFoodDiariesFromDateCreateGoal(idGoal);
        var dictForFoodConsumptionStatistic = getSortedDictForFoodConsumptionStatistic(listAllFoodDiaries);
        var statisticConsumptionFoodAccordingPlan = getStatisticConsumptionFoodAccordingPlan(idGoal);
        var dataStatistic = getDataStatistic(
                dictForFoodConsumptionStatistic,
                statisticConsumptionFoodAccordingPlan,
                dataCreateGoal
        );

        var leftBorder = start;
        var rightBorder = end;
        if (start == null) {
            leftBorder = dataCreateGoal;
        }
        if (end == null) {
            rightBorder = dataCreateGoal.plusDays(statisticConsumptionFoodAccordingPlan.length - 1);
        }
        return trimArray(dataStatistic, leftBorder, rightBorder, dataCreateGoal);
    }

    /**
     * По id цели определяет дату её создания,
     * @param idGoal id цели, данные по которой мы ищем в базе
     * @return дата создания цели
     */
    private LocalDate getDateCreateGoal(Long idGoal) {
        var currGoal = goalRepository.findById(idGoal);
        if (currGoal.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        return currGoal.get().getStartDate();
    }

    /**
     * По id и пользователю цели собирает все его приёмы пищи начиная с создания цели,
     * id которой мы передали
     * @param idGoal id цели, данные по которой мы ищем в базе
     * @return приёмы пищи пользователя, начиная с создания цели
     */
    private List<FoodDiaryEntry> getListAllFoodDiariesFromDateCreateGoal(Long idGoal) {
        var currGoal = goalRepository.findById(idGoal);
        if (currGoal.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        var customerId = currGoal.get().getCustomer().getId();
        var dateCreateGoal = currGoal.get().getStartDate();
        return foodDiaryEntryRepository.findByCustomerIdAndDateFrom(customerId, dateCreateGoal);
    }

    /**
     * Анализирует приёмы пищи пользователя и возвращает промежуточную структуру для создания статистики
     * @param foodDiaryEntries приёмы пищи, начиная с создания цели
     * @return промежуточная структура, которая далее будет использоваться для получения статистики
     */
    private TreeMap<LocalDate, Double> getSortedDictForFoodConsumptionStatistic(List<FoodDiaryEntry> foodDiaryEntries) {
        var dict = new TreeMap<LocalDate, Double>();
        for (var foodDiaryEntry: foodDiaryEntries) {
            var date = foodDiaryEntry.getEatenProduct().getEatingDate();
            var calories = foodDiaryEntry.getEatenProduct().getProduct().getCaloriesNumberHundred();
            var amount = foodDiaryEntry.getEatenProduct().getEatenAmount();
            if (dict.containsKey(date)) {
                dict.put(date, dict.get(date) + calories * amount);
            } else {
                dict.put(date, calories * amount);
            }
        }
        return dict;
    }

    /**
     * (дата конца диеты - дата начала диеты) = Изменённые калории / изменяемые калории в день
     * По id поставленной цели возвращает план потребления калорий по дням с начала постановки цели
     * @param idGoal id цели
     * @return массив потребления калорий по дням
     */
    private Double[] getStatisticConsumptionFoodAccordingPlan(Long idGoal) {
        var currGoal = goalRepository.findById(idGoal);
        if (currGoal.isEmpty()) {
            throw new ResourceNotFoundException();
        }
        var goal = currGoal.get();
        var lengthArrayStatistic = 0;
        if (goal.getCaloriesChangeToPlanPerDay() == 0) {
            lengthArrayStatistic = 30;
        } else {
            lengthArrayStatistic = (int) Math.ceil(goal.getCaloriesNeedChange() / goal.getCaloriesChangeToPlanPerDay());
        }
        var statisticConsumptionFoodAccordingPlan = new Double[lengthArrayStatistic];
        for (var i = 0; i < lengthArrayStatistic - 1; i++) {
            var consumptionInDay = goal.getCaloriesStablePerDay() + goal.getCaloriesChangeToPlanPerDay();
            statisticConsumptionFoodAccordingPlan[i] = consumptionInDay;
        }
        var deltaConsumptionCaloriesStablePerDay = (lengthArrayStatistic - 1) * goal.getCaloriesChangeToPlanPerDay();
        var remainder = goal.getCaloriesNeedChange() - deltaConsumptionCaloriesStablePerDay;
        var lastDayConsumption = goal.getCaloriesStablePerDay() + remainder;
        statisticConsumptionFoodAccordingPlan[lengthArrayStatistic - 1] = lastDayConsumption;
        return statisticConsumptionFoodAccordingPlan;
    }

    /**
     * Из промежуточной структуры для создания статистики создаёт два массива, где на i-ом
     * индексе в одном массиве находится дата, а во втором на i-ой позиции кол-во калорий
     * употреблённых в этот день
     * @param dictForFoodConsumptionStatistic промежуточная структура для создания статистики
     * @param statisticConsumptionFoodAccordingPlan данные о потреблении по плану калорий
     * @param dataCreateGoal дата создания цели
     * @return дто, где хранятся два массива описанных выше
     */
    private DataStatistic getDataStatistic(TreeMap<LocalDate, Double> dictForFoodConsumptionStatistic,
                                           Double[] statisticConsumptionFoodAccordingPlan,
                                           LocalDate dataCreateGoal) {
        var startDay = convertDateInDays(dataCreateGoal);
        var dates = new LocalDate[statisticConsumptionFoodAccordingPlan.length];
        var consumptionReal = new Double[statisticConsumptionFoodAccordingPlan.length];
        Arrays.fill(consumptionReal, 0.0);
        for (var i = 0; i < statisticConsumptionFoodAccordingPlan.length; i++) {
            dates[i] = dataCreateGoal.plusDays(i);
        }
        for (var key: dictForFoodConsumptionStatistic.keySet()) {
            var index = convertDateInDays(key) - startDay;
            if (index >= statisticConsumptionFoodAccordingPlan.length) {
                continue;
            }
            consumptionReal[index] += dictForFoodConsumptionStatistic.get(key);
        }
        return new DataStatistic(dates, consumptionReal, statisticConsumptionFoodAccordingPlan);
    }

    /**
     * Образка массива в соответствии с датами между которыми нам нужна статистика
     * @param dataStatistic данные о статистике достижения цели
     * @param leftBorder момент с которого нужна статистика(включительно)
     * @param rightBorder момент до которого нужна статистика(включительно)
     * @param dataCreateGoal момент с которого ведётся статистика
     * @return обрезанный массив со статистикой
     */
    private DataStatistic trimArray(DataStatistic dataStatistic, LocalDate leftBorder,
                             LocalDate rightBorder, LocalDate dataCreateGoal) {
        var leftIndex = convertDateInDays(leftBorder) - convertDateInDays(dataCreateGoal);
        var rightIndex = convertDateInDays(rightBorder) - convertDateInDays(dataCreateGoal);
        var labelsDate = Arrays.copyOfRange(dataStatistic.labelsDate(), leftIndex, rightIndex + 1);
        var valuesConsumptionReal = Arrays.copyOfRange(
                dataStatistic.valuesRealConsumption(),
                leftIndex, rightIndex + 1);
        var valuesConsumptionPlan = Arrays.copyOfRange(
                dataStatistic.valuesPlanConsumption(),
                leftIndex, rightIndex + 1);
        return new DataStatistic(labelsDate, valuesConsumptionReal, valuesConsumptionPlan);
    }

    /**
     * Представление даты в кол-во дней, которое прошло с момента начала эпохи UNIX
     * @param date как быстро хочется поправится
     * @return режим набора веса
     */
    private Integer convertDateInDays(LocalDate date) {
        return (int) (date.toEpochDay());
    }

    /**
     * Основной метод, который создаёт цель,
     * созданную на основе данных, введённых пользователем
     *
     * @param dataForCreatingGoal Данные для создания цели: вес, рост, возраст, пол и другое
     * @param customer Пользователь, для которого мы составляем цель
     * @return созданная цель, созданная на основе данных, введённых пользователем
     */
    public Goal createGoal(DataForCreatingGoal dataForCreatingGoal, Customer customer) {
        var dataOfCalculator = CalculatorServiceImpl.getCalculatedData(
                new CalculatorData(
                        dataForCreatingGoal.age(),
                        dataForCreatingGoal.sex(),
                        dataForCreatingGoal.currentWeight(),
                        dataForCreatingGoal.heightInSm(),
                        dataForCreatingGoal.physicalActivity(),
                        dataForCreatingGoal.formula()
                )
        );
        var caloriesNeedChange = getCaloriesNeedChange(dataForCreatingGoal);
        var weightChangeMode = getWeightChangeMode(dataForCreatingGoal);
        var caloriesChangeToPlanPerDay = getCaloriesChangeToPlanPerDay(
                dataOfCalculator.bounds().stable(),
                weightChangeMode
        );
        return new Goal(dataForCreatingGoal.name(), caloriesNeedChange,
                LocalDate.now(), caloriesChangeToPlanPerDay, dataOfCalculator.bounds().stable(),
                weightChangeMode, customer);
    }

    /**
     * Формула расчёта изменения калорий по разнице веса
     *
     * @param dataForCreatingGoal Данные для создания цели: вес текущий,
     * вес, которого хотим достичь и другое
     * @return необходимое кол-во калорий, которое нужно изменить
     */
    private Double getCaloriesNeedChange(DataForCreatingGoal dataForCreatingGoal) {
        return (dataForCreatingGoal.goalWeight() - dataForCreatingGoal.currentWeight()) * 7700;
    }

    /**
     * Формула расчёта калорий, которые по плану стоит сжигать/набирать в день
     * @param stableAmountCalories кол-во калорий для поддержания текущего веса,
     * @param weightChangeMode режим сброса веса
     * @return калории, которые по плану стоит сжигать/набирать в день
     */
    private Double getCaloriesChangeToPlanPerDay(Double stableAmountCalories,
                                                WeightChangeMode weightChangeMode) {
        return stableAmountCalories * (weightChangeMode.getCoefficient() - 1);
    }

    /**
     * Определение режима набора\сброса веса
     * @param dataForCreatingGoal данные по созданию цели: скорость изменения веса,
     * текущий вес, целевой вес и другое
     * @return режим набора\сброса лишнего веса
     */
    private WeightChangeMode getWeightChangeMode(DataForCreatingGoal dataForCreatingGoal) {
        var weightChangeSpeed = dataForCreatingGoal.weightChangeSpeed();
        var signChangeWeight = Math.signum(dataForCreatingGoal.goalWeight() - dataForCreatingGoal.currentWeight());
        return switch ((int) Math.signum(signChangeWeight)) {
            case -1 -> getChangeModeWhenBurnCalories(weightChangeSpeed);
            case 1 -> getChangeModeWhenGainCalories(weightChangeSpeed);
            default -> WeightChangeMode.WEIGHT_SAVING;
        };
    }

    /**
     * Определение режима сброса веса
     * @param weightChangeSpeed как быстро хочется похудеть
     * @return режим сброса веса
     */
    private WeightChangeMode getChangeModeWhenBurnCalories(WeightChangeSpeed weightChangeSpeed) {
        return switch (weightChangeSpeed) {
            case WeightChangeSpeed.COMFORT_SPEED -> WeightChangeMode.COMFORT_WEIGHT_LOSS;
            case WeightChangeSpeed.HARD_SPEED -> WeightChangeMode.HARD_WEIGHT_LOSS;
        };
    }

    /**
     * Определение режима набора веса
     * @param weightChangeSpeed как быстро хочется поправится
     * @return режим набора веса
     */
    private WeightChangeMode getChangeModeWhenGainCalories(WeightChangeSpeed weightChangeSpeed) {
        return switch (weightChangeSpeed) {
            case WeightChangeSpeed.COMFORT_SPEED -> WeightChangeMode.COMFORT_WEIGHT_GAIN;
            case WeightChangeSpeed.HARD_SPEED -> WeightChangeMode.HARD_WEIGHT_GAIN;
        };
    }

}
