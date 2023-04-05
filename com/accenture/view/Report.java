package  view;

import controller.Controller;
import utils.PropertiesManager;
import  action.ActionRicalcolo;
import  action.ActionSalva;
import  action.ActionReset;
import  action.ActionExport;
import  action.MyColumnControlButton;
import  action.ModellingModel;

import org.jdesktop.swingx.table.ColumnControlButton;
import java.awt.Color;
import org.jdesktop.swingx.JXButton;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import java.awt.FlowLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JScrollPane;
import java.awt.BorderLayout;
import javax.swing.JComponent;
import javax.swing.table.TableModel;
import org.jdesktop.swingx.JXTable;
import java.awt.Font;
import javax.swing.JLabel;
import java.awt.Component;
import javax.swing.JPanel;
import java.awt.LayoutManager;
import javax.swing.BoxLayout;
import java.util.ArrayList;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import javax.swing.JFrame;

public class Report
{
    private JFrame frame;
    private Controller controller;
    private List<HashMap<String, String>> ls;

    public JFrame getFrame() {
        return this.frame;
    }

    public Report() throws SQLException {
        this.ls = null;
        this.initialize();
    }

    private void initialize() throws SQLException {
        this.ls = new ArrayList<HashMap<String, String>>();
        this.controller = new Controller();
        (this.frame = new JFrame()).setTitle(PropertiesManager.getInstanceOfPropertiesManager().getProperty("TITLE"));
        this.frame.setBounds(100, 100, 943, 589);
        this.frame.setDefaultCloseOperation(3);
        this.frame.getContentPane().setLayout(new BoxLayout(this.frame.getContentPane(), 1));
        final JPanel pnlTitle = new JPanel();
        this.frame.getContentPane().add(pnlTitle);
        final JLabel lblTitle = new JLabel(PropertiesManager.getInstanceOfPropertiesManager().getProperty("TITLE"));
        lblTitle.setFont(new Font("Times New Roman", 1, 30));
        pnlTitle.add(lblTitle);
        final JPanel pnlTable = new JPanel();
        this.frame.getContentPane().add(pnlTable);
        final JXTable table = new JXTable((TableModel)this.controller.getModel());
        final ModellingModel mm = new ModellingModel(table, this.ls);
        mm.modelling();
        table.getTableHeader().setReorderingAllowed(false);
        table.setHorizontalScrollEnabled(true);
        final ColumnControlButton columnControl = new MyColumnControlButton(table);
        table.setColumnControl((JComponent)columnControl);
        table.setColumnControlVisible(true);
        table.packAll();
        table.getColumnModel().getColumn(0).setMinWidth(0);
        table.getColumnModel().getColumn(0).setMaxWidth(0);
        table.getColumnModel().getColumn(0).setWidth(0);
        pnlTable.setLayout(new BorderLayout(0, 0));
        final JScrollPane scrollPane_1 = new JScrollPane();
        scrollPane_1.setViewportView((Component)table);
        scrollPane_1.setPreferredSize(new Dimension(100, 50));
        pnlTable.add(scrollPane_1);
        final JPanel pnlFooter = new JPanel();
        this.frame.getContentPane().add(pnlFooter);
        pnlFooter.setLayout(new BoxLayout(pnlFooter, 0));
        final JPanel panel_1 = new JPanel();
        final FlowLayout flowLayout_1 = (FlowLayout)panel_1.getLayout();
        flowLayout_1.setAlignment(2);
        pnlFooter.add(panel_1);
        final JButton btnSalva = new JButton(PropertiesManager.getInstanceOfPropertiesManager().getProperty("BTN_SAVE"));
        panel_1.add(btnSalva);
        final JButton btnReset = new JButton(PropertiesManager.getInstanceOfPropertiesManager().getProperty("BTN_RESET"));
        panel_1.add(btnReset);
        final JButton btnExport = new JButton(PropertiesManager.getInstanceOfPropertiesManager().getProperty("BTN_EXPORT"));
        panel_1.add(btnExport);
        btnExport.addActionListener(new ActionExport());
        btnReset.addActionListener(new ActionReset(this.ls, table, this.frame));
        btnSalva.addActionListener(new ActionSalva(this.ls, table, this.frame));
        final JPanel panel = new JPanel();
        final FlowLayout flowLayout = (FlowLayout)panel.getLayout();
        flowLayout.setAlignment(2);
        pnlFooter.add(panel);
        final JXButton b = new JXButton(PropertiesManager.getInstanceOfPropertiesManager().getProperty("BTN_RICALCOLO"));
        b.setBackground(Color.DARK_GRAY);
        b.setForeground(Color.WHITE);
        b.addActionListener((ActionListener)new ActionRicalcolo(this.ls, table, this.frame));
        panel.add((Component)b);
    }
}