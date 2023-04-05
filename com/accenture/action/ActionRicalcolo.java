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

public class ActionRicalcolo implements ActionListener
{
    private Controller controller;
    private List<HashMap<String, String>> ls;
    private JXTable table;
    private JFrame frame;

    public ActionRicalcolo(final List<HashMap<String, String>> ls, final JXTable table, final JFrame frame) {
        this.ls = ls;
        this.table = table;
        this.frame = frame;
        this.controller = new Controller();
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        try {
            Controller var10000 = this.controller;
            Controller.getEntityList().clear();
            var10000 = this.controller;
            Controller.getPcgList().clear();
            this.controller.ricalcola();
            this.table.setModel((TableModel)this.controller.getModel());
            this.ls.clear();
            final ModellingModel mm = new ModellingModel(this.table, this.ls);
            mm.modelling();
            this.table.packAll();
            this.table.getColumnModel().getColumn(0).setMinWidth(0);
            this.table.getColumnModel().getColumn(0).setMaxWidth(0);
            this.table.getColumnModel().getColumn(0).setWidth(0);
            JOptionPane.showMessageDialog(this.frame, PropertiesManager.getInstanceOfPropertiesManager().getProperty("RICALCOLO"));
        }
        catch (SQLException var10001) {
            var10001.printStackTrace();
            JOptionPane.showMessageDialog(this.frame, PropertiesManager.getInstanceOfPropertiesManager().getProperty("ERROR"));
        }
    }
}
