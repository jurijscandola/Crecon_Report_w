package  dao;

import  utils.PropertiesManager;
import  model.Entity;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.Connection;

public class DaoEntityImpl implements DaoEntity
{
    private Connection conn;
    private PreparedStatement prpstmt;
    private ResultSet rs;
    private String query;
    private List<Entity> list;
    private Entity ey;
    private String id_tbl;
    private String name_tbl;

    public DaoEntityImpl() {
        this.conn = null;
        this.prpstmt = null;
        this.rs = null;
        this.list = null;
        this.ey = null;
        this.list = new ArrayList<Entity>();
        this.id_tbl = PropertiesManager.getInstanceOfPropertiesManager().getProperty("T_ENTITY_ID");
        this.name_tbl = PropertiesManager.getInstanceOfPropertiesManager().getProperty("T_ENTITY_NAME");
    }

    @Override
    public List<Entity> findAll() {
        ConnManager.getInstance();
        this.conn = ConnManager.getConnection();
        this.list.clear();
        this.query = PropertiesManager.getInstanceOfPropertiesManager().getProperty("FINDALL_ENTITY");
        try {
            this.prpstmt = this.conn.prepareStatement(this.query);
            this.rs = this.prpstmt.executeQuery();
            while (this.rs.next()) {
                this.ey = new Entity(this.rs.getInt(this.id_tbl), this.rs.getString(this.name_tbl));
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
    public List<Entity> findById(final int id) {
        ConnManager.getInstance();
        this.conn = ConnManager.getConnection();
        this.list.clear();
        this.query = PropertiesManager.getInstanceOfPropertiesManager().getProperty("FINDBYID_ENTITY");
        try {
            (this.prpstmt = this.conn.prepareStatement(this.query)).setInt(1, id);
            this.rs = this.prpstmt.executeQuery();
            while (this.rs.next()) {
                this.ey = new Entity(this.rs.getInt(this.id_tbl), this.rs.getString(this.name_tbl));
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
    public List<Entity> findByName(final String name) {
        ConnManager.getInstance();
        this.conn = ConnManager.getConnection();
        this.list.clear();
        this.query = PropertiesManager.getInstanceOfPropertiesManager().getProperty("FINDBYNAME_ENTITY");
        try {
            (this.prpstmt = this.conn.prepareStatement(this.query)).setString(1, name);
            this.rs = this.prpstmt.executeQuery();
            while (this.rs.next()) {
                this.ey = new Entity(this.rs.getInt(this.id_tbl), this.rs.getString(this.name_tbl));
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
    public boolean insert(final Entity object) {
        ConnManager.getInstance();
        this.conn = ConnManager.getConnection();
        this.query = PropertiesManager.getInstanceOfPropertiesManager().getProperty("INSERT_ENTITY");
        try {
            (this.prpstmt = this.conn.prepareStatement(this.query)).setString(1, object.getEntity_name());
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
    public boolean update(final Entity object) {
        ConnManager.getInstance();
        this.conn = ConnManager.getConnection();
        this.query = PropertiesManager.getInstanceOfPropertiesManager().getProperty("UPDATE_ENTITY");
        try {
            (this.prpstmt = this.conn.prepareStatement(this.query)).setString(1, object.getEntity_name());
            this.prpstmt.setInt(2, object.getEntity_id());
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
    public boolean delete(final int id) {
        ConnManager.getInstance();
        this.conn = ConnManager.getConnection();
        this.query = PropertiesManager.getInstanceOfPropertiesManager().getProperty("DELETE_ENTITY");
        try {
            (this.prpstmt = this.conn.prepareStatement(this.query)).setInt(1, id);
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
    public List<Entity> findById(final String id) {
        return null;
    }

    @Override
    public boolean delete(final String id) {
        return false;
    }
}

