package  action;

import java.util.Map;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import  utils.PropertiesManager;
import javax.swing.event.TableModelEvent;
import  controller.Controller;
import org.jdesktop.swingx.JXTable;
import javax.swing.event.TableModelListener;

public class MyTableModelListener implements TableModelListener
{
    private JXTable table;
    private Controller controller;

    public MyTableModelListener(final JXTable table) {
        this.table = table;
    }

    @Override
    public void tableChanged(final TableModelEvent e) {
        final Object st = this.table.getValueAt(e.getFirstRow(), e.getColumn());
        final String id = String.valueOf(this.table.getValueAt(e.getFirstRow(), 0));
        final String columnName = (String)this.table.getColumnExt(e.getColumn()).getHeaderValue();
        if (columnName.equals(PropertiesManager.getInstanceOfPropertiesManager().getProperty("COL_PCG_GARANZIE"))) {
            final Map<String, String> map = new HashMap<String, String>();
            map.put(id, (String)st);
            final Controller var10000 = this.controller;
            Controller.getPcgList().add((HashMap)map);
        }
        else {
            int i = 0;
            while (true) {
                Controller var10001 = this.controller;
                if (i >= Controller.getEditableFieldList().size()) {
                    break;
                }
                var10001 = this.controller;
                if (columnName.equals(Controller.getEditableFieldList().get(i))) {
                    final Map<String, List<String>> map2 = new HashMap<String, List<String>>();
                    final List<String> ls = new ArrayList<String>();
                    ls.add(String.valueOf(st));
                    var10001 = this.controller;
                    ls.add(Controller.getEditableFieldList().get(i));
                    map2.put(id, ls);
                    final Controller var10000 = this.controller;
                    Controller.getEntityList().add((HashMap)map2);
                }
                ++i;
            }
        }
    }
}

