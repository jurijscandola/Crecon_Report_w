package  action;

import java.awt.event.ActionEvent;
import  controller.Controller;
import java.awt.event.ActionListener;

public class ActionExport implements ActionListener
{
    private Controller controller;

    public ActionExport() {
        this.controller = new Controller();
    }

    @Override
    public void actionPerformed(final ActionEvent e) {
        this.controller.export();
    }
}

