package ru.antoshkaxxr.JavaNaumenProject.Repositories;

import java.util.List;
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
}
