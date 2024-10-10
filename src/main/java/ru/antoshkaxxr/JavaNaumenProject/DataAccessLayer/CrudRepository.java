package ru.antoshkaxxr.JavaNaumenProject.DataAccessLayer;

public interface CrudRepository<T, ID> {
    boolean create(T entity);

    T read(ID id);

    boolean update(T entity);

    boolean delete(ID id);
}
