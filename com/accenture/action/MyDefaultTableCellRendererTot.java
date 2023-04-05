package  action;

import java.awt.Color;
import java.math.BigDecimal;
import javax.swing.JTextField;
import java.text.DecimalFormatSymbols;
import java.text.DecimalFormat;
import java.awt.Component;
import javax.swing.JTable;
import java.util.HashMap;
import java.util.List;
import javax.swing.table.DefaultTableCellRenderer;

public class MyDefaultTableCellRendererTot extends DefaultTableCellRenderer
{
    private List<HashMap<String, String>> ls;

    public MyDefaultTableCellRendererTot(final List<HashMap<String, String>> ls) {
        this.ls = ls;
    }

    @Override
    public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected, final boolean hasFocus, final int row, final int column) {
        final DecimalFormat df = new DecimalFormat();
        final DecimalFormatSymbols custom = new DecimalFormatSymbols();
        custom.setDecimalSeparator(',');
        custom.setGroupingSeparator('.');
        df.setGroupingSize(3);
        df.setDecimalFormatSymbols(custom);
        String formattedValue = null;
        final JTextField editor = new JTextField();
        editor.setEditable(false);
        if (value != null) {
            if (value instanceof BigDecimal) {
                formattedValue = df.format(((BigDecimal)value).doubleValue()).toString();
            }
            else {
                formattedValue = df.format(Double.parseDouble((String)value)).toString();
            }
            if (formattedValue.equals(null)) {
                formattedValue = "0";
            }
            editor.setText(formattedValue);
        }
        final String st = String.valueOf(table.getValueAt(row, table.getColumnCount() - 1));
        final String stR = st.replace(" %", "");
        final double tot = Double.parseDouble(stR);
        if (tot >= 100.0) {
            editor.setBackground(Color.RED);
            String cpty = (String)table.getValueAt(row, 1);
            if (cpty == null) {
                cpty = String.valueOf(table.getValueAt(row, 2));
                final HashMap map = new HashMap();
                map.put("Grp", cpty + ": " + tot);
                this.ls.add(map);
            }
            else {
                final HashMap map = new HashMap();
                map.put("Cpty", cpty + ": " + tot);
                this.ls.add(map);
            }
        }
        else if (tot >= 80.0 && tot < 100.0) {
            editor.setBackground(Color.ORANGE);
            String cpty = (String)table.getValueAt(row, 1);
            if (cpty == null) {
                cpty = String.valueOf(table.getValueAt(row, 2));
                final HashMap map = new HashMap();
                map.put("Grp", cpty + ": " + tot);
                this.ls.add(map);
            }
            else {
                final HashMap map = new HashMap();
                map.put("Cpty", cpty + ": " + tot);
                this.ls.add(map);
            }
        }
        else {
            editor.setBackground(Color.WHITE);
        }
        return editor;
    }
}
