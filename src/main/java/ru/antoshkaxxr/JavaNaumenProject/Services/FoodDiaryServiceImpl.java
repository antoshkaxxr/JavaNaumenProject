package ru.antoshkaxxr.JavaNaumenProject.Services;

import org.springframework.stereotype.Service;
import ru.antoshkaxxr.JavaNaumenProject.Entities.FoodDiaryEntry;
import ru.antoshkaxxr.JavaNaumenProject.Repositories.FoodDiaryEntryRepository;

import java.util.List;

/**
 * Реализация сервиса для работы с приёмами пищи.
 * Обрабатывает операции, связанные с управлением данными пользователей,
 * включая удаление пользователей, их записей в журнале питания и оценок.
 */
@Service
public class FoodDiaryServiceImpl {

    private final FoodDiaryEntryRepository foodDiaryEntryRepository;

    public FoodDiaryServiceImpl(FoodDiaryEntryRepository foodDiaryEntryRepository) {
        this.foodDiaryEntryRepository = foodDiaryEntryRepository;
    }

    public List<FoodDiaryEntry> getFoodDiaryEntries(Long customerId) {
        return foodDiaryEntryRepository.findByCustomerId(customerId);
    }

    public void save(FoodDiaryEntry foodDiaryEntry) {
        foodDiaryEntryRepository.save(foodDiaryEntry);
    }
}
