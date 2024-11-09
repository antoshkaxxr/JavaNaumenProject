package ru.antoshkaxxr.JavaNaumenProject.CriteriaApi;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;
import ru.antoshkaxxr.JavaNaumenProject.Entities.Customer;
import ru.antoshkaxxr.JavaNaumenProject.Entities.EatenProduct;
import ru.antoshkaxxr.JavaNaumenProject.Entities.FoodDiaryEntry;

/**
 * Реализация пользовательского репозитория для работы с записями дневника питания.
 * Этот класс использует Criteria API для выполнения запросов к базе данных
 * через объект {@link EntityManager}. Все методы взаимодействуют с сущностью
 * {@link FoodDiaryEntry} и используют различные критерии для фильтрации данных.
 */
@Repository
@RepositoryRestResource
public class FoodDiaryEntryRepositoryImpl implements FoodDiaryEntryRepository {
    private final EntityManager entityManager;

    /**
     * Конструктор для внедрения зависимости EntityManager.
     *
     * @param entityManager Менеджер сущностей для работы с базой данных
     */
    @Autowired
    public FoodDiaryEntryRepositoryImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<FoodDiaryEntry> findByCustomerId(Long customerId) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<FoodDiaryEntry> criteriaQuery = criteriaBuilder.createQuery(FoodDiaryEntry.class);

        Root<FoodDiaryEntry> entryRoot = criteriaQuery.from(FoodDiaryEntry.class);
        Join<FoodDiaryEntry, Customer> customer = entryRoot.join("customer", JoinType.INNER);
        Predicate namePredicate = criteriaBuilder.equal(customer.get("id"), customerId);

        criteriaQuery.select(entryRoot).where(namePredicate);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }

    @Override
    public List<FoodDiaryEntry> findByEatingDate(LocalDate eatingDate) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<FoodDiaryEntry> criteriaQuery = criteriaBuilder.createQuery(FoodDiaryEntry.class);

        Root<FoodDiaryEntry> entryRoot = criteriaQuery.from(FoodDiaryEntry.class);
        Join<FoodDiaryEntry, EatenProduct> eatenProduct = entryRoot.join("eatenProduct", JoinType.INNER);
        Predicate namePredicate = criteriaBuilder.equal(eatenProduct.get("eatingDate"), eatingDate);

        criteriaQuery.select(entryRoot).where(namePredicate);

        return entityManager.createQuery(criteriaQuery).getResultList();
    }
}
