package  dao;

import  model.PCG_Garanzie;
import  dao.ConnManager;
import  utils.PropertiesManager;
import  dao.DaoPCG_Garanzie;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;

public class DaoPCG_GaranzieImpl implements DaoPCG_Garanzie
{
    private Connection conn;
    private PreparedStatement prpstmt;
    private ResultSet rs;
    private String query;
    private List<PCG_Garanzie> list;
    private PCG_Garanzie ey;
    private String id_tbl;
    private String pcg_garanzie;

    public DaoPCG_GaranzieImpl() {
        this.conn = null;
        this.prpstmt = null;
        this.rs = null;
        this.list = null;
        this.ey = null;
        this.list = new ArrayList<PCG_Garanzie>();
        this.id_tbl = PropertiesManager.getInstanceOfPropertiesManager().getProperty("T_PCG_GARANZIE_ID");
        this.pcg_garanzie = PropertiesManager.getInstanceOfPropertiesManager().getProperty("T_PCG_GARANZIE_NAME");
    }

    @Override
    public List<PCG_Garanzie> findAll() {
        ConnManager.getInstance();
        this.conn = ConnManager.getConnection();
        this.list.clear();
        this.query = PropertiesManager.getInstanceOfPropertiesManager().getProperty("FINDALL_PCG_GARANZIE");
        try {
            this.prpstmt = this.conn.prepareStatement(this.query);
            this.rs = this.prpstmt.executeQuery();
            while (this.rs.next()) {
                this.ey = new PCG_Garanzie(this.rs.getString(this.id_tbl), this.rs.getString(this.pcg_garanzie));
                this.list.add(this.ey);
            }
            this.rs.close();
            this.prpstmt.close();
            ConnManager.getInstance();
            ConnManager.closeConnection();
        }
        catch (SQLException var2) {
            var2.printStackTrace();
        }
        return this.list;
    }

    @Override
    public List<PCG_Garanzie> findById(final String id) {
        ConnManager.getInstance();
        this.conn = ConnManager.getConnection();
        this.list.clear();
        this.query = PropertiesManager.getInstanceOfPropertiesManager().getProperty("FINDBYID_PCG_GARANZIE");
        try {
            (this.prpstmt = this.conn.prepareStatement(this.query)).setString(1, id);
            this.rs = this.prpstmt.executeQuery();
            while (this.rs.next()) {
                this.ey = new PCG_Garanzie(this.rs.getString(this.id_tbl), this.rs.getString(this.pcg_garanzie));
                this.list.add(this.ey);
            }
            this.rs.close();
            this.prpstmt.close();
            ConnManager.getInstance();
            ConnManager.closeConnection();
        }
        catch (SQLException var3) {
            var3.printStackTrace();
        }
        return this.list;
    }

    @Override
    public List<PCG_Garanzie> findByName(final String name) {
        return null;
    }

    @Override
    public boolean insert(final PCG_Garanzie object) {
        ConnManager.getInstance();
        this.conn = ConnManager.getConnection();
        this.query = PropertiesManager.getInstanceOfPropertiesManager().getProperty("INSERT_PCG_GARANZIE");
        try {
            (this.prpstmt = this.conn.prepareStatement(this.query)).setString(1, object.getId());
            this.prpstmt.setString(2, object.getPcg_garanzie());
            this.prpstmt.executeUpdate();
            this.prpstmt.close();
            ConnManager.getInstance();
            ConnManager.closeConnection();
            return true;
        }
        catch (SQLException var3) {
            var3.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean update(final PCG_Garanzie object) {
        ConnManager.getInstance();
        this.conn = ConnManager.getConnection();
        this.query = PropertiesManager.getInstanceOfPropertiesManager().getProperty("UPDATE_PCG_GARANZIE");
        try {
            (this.prpstmt = this.conn.prepareStatement(this.query)).setString(1, object.getPcg_garanzie());
            this.prpstmt.setString(2, object.getId());
            this.prpstmt.executeUpdate();
            this.prpstmt.close();
            ConnManager.getInstance();
            ConnManager.closeConnection();
            return true;
        }
        catch (SQLException var3) {
            var3.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(final String id) {
        ConnManager.getInstance();
        this.conn = ConnManager.getConnection();
        this.query = PropertiesManager.getInstanceOfPropertiesManager().getProperty("DELETE_PCG_GARANZIE");
        try {
            (this.prpstmt = this.conn.prepareStatement(this.query)).setString(1, id);
            this.prpstmt.executeUpdate();
            this.prpstmt.close();
            ConnManager.getInstance();
            ConnManager.closeConnection();
            return true;
        }
        catch (SQLException var3) {
            var3.printStackTrace();
            return false;
        }
    }
}

