package  action;

import org.jdesktop.swingx.decorator.HighlightPredicate;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import  utils.PropertiesManager;
import  controller.Controller;
import javax.swing.JTextField;
import org.jdesktop.swingx.decorator.Highlighter;
import org.jdesktop.swingx.decorator.FontHighlighter;
import java.awt.Font;
import org.jdesktop.swingx.decorator.ColorHighlighter;
import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import org.jdesktop.swingx.JXTable;

public class ModellingModel
{
    private JXTable table;
    private List<HashMap<String, String>> ls;

    public ModellingModel(final JXTable table, final List<HashMap<String, String>> ls) {
        this.table = table;
        this.ls = ls;
    }

    public void modelling() {
        final HighlightPredicate myPredicate = (HighlightPredicate)new MyHighlightPredicate();
        final ColorHighlighter highlighter = new ColorHighlighter(myPredicate, (Color)null, Color.BLUE);
        final FontHighlighter hl = new FontHighlighter(myPredicate, new Font("TimesRoman", 1, 12));
        this.table.addHighlighter((Highlighter)highlighter);
        this.table.addHighlighter((Highlighter)hl);
        final JTextField textField = new JTextField();
        for (int i = 0; i < this.table.getColumnCount(); ++i) {
            if (!Controller.getEditableFieldList().contains(this.table.getColumnExt(i).getHeaderValue()) && !this.table.getColumnExt(i).getHeaderValue().equals(PropertiesManager.getInstanceOfPropertiesManager().getProperty("COL_PCG_GARANZIE"))) {
                this.table.getColumnExt(i).setEditable(false);
            }
            if (this.table.getColumnModel().getColumn(i).getHeaderValue().equals(PropertiesManager.getInstanceOfPropertiesManager().getProperty("COL_AFFIDAMENTI")) || this.table.getColumnModel().getColumn(i).getHeaderValue().equals(PropertiesManager.getInstanceOfPropertiesManager().getProperty("COL_AFFIDAMENTI_ORIGINAL"))) {
                this.table.getColumnModel().getColumn(i).setCellRenderer(new MyDefaultTableCellRenderer());
            }
            if (Controller.getEditableFieldList().contains(this.table.getColumnExt(i).getHeaderValue())) {
                this.table.getColumnModel().getColumn(i).setCellEditor(new MyDefaultCellEditor(textField, this.table));
                this.table.getColumnModel().getColumn(i).setCellRenderer(new MyDefaultTableCellRenderer());
            }
            if (this.table.getColumnModel().getColumn(i).getHeaderValue().equals(PropertiesManager.getInstanceOfPropertiesManager().getProperty("COL_TOT"))) {
                this.table.getColumnModel().getColumn(i).setCellRenderer(new MyDefaultTableCellRendererTot(this.ls));
            }
            if (this.table.getColumnModel().getColumn(i).getHeaderValue().equals(PropertiesManager.getInstanceOfPropertiesManager().getProperty("COL_PER"))) {
                this.table.getColumnModel().getColumn(i).setCellRenderer(new MyDefaultTableCellRenderer());
            }
            if (this.table.getColumnModel().getColumn(i).getHeaderValue().equals(PropertiesManager.getInstanceOfPropertiesManager().getProperty("COL_RATING"))) {
                this.table.getColumnModel().getColumn(i).setCellRenderer(new MyDefaultTableCellRendererRating());
            }
            this.table.getColumnExt(i).setSortable(false);
        }
        this.table.getModel().addTableModelListener(new MyTableModelListener(this.table));
    }
}

