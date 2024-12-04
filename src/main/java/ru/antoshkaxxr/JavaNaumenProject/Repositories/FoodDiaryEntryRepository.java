package ru.antoshkaxxr.JavaNaumenProject.Repositories;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.antoshkaxxr.JavaNaumenProject.Entities.FoodDiaryEntry;

/**
 * Репозиторий для работы с сущностью {@link FoodDiaryEntry}.
 * Позволяет выполнять основные операции CRUD (Создание, Чтение, Обновление, Удаление)
 * с объектами класса {@link FoodDiaryEntry}.
 */
@RepositoryRestResource(path = "food_diary_entries")
public interface FoodDiaryEntryRepository extends CrudRepository<FoodDiaryEntry, Long> {
    /**
     * Находит все записи в дневнике питания определенного пользователя.
     *
     * @param customerId Идентификатор пользователя.
     * @return Список объектов {@link FoodDiaryEntry}, соответствующих указанному идентификатору пользователя.
     */
    List<FoodDiaryEntry> findByCustomerId(Long customerId);

    /**
     * Находит все записи в дневнике питания начиная с какого-то момента
     *
     * @param customerId Идентификатор пользователя.
     * @param date Дата с которой идёт поиск приёмов пищи
     * @return Список объектов {@link FoodDiaryEntry}, соответствующих указанному идентификатору пользователя.
     */
    @Query("SELECT r FROM FoodDiaryEntry r WHERE r.customer.id = :customerId AND r.eatenProduct.eatingDate >= :date")
    List<FoodDiaryEntry> findByCustomerIdAndDateFrom(Long customerId, LocalDate date);

    List<FoodDiaryEntry> findByCustomerIdAndEatenProductEatingDateBetween(Long customerId,
                                                                          LocalDate startDate,
                                                                          LocalDate endDate);
}
