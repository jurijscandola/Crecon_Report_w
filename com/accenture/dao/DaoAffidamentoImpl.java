package  dao;

import  dao.DaoAffidamento;
import  model.Affidamento;
import  dao.ConnManager;
import  model.Affidamento;
import  utils.PropertiesManager;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;

public class DaoAffidamentoImpl implements DaoAffidamento
{
    private Connection conn;
    private PreparedStatement prpstmt;
    private ResultSet rs;
    private String query;
    private List<Affidamento> list;
    private Affidamento ey;
    private String id_tbl;
    private String entity;
    private String affidamento;

    public DaoAffidamentoImpl() {
        this.conn = null;
        this.prpstmt = null;
        this.rs = null;
        this.list = null;
        this.ey = null;
        this.list = new ArrayList<Affidamento>();
        this.id_tbl = PropertiesManager.getInstanceOfPropertiesManager().getProperty("T_AFFIDAMENTO_ID");
        this.entity = PropertiesManager.getInstanceOfPropertiesManager().getProperty("T_AFFIDAMENTO_ENTITY_ID");
        this.affidamento = PropertiesManager.getInstanceOfPropertiesManager().getProperty("T_AFFIDAMENTO_AFFIDAMENTO");
    }

    @Override
    public List<Affidamento> findAll() {
        ConnManager.getInstance();
        this.conn = ConnManager.getConnection();
        this.list.clear();
        this.query = PropertiesManager.getInstanceOfPropertiesManager().getProperty("FINDALL_AFFIDAMENTO");
        try {
            this.prpstmt = this.conn.prepareStatement(this.query);
            this.rs = this.prpstmt.executeQuery();
            while (this.rs.next()) {
                this.ey = new Affidamento(this.rs.getString(this.id_tbl), this.rs.getInt(this.entity), this.rs.getDouble(this.affidamento));
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
    public List<Affidamento> findById(final String id) {
        ConnManager.getInstance();
        this.conn = ConnManager.getConnection();
        this.list.clear();
        this.query = PropertiesManager.getInstanceOfPropertiesManager().getProperty("FINDBYID_CPTY_AFFIDAMENTO");
        try {
            (this.prpstmt = this.conn.prepareStatement(this.query)).setString(1, id);
            this.rs = this.prpstmt.executeQuery();
            while (this.rs.next()) {
                this.ey = new Affidamento(this.rs.getString(this.id_tbl), this.rs.getInt(this.entity), this.rs.getDouble(this.affidamento));
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
    public List<Affidamento> findByIdEntity(final int id) {
        ConnManager.getInstance();
        this.conn = ConnManager.getConnection();
        this.list.clear();
        this.query = PropertiesManager.getInstanceOfPropertiesManager().getProperty("FINDBYID_ENTITY_AFFIDAMENTO");
        try {
            (this.prpstmt = this.conn.prepareStatement(this.query)).setInt(1, id);
            this.rs = this.prpstmt.executeQuery();
            while (this.rs.next()) {
                this.ey = new Affidamento(this.rs.getString(this.id_tbl), this.rs.getInt(this.entity), this.rs.getDouble(this.affidamento));
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
    public List<Affidamento> findByCptyEntity(final String id, final int id2) {
        ConnManager.getInstance();
        this.conn = ConnManager.getConnection();
        this.list.clear();
        this.query = PropertiesManager.getInstanceOfPropertiesManager().getProperty("FINDBYID_CPTY_ENTITY_AFFIDAMENTO");
        try {
            (this.prpstmt = this.conn.prepareStatement(this.query)).setString(1, id);
            this.prpstmt.setInt(2, id2);
            this.rs = this.prpstmt.executeQuery();
            while (this.rs.next()) {
                this.ey = new Affidamento(this.rs.getString(this.id_tbl), this.rs.getInt(this.entity), this.rs.getDouble(this.affidamento));
                this.list.add(this.ey);
            }
            this.rs.close();
            this.prpstmt.close();
            ConnManager.getInstance();
            ConnManager.closeConnection();
        }
        catch (SQLException var4) {
            var4.printStackTrace();
        }
        return this.list;
    }



    @Override
    public List<Affidamento> findByName(final String name) {
        return null;
    }

    @Override
    public boolean insert(final Affidamento object) {
        ConnManager.getInstance();
        this.conn = ConnManager.getConnection();
        this.query = PropertiesManager.getInstanceOfPropertiesManager().getProperty("INSERT_AFFIDAMENTO");
        try {
            (this.prpstmt = this.conn.prepareStatement(this.query)).setString(1, object.getId());
            this.prpstmt.setInt(2, object.getEntity_Id());
            this.prpstmt.setDouble(3, object.getAffidamento());
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
    public boolean update(final Affidamento object) {
        ConnManager.getInstance();
        this.conn = ConnManager.getConnection();
        this.query = PropertiesManager.getInstanceOfPropertiesManager().getProperty("UPDATE_AFFIDAMENTO");
        try {
            (this.prpstmt = this.conn.prepareStatement(this.query)).setDouble(1, object.getAffidamento());
            this.prpstmt.setString(2, object.getId());
            this.prpstmt.setInt(3, object.getEntity_Id());
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
    public boolean delete(final String cpty_id, final int entity_id) {
        ConnManager.getInstance();
        this.conn = ConnManager.getConnection();
        this.query = PropertiesManager.getInstanceOfPropertiesManager().getProperty("DELETE_AFFIDAMENTO_CPTY_ENTITY");
        try {
            (this.prpstmt = this.conn.prepareStatement(this.query)).setString(1, cpty_id);
            this.prpstmt.setInt(2, entity_id);
            this.prpstmt.executeUpdate();
            this.prpstmt.close();
            ConnManager.getInstance();
            ConnManager.closeConnection();
            return true;
        }
        catch (SQLException var4) {
            var4.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean delete(final String cpty_id) {
        ConnManager.getInstance();
        this.conn = ConnManager.getConnection();
        this.query = PropertiesManager.getInstanceOfPropertiesManager().getProperty("DELETE_AFFIDAMENTO");
        try {
            (this.prpstmt = this.conn.prepareStatement(this.query)).setString(1, cpty_id);
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

