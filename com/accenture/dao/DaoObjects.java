package  dao;

import java.util.List;

public interface DaoObjects<T>
{
    List<T> findAll();

    List<T> findById(final String p0);

    List<T> findByName(final String p0);

    boolean insert(final T p0);

    boolean update(final T p0);

    boolean delete(final String p0);
}