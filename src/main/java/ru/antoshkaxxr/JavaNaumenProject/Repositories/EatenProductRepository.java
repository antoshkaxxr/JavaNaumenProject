package ru.antoshkaxxr.JavaNaumenProject.Repositories;

import java.time.LocalDate;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.antoshkaxxr.JavaNaumenProject.Entities.EatenProduct;

/**
 * Репозиторий для работы с сущностью {@link EatenProduct}.
 * Позволяет выполнять основные операции CRUD (Создание, Чтение, Обновление, Удаление)
 * с объектами класса {@link EatenProduct}.
 */
@RepositoryRestResource(path = "eaten_products")
public interface EatenProductRepository extends CrudRepository<EatenProduct, Long> {
    /**
     * Находит все продукты, съеденные в определенный день.
     *
     * @param date Требуемая дата.
     * @return Список объектов {@link EatenProduct}, соответствующих указанной дате.
     */
    List<EatenProduct> findByEatingDate(LocalDate date);

    /**
     * Находит все продукты, съеденные в определенный интервал дат.
     *
     * @param start Начало интервала.
     * @param end Конец интервала.
     * @return Список объектов {@link EatenProduct}, соответствующих указанному интервалу дат.
     */
    List<EatenProduct> findByEatingDateBetween(LocalDate start, LocalDate end);
}
