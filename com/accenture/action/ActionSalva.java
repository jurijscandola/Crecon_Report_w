package  action;

import java.sql.SQLException;
import java.awt.Component;
import javax.swing.JOptionPane;
import  utils.PropertiesManager;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import org.jdesktop.swingx.JXTable;
import java.util.HashMap;
import java.util.List;
import  controller.Controller;
import java.awt.event.ActionListener;

public class ActionSalva implements ActionListener
{
    private Controller controller;
    private List<HashMap<String, String>> ls;
    private JXTable table;
    private JFrame frame;

    public ActionSalva(final List<HashMap<String, String>> ls, final JXTable table, final JFrame frame) {
        this.ls = ls;
        this.table = table;
        this.frame = frame;
        this.controller = new Controller();
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        try {
            this.ls.clear();
            this.controller.salva();
            this.table.setModel((TableModel)this.controller.getModel());
            final ModellingModel mm = new ModellingModel(this.table, this.ls);
            mm.modelling();
            this.table.packAll();
            this.table.getColumnModel().getColumn(0).setMinWidth(0);
            this.table.getColumnModel().getColumn(0).setMaxWidth(0);
            this.table.getColumnModel().getColumn(0).setWidth(0);
            final String st = PropertiesManager.getInstanceOfPropertiesManager().getProperty("SAVE");
            JOptionPane.showMessageDialog(this.frame, st);
        }
        catch (SQLException var4) {
            var4.printStackTrace();
            JOptionPane.showMessageDialog(this.frame, PropertiesManager.getInstanceOfPropertiesManager().getProperty("ERROR"));
        }
    }
}

