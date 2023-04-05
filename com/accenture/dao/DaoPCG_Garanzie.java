package  dao;

import  model.PCG_Garanzie;

import java.util.List;

public interface DaoPCG_Garanzie extends DaoObjects<PCG_Garanzie>
{
    List<PCG_Garanzie> findAll();

    List<PCG_Garanzie> findById(final String p0);

    List<PCG_Garanzie> findByName(final String p0);

    boolean insert(final PCG_Garanzie p0);

    boolean update(final PCG_Garanzie p0);

    boolean delete(final String p0);
}
