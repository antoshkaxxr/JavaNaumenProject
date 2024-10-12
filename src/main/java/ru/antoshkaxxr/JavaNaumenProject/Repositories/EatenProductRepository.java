package ru.antoshkaxxr.JavaNaumenProject.Repositories;

import org.springframework.data.repository.CrudRepository;
import ru.antoshkaxxr.JavaNaumenProject.Entities.EatenProduct;

import java.time.LocalDate;
import java.util.List;

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
