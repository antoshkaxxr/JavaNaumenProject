package ru.antoshkaxxr.JavaNaumenProject.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.antoshkaxxr.JavaNaumenProject.Entities.EatenProduct;

import java.time.LocalDate;
import java.util.List;

/**
 * Репозиторий для работы с сущностью {@link EatenProduct}.
 * Позволяет выполнять основные операции CRUD (Создание, Чтение, Обновление, Удаление)
 * с объектами класса {@link EatenProduct}.
 */
@RepositoryRestResource(path = "eaten_products")
public interface EatenProductRepository extends CrudRepository<EatenProduct, Long> {
    /**
     * Находит все продукты, съеденные в определенный день.
     * @param date требуемая дата
     */
    List<EatenProduct> findByEatingDate(LocalDate date);

    /**
     * Находит все продукты, съеденные в определенный интервал дат.
     * @param start начало интервала
     * @param end конец интервала
     */
    List<EatenProduct> findByEatingDateBetween(LocalDate start, LocalDate end);
}
