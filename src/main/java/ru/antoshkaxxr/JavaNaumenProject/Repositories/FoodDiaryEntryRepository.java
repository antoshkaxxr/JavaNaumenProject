package ru.antoshkaxxr.JavaNaumenProject.Repositories;

import org.springframework.data.repository.CrudRepository;
import ru.antoshkaxxr.JavaNaumenProject.Entities.FoodDiaryEntry;

import java.util.List;

/**
 * Репозиторий для работы с сущностью {@link FoodDiaryEntry}.
 * Позволяет выполнять основные операции CRUD (Создание, Чтение, Обновление, Удаление)
 * с объектами класса {@link FoodDiaryEntry}.
 */
public interface FoodDiaryEntryRepository extends CrudRepository<FoodDiaryEntry, Long> {
    /**
     * Находит все записи в дневнике питания определенного пользователя
     * @param customerId id пользователя
     */
    List<FoodDiaryEntry> findByCustomerId(Long customerId);
}
