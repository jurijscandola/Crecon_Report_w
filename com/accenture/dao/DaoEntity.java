package  dao;

import  model.Entity;

import java.util.List;

public interface DaoEntity extends DaoObjects<Entity>
{
    List<Entity> findAll();

    List<Entity> findById(final int p0);

    List<Entity> findByName(final String p0);

    boolean insert(final Entity p0);

    boolean update(final Entity p0);

    boolean delete(final int p0);
}
