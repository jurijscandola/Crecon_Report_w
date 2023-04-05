package  action;

import  controller.Controller;

import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

public class MyWindowAdapter extends WindowAdapter
{
    @Override
    public void windowClosing(final WindowEvent e) {
        super.windowClosing(e);
        Controller.dropTable();
    }
}
