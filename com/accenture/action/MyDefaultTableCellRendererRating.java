package  action;

import java.awt.Color;
import javax.swing.JTextField;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class MyDefaultTableCellRendererRating extends DefaultTableCellRenderer
{
    @Override
    public Component getTableCellRendererComponent(final JTable table, final Object value, final boolean isSelected, final boolean hasFocus, final int row, final int column) {
        final JTextField editor = new JTextField();
        if (value != null) {
            editor.setText(value.toString());
        }
        else {
            editor.setText("");
        }
        editor.setEditable(false);
        if (!"".equals(table.getValueAt(row, 5)) && null != table.getValueAt(row, 5)) {
            editor.setBackground(Color.WHITE);
        }
        else {
            editor.setBackground(Color.RED);
        }
        return editor;
    }
}
