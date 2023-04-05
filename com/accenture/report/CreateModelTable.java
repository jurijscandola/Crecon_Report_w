package  report;

import  utils.PropertiesManager;

import javax.swing.table.DefaultTableModel;
import java.sql.SQLException;
import java.util.Map;
import java.sql.ResultSetMetaData;
import java.util.Vector;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class CreateModelTable
{
    private List<LinkedHashMap<String, Object>> list;

    public CreateModelTable() {
        this.list = null;
        this.list = new ArrayList<LinkedHashMap<String, Object>>();
    }

    public List<LinkedHashMap<String, Object>> getDataListMap(final ResultSet rs) throws SQLException {
        final ResultSetMetaData metaData = rs.getMetaData();
        final Vector<String> columnNames = new Vector<String>();
        final int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; ++column) {
            final String headerName = metaData.getColumnName(column).replace('_', ' ');
            columnNames.add(headerName);
        }
        while (rs.next()) {
            final Map<String, Object> map = new LinkedHashMap<String, Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; ++columnIndex) {
                final String colName = columnNames.get(columnIndex - 1);
                if (!colName.equals(PropertiesManager.getInstanceOfPropertiesManager().getProperty("COL_ID"))) {
                    if (colName.equals(PropertiesManager.getInstanceOfPropertiesManager().getProperty("COL_PER"))) {
                        final String st = rs.getString(columnIndex) + " %";
                        map.put(colName, st);
                    }
                    else {
                        map.put(colName, rs.getString(columnIndex));
                    }
                }
            }
            this.list.add((LinkedHashMap)map);
        }
        return this.list;
    }

    public DefaultTableModel getModel(final ResultSet rs) throws SQLException {
        final ResultSetMetaData metaData = rs.getMetaData();
        final Vector<String> columnNames = new Vector<String>();
        final int columnCount = metaData.getColumnCount();
        for (int column = 1; column <= columnCount; ++column) {
            final String headerName = metaData.getColumnName(column).replace('_', ' ');
            columnNames.add(headerName);
        }
        final Vector data = new Vector();
        while (rs.next()) {
            final Vector<Object> vector = new Vector<Object>();
            for (int columnIndex = 1; columnIndex <= columnCount; ++columnIndex) {
                if (columnIndex == columnCount) {
                    final String st = rs.getObject(columnIndex) + " %";
                    vector.add(st);
                }
                else {
                    vector.add(rs.getObject(columnIndex));
                }
            }
            data.add(vector);
        }
        return new DefaultTableModel(data, columnNames);
    }
}
