package  action;

import  dao.ConnManager;
import org.jdesktop.swingx.auth.LoginService;

public class MyLoginService extends LoginService
{
    public boolean authenticate(final String name, final char[] password, final String server) throws Exception {
        ConnManager.getInstance();
        ConnManager.setUserName(name);
        ConnManager.getInstance();
        ConnManager.setPassword(String.valueOf(password));
        ConnManager.getInstance();
        return !ConnManager.getConnection().equals(null);
    }
}
