package  controller;

import  model.Entity;
import  dao.*;
import  model.Affidamento;
import  model.PCG_Garanzie;
import  report.CreateModelTable;
import  report.ReportManager;
import  utils.PropertiesManager;

import net.sf.jasperreports.engine.JRException;
import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.VelocityEngine;
import org.apache.velocity.context.Context;

import javax.swing.table.DefaultTableModel;
import java.io.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

public class Controller
{
    private static List<String> editableFieldList;
    private static List<HashMap<String, String>> pcgList;
    private static List<HashMap<String, List<String>>> entityList;
    private static CreateModelTable cmt;
    private List<LinkedHashMap<String, Object>> list;
    private ArrayList<HashMap<String, String>> list3;
    private String pathOutFinalFileJrxml;
    private String codifica;
    private String reportPath;
    private int positionX;
    private int size;

    public Controller() {
        this.list = null;
        this.list3 = new ArrayList<HashMap<String, String>>();
        this.codifica = PropertiesManager.getInstanceOfPropertiesManager().getProperty("CODIFICA_EXPORT");
        this.positionX = 0;
        this.size = 0;
    }

    public DefaultTableModel getModel() throws SQLException {
        DefaultTableModel dtm = null;
        final DaoEntity daoentity = new DaoEntityImpl();
        new ArrayList();
        final List<Entity> entList = daoentity.findAll();
        for (int i = 0; i < entList.size(); ++i) {
            final String headerName = entList.get(i).getEntity_name().replace('_', ' ');
            Controller.editableFieldList.add(headerName);
        }
        dropTable();
        ConnManager.getInstance();
        final Connection conn = ConnManager.getConnection();
        final PreparedStatement stmt = conn.prepareStatement(PropertiesManager.getInstanceOfPropertiesManager().getProperty("SELECT_REPORT"));
        ResultSet rs = null;
        try {
            rs = stmt.executeQuery();
            dtm = Controller.cmt.getModel(rs);
            rs.close();
            stmt.close();
            ConnManager.getInstance();
            ConnManager.closeConnection();
        }
        catch (SQLException var8) {
            var8.printStackTrace();
        }
        return dtm;
    }

    public static List<HashMap<String, String>> getPcgList() {
        return Controller.pcgList;
    }

    public static List<HashMap<String, List<String>>> getEntityList() {
        return Controller.entityList;
    }

    public static List<String> getEditableFieldList() {
        return Controller.editableFieldList;
    }

    private void updatePCG(final Object value, final String id) {
        final PCG_Garanzie pcg = new PCG_Garanzie(id, (String)value);
        final DaoPCG_GaranzieImpl daoPcg = new DaoPCG_GaranzieImpl();
        daoPcg.update(pcg);
    }

    private void updateAffidamento(final Object value, final String id, final String entityName) {
        final DaoEntityImpl daoEnt = new DaoEntityImpl();
        final String headerName = entityName.replace(' ', '_');
        final int idEntity = daoEnt.findByName(headerName).get(0).getEntity_id();
        final Affidamento aff = new Affidamento(id, idEntity, Double.parseDouble((String)value));
        final DaoAffidamentoImpl daoAff = new DaoAffidamentoImpl();
        daoAff.update(aff);
    }

    public void salva() {
        Iterator i;
        HashMap map;
        Map.Entry entry;
        if (!getPcgList().isEmpty()) {
            i = getPcgList().iterator();

            while(i.hasNext()) {
                map = (HashMap)i.next();
                i = map.entrySet().iterator();
                while(i.hasNext()) {
                    entry = (Map.Entry)i.next();
                    this.updatePCG(entry.getValue(), (String)entry.getKey());
                    }
                }
            }


        if (!getEntityList().isEmpty()) {
            i = getEntityList().iterator();

            while(i.hasNext()) {
                map = (HashMap)i.next();
                i = map.entrySet().iterator();

                while(i.hasNext()) {
                    entry = (Map.Entry)i.next();
                    String s = ((String)((List)entry.getValue()).get(0)).replace(",", ".");
                    this.updateAffidamento(s, (String)entry.getKey(), (String)((List)entry.getValue()).get(1));
                    }
                }
            }

        getPcgList().clear();
        getEntityList().clear();
    }


//        if (!getPcgList().isEmpty()) {
//            for (final HashMap map : getPcgList()) {
//                map.forEach((key, value) -> this.updatePCG(value, (String) key));
//            }
//        }
//        if (!getEntityList().isEmpty()) {
//            for (final HashMap map : getEntityList()) {
//
////                for (final Map.Entry entry : map.entrySet()) {
////                    final String s = entry.getValue().get(0).replace(",", ".");;
////                    this.updateAffidamento( entry.getKey(), s, entry.getValue().get(1));
////                } //Arrays.stream(map.entrySet().toArray()).toList().get(1).toString().replace(",", "."); getValue().get(0).replace(",", ".");
//            }
//        }
//        getPcgList().clear();
//        getEntityList().clear();
//    }

