package  dao;

import java.sql.SQLException;
import java.sql.DriverManager;
import java.sql.Connection;

public class ConnManager
{
    private static String url;
    private static String dbName;
    private static String driver;
    private static String userName;
    private static String password;
    private static Connection conn;
    private static ConnManager instance;

    public static ConnManager getInstance() {
        if (ConnManager.instance == null) {
            ConnManager.instance = new ConnManager();
        }
        return ConnManager.instance;
    }

    public static Connection getConnection() {
        try {
            ConnManager.driver = "net.sourceforge.jtds.jdbc.Driver";
            ConnManager.url = "jdbc:jtds:sybase://10.103.61.207:4901/";
            ConnManager.dbName = "Kustom";
            Class.forName(ConnManager.driver).newInstance();
            System.out.println(userName);
            System.out.println(password);
            ConnManager.conn = DriverManager.getConnection(ConnManager.url + ConnManager.dbName, ConnManager.userName, ConnManager.password);
        }
        catch (SQLException var1) {
            var1.printStackTrace();
        }
        catch (InstantiationException var2) {
            var2.printStackTrace();
        }
        catch (IllegalAccessException var3) {
            var3.printStackTrace();
        }
        catch (ClassNotFoundException var4) {
            var4.printStackTrace();
        }
        return ConnManager.conn;
    }

    public static void closeConnection() {
        try {
            ConnManager.conn.close();
        }
        catch (SQLException var1) {
            var1.printStackTrace();
        }
    }

    public static String getUserName() {
        return ConnManager.userName;
    }

    public static void setUserName(final String userName) {
        ConnManager.userName = userName;
    }

    public static String getPassword() {
        return ConnManager.password;
    }

    public static void setPassword(final String password) {
        ConnManager.password = password;
    }
}

