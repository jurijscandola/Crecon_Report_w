package  action;

import java.util.EventObject;
import java.awt.Color;
import java.text.DecimalFormatSymbols;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import javax.swing.JTextField;
import org.jdesktop.swingx.JXTable;
import javax.swing.DefaultCellEditor;

public class MyDefaultCellEditor extends DefaultCellEditor
{
    private JXTable table;

    public MyDefaultCellEditor(final JTextField textField, final JXTable table) {
        super(textField);
        this.table = table;
    }

    @Override
    public Object getCellEditorValue() {
        final DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.HALF_UP);
        final DecimalFormatSymbols custom = new DecimalFormatSymbols();
        custom.setDecimalSeparator(',');
        df.setDecimalFormatSymbols(custom);
        return df.format(Double.parseDouble((String)super.getCellEditorValue()));
    }

    @Override
    public boolean stopCellEditing() {
        boolean result = false;
        try {
            result = super.stopCellEditing();
            ((JTextField)this.getComponent()).setBackground(Color.WHITE);
        }
        catch (NumberFormatException var3) {
            ((JTextField)this.getComponent()).setBackground(Color.RED);
            result = false;
        }
        return result;
    }

    @Override
    public boolean isCellEditable(final EventObject anEvent) {
        final int r = this.table.getSelectedRow();
        if (r == -1) {
            return false;
        }
        final String s = (String)this.table.getValueAt(r, 1);
        if (!"".equals(s) && null != s) {
            ((JTextField)this.getComponent()).setBackground(Color.WHITE);
            return super.isCellEditable(anEvent);
        }
        return false;
    }
}
