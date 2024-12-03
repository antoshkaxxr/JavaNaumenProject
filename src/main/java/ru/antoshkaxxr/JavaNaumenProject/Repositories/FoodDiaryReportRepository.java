package ru.antoshkaxxr.JavaNaumenProject.Repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.antoshkaxxr.JavaNaumenProject.Entities.FoodDiaryReport;

@RepositoryRestResource(path = "foodDiaryReport")
public interface FoodDiaryReportRepository  extends CrudRepository<FoodDiaryReport, Long> {
}
