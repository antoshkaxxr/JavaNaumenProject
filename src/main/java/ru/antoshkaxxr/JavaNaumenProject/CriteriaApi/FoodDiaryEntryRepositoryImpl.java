package ru.antoshkaxxr.JavaNaumenProject.CriteriaApi;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.antoshkaxxr.JavaNaumenProject.Entities.Customer;
import ru.antoshkaxxr.JavaNaumenProject.Entities.EatenProduct;
import ru.antoshkaxxr.JavaNaumenProject.Entities.FoodDiaryEntry;

import java.time.LocalDate;
import java.util.List;

@Repository
public class FoodDiaryEntryRepositoryImpl implements FoodDiaryEntryRepositoryCustom {
    private final EntityManager entityManager;

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
