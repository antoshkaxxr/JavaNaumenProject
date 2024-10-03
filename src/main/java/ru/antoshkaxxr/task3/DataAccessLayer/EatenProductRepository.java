package ru.antoshkaxxr.task3.DataAccessLayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.antoshkaxxr.task3.Entity.EatenProduct;
import ru.antoshkaxxr.task3.IdGenerator;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Component
public class EatenProductRepository implements BaseRepository<EatenProduct, Date> {
    private final Map<Long, EatenProduct> eatenProductStorage;
    private final IdGenerator idGenerator;

    @Autowired
    public EatenProductRepository(Map<Long, EatenProduct> eatenProductStorage, IdGenerator idGenerator) {
        this.eatenProductStorage = eatenProductStorage;
        this.idGenerator = idGenerator;
    }

    @Override
    public void add(EatenProduct entity) {
        long id = idGenerator.generateId();
        eatenProductStorage.put(id, entity);
    }

    @Override
    public List<EatenProduct> getInDate(Date date) {
        List<EatenProduct> products = new ArrayList<>();

        for (EatenProduct eatenProduct : eatenProductStorage.values()) {
            Date eatenDate = eatenProduct.getEatingDate();
            if (eatenDate != null && eatenDate.equals(date)) {
                products.add(eatenProduct);
            }
        }

        return products;
    }

    @Override
    public List<EatenProduct> getInInterval(Date from, Date to) {
        List<EatenProduct> products = new ArrayList<>();

        for (EatenProduct eatenProduct : eatenProductStorage.values()) {
            Date eatenDate = eatenProduct.getEatingDate();
            if (eatenDate != null && (eatenDate.after(from) || eatenDate.equals(from)) && (eatenDate.before(to) || eatenDate.equals(to))) {
                products.add(eatenProduct);
            }
        }

        return products;
    }
}