    public void export() {
        final String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        this.pathOutFinalFileJrxml = PropertiesManager.getInstanceOfPropertiesManager().getProperty("JRXML_PATH_OUT") + timeStamp + ".jrxml";
        this.reportPath = PropertiesManager.getInstanceOfPropertiesManager().getProperty("EXCEL_PATH_OUT") + timeStamp + ".xls";
        this.positionX = 0;
        this.size = 0;
        ConnManager.getInstance();
        final Connection conn = ConnManager.getConnection();
        ResultSet rs = null;
        this.positionX = 0;
        this.size = 0;
        try {
            final PreparedStatement stmt = conn.prepareStatement(PropertiesManager.getInstanceOfPropertiesManager().getProperty("SELECT_REPORT"));
            rs = stmt.executeQuery();
            final ResultSetMetaData metaData = rs.getMetaData();
            for (int columnCount = metaData.getColumnCount(), column = 1; column <= columnCount; ++column) {
                final String headerName = metaData.getColumnName(column).replace('_', ' ');
                if (!headerName.equals(PropertiesManager.getInstanceOfPropertiesManager().getProperty("COL_ID"))) {
                    this.newElementHashMap(headerName);
                }
            }
            this.list = Controller.cmt.getDataListMap(rs);
            stmt.close();
            rs.close();
            ConnManager.getInstance();
            ConnManager.closeConnection();
        }
        catch (SQLException var15) {
            var15.printStackTrace();
        }
        final VelocityEngine ve = new VelocityEngine();
        ve.init();
        final Template t = ve.getTemplate(PropertiesManager.getInstanceOfPropertiesManager().getProperty("TEMPLATE_PATH"));
        final VelocityContext context = new VelocityContext();
        final StringWriter writer = new StringWriter();
        context.put("headerList", (Object)this.list3);
        t.merge((Context)context, (Writer)writer);
        try {
            final Writer writer2 = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(this.pathOutFinalFileJrxml), this.codifica));
            writer2.write(writer.toString());
            writer2.close();
        }
        catch (UnsupportedEncodingException var16) {
            var16.printStackTrace();
        }
        catch (FileNotFoundException var17) {
            var17.printStackTrace();
        }
        catch (IOException var18) {
            var18.printStackTrace();
        }
        final ReportManager rm = new ReportManager(this.pathOutFinalFileJrxml, this.reportPath, ReportManager.ExportFormat.EXCEL, this.list, null);
        try {
            rm.run();
        }
        catch (JRException var19) {
            var19.printStackTrace();
        }
    }

    private void newElementHashMap(final String name) {
        final HashMap<String, String> map = new HashMap<String, String>();
        this.positionX += this.size;
        this.size = name.length() * 10 + 50;
        map.put("name", name);
        map.put("value", "$F{" + name + "}");
        map.put("x", Integer.toString(this.positionX));
        map.put("size", Integer.toString(this.size));
        this.list3.add(map);
    }

    public void ricalcola() {
        DefaultTableModel dtm = null;
        try {
            ConnManager.getInstance();
            final Connection conn = ConnManager.getConnection();
            final PreparedStatement stmt_1 = conn.prepareStatement(PropertiesManager.getInstanceOfPropertiesManager().getProperty("MISYS_BATCH"));
            stmt_1.execute();
            final PreparedStatement stmt_2 = conn.prepareStatement(PropertiesManager.getInstanceOfPropertiesManager().getProperty("INIT_BATCH"));
            stmt_2.execute();
            final PreparedStatement stmt = conn.prepareStatement(PropertiesManager.getInstanceOfPropertiesManager().getProperty("SELECT_REPORT"));
            ResultSet rs = null;
            rs = stmt.executeQuery();
            dtm = Controller.cmt.getModel(rs);
            rs.close();
            stmt.close();
            ConnManager.getInstance();
            ConnManager.closeConnection();
        }
        catch (SQLException var7) {
            var7.printStackTrace();
        }
    }

    public static void dropTable() {
        ConnManager.getInstance();
        final Connection conn = ConnManager.getConnection();
        try {
            final PreparedStatement stmt_1 = conn.prepareStatement(PropertiesManager.getInstanceOfPropertiesManager().getProperty("DROP_TEMP"));
            stmt_1.execute();
            stmt_1.close();
            conn.close();
        }
        catch (SQLException var3) {
            System.out.println("Table temporanea non trovata");
            var3.printStackTrace();
        }
    }

    static {
        Controller.editableFieldList = new ArrayList<String>();
        Controller.pcgList = new ArrayList<HashMap<String, String>>();
        Controller.entityList = new ArrayList<HashMap<String, List<String>>>();
        Controller.cmt = new CreateModelTable();
    }
}

