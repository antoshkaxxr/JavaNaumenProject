package ru.antoshkaxxr.JavaNaumenProject.Repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.antoshkaxxr.JavaNaumenProject.Entities.Goal;

/**
 * Репозиторий для работы с сущностью {@link Goal}.
 * Позволяет выполнять основные операции CRUD (Создание, Чтение, Обновление, Удаление)
 * с объектами класса {@link Goal}.
 */
@RepositoryRestResource(path = "customers")
public interface GoalRepository extends CrudRepository<Goal, Long> {
    /**
     * Находит цели пользователя по его id
     *
     * @param idCustomer Айди пользователя.
     * @return Объект {@link Goal}, соответствующий целям пользователя с id = {@param idCustomer}
     */
    List<Goal> findByCustomerId(Long idCustomer);

}
