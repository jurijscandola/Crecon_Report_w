package  action;

import  utils.PropertiesManager;
import org.jdesktop.swingx.table.TableColumnExt;
import javax.swing.table.TableColumn;
import org.jdesktop.swingx.JXTable;
import org.jdesktop.swingx.table.ColumnControlButton;

public class MyColumnControlButton extends ColumnControlButton
{
    public MyColumnControlButton(final JXTable table) {
        super(table);
    }

    protected ColumnControlButton.ColumnVisibilityAction createColumnVisibilityAction(final TableColumn column) {
        return (column instanceof TableColumnExt && ((TableColumnExt)column).getHeaderValue().equals(PropertiesManager.getInstanceOfPropertiesManager().getProperty("COL_ID"))) ? null : super.createColumnVisibilityAction(column);
    }
}
