package ru.antoshkaxxr.JavaNaumenProject.Controllers;

import java.time.LocalDate;
import java.util.Comparator;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.antoshkaxxr.JavaNaumenProject.Entities.Goal;
import ru.antoshkaxxr.JavaNaumenProject.Models.DataForCreatingGoal;
import ru.antoshkaxxr.JavaNaumenProject.Repositories.GoalRepository;
import ru.antoshkaxxr.JavaNaumenProject.Services.CustomerServiceImpl;
import ru.antoshkaxxr.JavaNaumenProject.Services.GoalServiceImpl;

/**
 * Контроллер для составления целей по похудению / набору веса и просмотра по ним статистики.
 */
@Controller
@RequestMapping("/goal")
public class GoalController {

    private static final String GOAL_VIEW_REDIRECT = "redirect:/goal";

    private final GoalServiceImpl goalServiceImpl;
    private final CustomerServiceImpl customerServiceImpl;
    private final GoalRepository goalRepository;

    /**
     * Конструктор для инициализации контроллера.
     *
     * @param goalServiceImpl Сервис для работы с целями
     * @param customerServiceImpl Сервис для работы с пользователями
     * @param goalRepository Репозиторий для работы с целями
     */
    public GoalController(GoalServiceImpl goalServiceImpl, CustomerServiceImpl customerServiceImpl,
                          GoalRepository goalRepository) {
        this.goalServiceImpl = goalServiceImpl;
        this.customerServiceImpl = customerServiceImpl;
        this.goalRepository = goalRepository;
    }

    /**
     * Обрабатывает GET-запрос для отправки формы текущего пользователя с его целями
     *
     * @param model Объект Model, используемый для передачи данных на представление.
     * @return Форма текущего пользователя с его целями
     */
    @GetMapping
    public String returnMyGoalsForm(Model model) {
        var customer = customerServiceImpl.getCurrentLoggedInCustomer();
        var goalsCurrCustomer = goalRepository.findByCustomerId(customer.getId());
        goalsCurrCustomer.sort(Comparator.comparing(Goal::getStartDate, Comparator.reverseOrder()));
        model.addAttribute("goals", goalsCurrCustomer);
        return "myGoalsForm";
    }

    /**
     * Обрабатывает GET-запрос для отправки формы, в которой пользователь введёт
     * данные для составления новой цели
     *
     * @return Форма ввода данных для новой цели по похудению / набору веса
     */
    @GetMapping("/form")
    public String returnGoalForm() {
        return "newGoalForm";
    }

    /**
     * Обрабатывает POST-запрос отправки данных со страницы составления новой цели
     * В базе данных создаётся объект цели на основе введённых пользователем параметров
     * @param data Объект данных, введённых пользователем
     * @return Имя представления для перенаправления
     */
    @PostMapping("/evaluate")
    public String redirectOnStatistic(DataForCreatingGoal data) {
        var customer = customerServiceImpl.getCurrentLoggedInCustomer();
        var newGoal = goalServiceImpl.createGoal(data, customer);
        goalRepository.save(newGoal);
        return GOAL_VIEW_REDIRECT;
    }

    /**
     * Обрабатывает POST-запрос на удаление цели по её id
     * @param goalId id цели, которую удаляют
     * @return Имя представления для перенаправления
     */
    @PostMapping("/delete/{goalId}")
    public String redirectOnStatistic(@PathVariable("goalId") Long goalId) {
        goalServiceImpl.deleteGoal(goalId);
        return GOAL_VIEW_REDIRECT;
    }

    /**
     * Обрабатывает GET-запрос для отображения текущей цели
     * Этот метод на основе приёмов пищи пользователем собирает статистику того,
     * сколько калорий было употреблено за каждый день с момента постановки цели
     * и отправляет страницу со статистикой
     *
     * @param model Объект Model, используемый для передачи данных на представление.
     * @param goalId Id цели, по которой будет отображена статистика
     * @param startDateForViewStatistic дата, с которой показывается статистика
     * @param endDateForViewStatistic дата, по которую показывается статистика
     * @return Имя представления для отображения результатов калькулятора или перенаправление на форму калькулятора,
     *         если входные данные отсутствуют.
     */
    @GetMapping("/statistic/{goalId}")
    public String returnStatisticOfGoal(@PathVariable("goalId") Long goalId,
                                @RequestParam(value = "startDateForViewStatistic", required = false)
                                LocalDate startDateForViewStatistic,
                                @RequestParam(value = "startDateForViewStatistic", required = false)
                                LocalDate endDateForViewStatistic,
                                Model model) {
        var statisticGoal = goalServiceImpl.getStatisticGoal(
                goalId,
                startDateForViewStatistic,
                endDateForViewStatistic
        );
        model.addAttribute("len", statisticGoal.labelsDate().length);
        model.addAttribute("labelsDate", statisticGoal.labelsDate());
        model.addAttribute("valuesConsumptionReal", statisticGoal.valuesRealConsumption());
        model.addAttribute("valuesConsumptionPlan", statisticGoal.valuesPlanConsumption());
        return "goalStatistic";
    }
}
