package ru.antoshkaxxr.JavaNaumenProject.Repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.antoshkaxxr.JavaNaumenProject.Entities.FoodDiaryReport;
/**
 * Репозиторий для работы с сущностью {@link FoodDiaryReport}.
 * Позволяет выполнять основные операции CRUD (Создание, Чтение, Обновление, Удаление)
 * с объектами класса {@link FoodDiaryReport}.
 */
@RepositoryRestResource(path = "foodDiaryReport")
public interface FoodDiaryReportRepository extends CrudRepository<FoodDiaryReport, Long> {

    /**
     * Находит все отчеты пользователя о приёмах пищи по его имени.
     *
     * @param customerName Имя пользователя.
     * @return Список объектов {@link FoodDiaryReport} данного пользователя.
     */
    List<FoodDiaryReport> getAllByCustomerName(String customerName);
}
