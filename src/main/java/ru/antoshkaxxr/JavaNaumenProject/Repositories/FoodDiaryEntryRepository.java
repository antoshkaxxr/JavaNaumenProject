package ru.antoshkaxxr.JavaNaumenProject.Repositories;

import org.springframework.data.repository.CrudRepository;
import ru.antoshkaxxr.JavaNaumenProject.Entities.FoodDiaryEntry;

import java.util.List;

public interface FoodDiaryEntryRepository extends CrudRepository<FoodDiaryEntry, Long> {
    /**
     * Находит все записи в дневнике питания определенного пользователя
     * @param customerId id пользователя
     */
    List<FoodDiaryEntry> findByCustomerId(Long customerId);
}
