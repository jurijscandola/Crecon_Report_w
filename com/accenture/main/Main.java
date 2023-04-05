package  main;

import  action.MyWindowAdapter;
import  view.Report;
import  view.Login;

import java.awt.EventQueue;

public class Main
{
    public static void main(final String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    final Login l = new Login();
                    l.showLoginDialog();
                    final Report window = new Report();
                    window.getFrame().setVisible(true);
                    window.getFrame().addWindowListener(new MyWindowAdapter());
                }
                catch (Exception var3) {
                    var3.printStackTrace();
                }
            }
        });
    }
}