package ru.antoshkaxxr.JavaNaumenProject.CriteriaApi;

import ru.antoshkaxxr.JavaNaumenProject.Entities.FoodDiaryEntry;

import java.time.LocalDate;
import java.util.List;

/**
 * Репозиторий для работы с записями дневника питания {@link FoodDiaryEntry}.
 * Предоставляет методы для извлечения данных о записях дневника питания
 * для определенного пользователя и по конкретной дате.
 */
public interface FoodDiaryEntryRepository {
    /**
     * Находит все записи из дневника питания определенного пользователя
     * @param customerId id пользователя
     */
    List<FoodDiaryEntry> findByCustomerId(Long customerId);

    /**
     * Находит все записи из дневника питания за определенную дату
     * @param eatingDate требуемая дата
     */
    List<FoodDiaryEntry> findByEatingDate(LocalDate eatingDate);
}
