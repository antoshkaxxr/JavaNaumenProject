package ru.antoshkaxxr.JavaNaumenProject.CriteriaApi;

import java.time.LocalDate;
import java.util.List;
import ru.antoshkaxxr.JavaNaumenProject.Entities.FoodDiaryEntry;

/**
 * Репозиторий для работы с записями дневника питания {@link FoodDiaryEntry}.
 * Предоставляет методы для извлечения данных о записях дневника питания
 * для определенного пользователя и по конкретной дате.
 */
public interface FoodDiaryEntryRepository {
    /**
     * Находит все записи из дневника питания для определенного пользователя.
     *
     * @param customerId Идентификатор пользователя
     * @return Список записей дневника питания для указанного пользователя
     */
    List<FoodDiaryEntry> findByCustomerId(Long customerId);

    /**
     * Находит все записи из дневника питания за определенную дату.
     *
     * @param eatingDate Дата, за которую нужно получить записи
     * @return Список записей дневника питания за указанную дату
     */
    List<FoodDiaryEntry> findByEatingDate(LocalDate eatingDate);
}
