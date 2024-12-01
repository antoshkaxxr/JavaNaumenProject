package ru.antoshkaxxr.JavaNaumenProject.Services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import ru.antoshkaxxr.JavaNaumenProject.Entities.EatenProduct;
import ru.antoshkaxxr.JavaNaumenProject.Entities.FoodDiaryEntry;
import ru.antoshkaxxr.JavaNaumenProject.Models.EatenProductData;
import ru.antoshkaxxr.JavaNaumenProject.Repositories.EatenProductRepository;
import ru.antoshkaxxr.JavaNaumenProject.Repositories.FoodDiaryEntryRepository;


/**
 * Реализация сервиса для работы с приёмами пищи.
 * Обрабатывает операции, связанные с управлением данными пользователей,
 * включая удаление пользователей, их записей в журнале питания и оценок.
 */
@Service
public class FoodDiaryServiceImpl {

    private final FoodDiaryEntryRepository foodDiaryEntryRepository;
    private final EatenProductRepository eatenProductRepository;
    private final ProductServiceImpl productServiceImpl;
    private final CustomerServiceImpl customerServiceImpl;

    /**
     * Конструктор для инициализации сервиса.
     *
     * @param foodDiaryEntryRepository Репозиторий для работы с записями в дневнике питания.
     * @param eatenProductRepository Репозиторий для работы со съеденными продуктами
     * @param productServiceImpl Сервис для работы с продуктами
     * @param customerServiceImpl Сервис для работы с пользователем
     */
    @Autowired
    public FoodDiaryServiceImpl(FoodDiaryEntryRepository foodDiaryEntryRepository,
                                EatenProductRepository eatenProductRepository,
                                ProductServiceImpl productServiceImpl,
                                CustomerServiceImpl customerServiceImpl) {
        this.foodDiaryEntryRepository = foodDiaryEntryRepository;
        this.eatenProductRepository = eatenProductRepository;
        this.productServiceImpl = productServiceImpl;
        this.customerServiceImpl = customerServiceImpl;
    }

    /**
     * Метод, возвращающий все приёмы пищи пользователя по его id
     *
     * @param customerId id пользователя
     * @return приёмы пищи пользователя по его id
     */
    public List<FoodDiaryEntry> getFoodDiaryEntries(Long customerId) {
        return foodDiaryEntryRepository.findByCustomerId(customerId);
    }

    /**
     * Метод, создающий в базе данных объект приёма пищи на основе данных об употреблённом
     * продукте, введённых пользователем
     *
     * @param eatenProductData id пользователя
     */
    public void save(EatenProductData eatenProductData) {
        var foodDiary = new FoodDiaryEntry();
        var customer = customerServiceImpl.getCurentLoginedCustomer();

        var product = productServiceImpl.getProducts(eatenProductData.id());
        var eatenProduct = new EatenProduct(product, eatenProductData.date(), eatenProductData.amount());
        eatenProductRepository.save(eatenProduct);

        foodDiary.setCustomer(customer);
        foodDiary.setEatenProduct(eatenProduct);
        foodDiaryEntryRepository.save(foodDiary);
    }

    /**
     * Метод, удаляющий из базы данных приём пищи
     *
     * @param foodDiaryId id приёма пищи
     */
    public void delete(Long foodDiaryId) {
        var resultFounding = foodDiaryEntryRepository.findById(foodDiaryId);
        if (resultFounding.isEmpty()) {
            throw new ResourceNotFoundException("Нет такого приёма пищи");
        }
        var currFoodDiary = resultFounding.get();
        foodDiaryEntryRepository.delete(currFoodDiary);
        eatenProductRepository.delete(currFoodDiary.getEatenProduct());
    }
}
