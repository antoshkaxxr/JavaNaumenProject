package ru.antoshkaxxr.task3.DataAccessLayer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.antoshkaxxr.task3.Entity.Product;

import java.util.Map;

@Component
public class ProductRepository implements CrudRepository<Product, Long> {
    private final Map<Long, Product> productCatalog;

    @Autowired
    public ProductRepository(Map<Long, Product> productCatalog) {
        this.productCatalog = productCatalog;
    }

    @Override
    public boolean create(Product entity) {
        if (entity != null && entity.getId() != null) {
            if (!productCatalog.containsKey(entity.getId())) {
                productCatalog.put(entity.getId(), entity);
                return true;
            }
        }

        return false;
    }

    @Override
    public Product read(Long id) {
        if (!productCatalog.containsKey(id)) {
            return null;
        }

        return productCatalog.get(id);
    }

    @Override
    public boolean update(Product entity) {
        if (entity != null && entity.getId() != null) {
            productCatalog.put(entity.getId(), entity);
            return true;
        }

        return false;
    }

    @Override
    public boolean delete(Long id) {
        if (productCatalog.containsKey(id)) {
            productCatalog.remove(id);
            return true;
        }

        return false;
    }
}
