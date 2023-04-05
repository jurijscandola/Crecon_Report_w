package  action;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class MyDefaultTableCellRenderer extends DefaultTableCellRenderer
{
    @Override
    public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected, final boolean hasFocus, final int row, final int column) {
        final NumberFormat formatter = NumberFormat.getInstance(Locale.GERMAN);
        formatter.setMaximumFractionDigits(2);
        formatter.setMinimumFractionDigits(2);
        String val = null;
        if (value != null) {
            if (value instanceof BigDecimal) {
                val = formatter.format(value);
            }
            else if (value instanceof String) {
                if (String.valueOf(value).contains("%")) {
                    String temp = null;
                    String temp2 = null;
                    temp = String.valueOf(value);
                    temp2 = temp.substring(0, temp.length() - 2);
                    final BigDecimal bd = new BigDecimal(temp2);
                    val = formatter.format(bd) + " %";
                }
                else {
                    String temp = null;
                    String temp2 = null;
                    temp = String.valueOf(value);
                    if (temp.contains(",")) {
                        temp2 = (temp = temp.replace(",", "."));
                    }
                    final BigDecimal bd = new BigDecimal(temp);
                    val = formatter.format(bd);
                }
            }
            return super.getTableCellRendererComponent(table, val, isSelected, hasFocus, row, column);
        }
        return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
    }
}
