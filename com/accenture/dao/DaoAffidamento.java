package  dao;

import  model.Affidamento;

import java.util.List;

public interface DaoAffidamento extends DaoObjects<Affidamento>
{
    List<Affidamento> findAll();

    List<Affidamento> findById(final String p0);

    List<Affidamento> findByIdEntity(final int p0);

    List<Affidamento> findByCptyEntity(final String p0, final int p1);

    boolean insert(final Affidamento p0);

    boolean update(final Affidamento p0);

    boolean delete(final String p0);

    boolean delete(final String p0, final int p1);
}
