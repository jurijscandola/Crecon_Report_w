package  action;

import  utils.PropertiesManager;
import org.jdesktop.swingx.auth.LoginEvent;
import  view.Login;
import org.jdesktop.swingx.JXLoginPane;
import org.jdesktop.swingx.auth.LoginAdapter;

public class MyLoginAdapter extends LoginAdapter
{
    private JXLoginPane loginPane;
    private Login login;

    public MyLoginAdapter(final JXLoginPane loginPane, final Login login) {
        this.loginPane = new JXLoginPane();
        this.loginPane = loginPane;
        this.login = login;
    }

    public void loginFailed(final LoginEvent source) {
        source.getCause().getMessage();
        String message;
        if (!this.loginPane.getUserName().isEmpty() && this.loginPane.getPassword().length != 0) {
            message = PropertiesManager.getInstanceOfPropertiesManager().getProperty("LOGIN_ERROR");
        }
        else {
            message = PropertiesManager.getInstanceOfPropertiesManager().getProperty("LOGIN_ERROR_BLANK");
        }
        this.loginPane.setErrorMessage(message);
    }

    public void loginSucceeded(final LoginEvent source) {
        this.login.setUserName(this.loginPane.getUserName());
    }
}
