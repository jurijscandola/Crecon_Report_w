package  view;

import  action.MyLoginService;
import  action.MyLoginAdapter;

import org.jdesktop.swingx.auth.LoginService;
import org.jdesktop.swingx.auth.LoginListener;
import java.awt.event.WindowEvent;
import java.awt.Frame;
import org.jdesktop.swingx.JXLoginPane;
import javax.swing.JFrame;

public class Login
{
    private JFrame frame;
    private String userName;

    public void showLoginDialog() {
        (this.frame = new JFrame()).setDefaultCloseOperation(2);
        final JXLoginPane loginPane = new JXLoginPane();
        final LoginListener loginListener = (LoginListener)new MyLoginAdapter(loginPane, this);
        final LoginService loginService = new MyLoginService();
        loginService.addLoginListener(loginListener);
        loginPane.setLoginService(loginService);
        final JXLoginPane.JXLoginDialog dialog = new JXLoginPane.JXLoginDialog((Frame)this.frame, loginPane);
        dialog.setDefaultCloseOperation(2);
        dialog.setVisible(true);
        if (loginPane.getStatus() == JXLoginPane.Status.CANCELLED) {
            this.frame.dispatchEvent(new WindowEvent(this.frame, 201));
        }
    }

    public String getUserName() {
        return this.userName;
    }

    public void setUserName(final String userName) {
        this.userName = userName;
    }
}
