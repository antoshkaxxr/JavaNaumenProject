package ru.antoshkaxxr.JavaNaumenProject.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.antoshkaxxr.JavaNaumenProject.Entities.Report;

/**
 * Репозиторий для работы с сущностью {@link Report}.
 * Позволяет выполнять основные операции CRUD (Создание, Чтение, Обновление, Удаление)
 * с объектами класса {@link Report}.
 */
@RepositoryRestResource(path = "reports")
public interface ReportRepository extends CrudRepository<Report, Long> {

}
