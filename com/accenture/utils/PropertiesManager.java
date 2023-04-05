package  utils;

import java.util.Enumeration;
import java.util.HashMap;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.File;
import java.util.Properties;
import java.lang.String;
import java.util.logging.*;


public class PropertiesManager
{
    static final Logger log;
    private static PropertiesManager instance;
    private Properties prop;
    File file;
    private FileOutputStream out;
    private static String configFile;

    private PropertiesManager() {
        this.prop = null;
        this.out = null;
        PropertiesManager.configFile = System.getProperty("configFile");
        if (PropertiesManager.configFile == null || PropertiesManager.configFile == "") {
            PropertiesManager.log.info("VM propos not set. Please passing VM argument -DconfFile=/path/to/file");
        }
        FileInputStream input = null;
        try {
            this.file = new File(PropertiesManager.configFile);
            input = new FileInputStream(PropertiesManager.configFile);
            (this.prop = new Properties()).load(input);
        }
        catch (IOException var3) {
            PropertiesManager.log.info("Error creating PropertiesManager Class");
            PropertiesManager.log.info((String) var3.getMessage());
            var3.printStackTrace();
        }
    }

    public String getPathConfig() {
        return (PropertiesManager.configFile != null) ? PropertiesManager.configFile : null;
    }

    public static PropertiesManager getInstanceOfPropertiesManager() {
        if (PropertiesManager.instance == null) {
            PropertiesManager.instance = new PropertiesManager();
        }
        return PropertiesManager.instance;
    }

    public String getProperty(final String key) {
        if (this.prop.containsKey(key)) {
            return this.prop.getProperty(key);
        }
        PropertiesManager.log.info(("key[" + key + "] doesn't exist into property file"));
        return "";
    }

    public void setPrintQuery(final String key, final String value) {
        if (key.equals("printQuery") && value.equals("true")) {
            this.prop.setProperty("printQuery", value);
        }
    }

    public void setProperty(final String Key, final String value) {
        if (this.prop != null) {
            this.prop.setProperty(Key, value);
            PropertiesManager.log.info((String) ("Property update: " + Key + "=" + value));
        }
    }

    public boolean saveProperties() {
        if (this.prop != null) {
            try {
                this.out = new FileOutputStream(this.file);
                this.prop.store(this.out, null);
                PropertiesManager.log.info((String) "Properties saved");
                return true;
            }
            catch (IOException var2) {
                PropertiesManager.log.info("Error in properties save");
            }
        }
        return false;
    }

    public HashMap<String, String> getAllProperties() {
        HashMap<String, String> allProperties = null;
        if (this.prop != null) {
            allProperties = new HashMap<String, String>();
            final Enumeration em = this.prop.keys();
            while (em.hasMoreElements()) {
                final String key = (String) em.nextElement();
                allProperties.put(key, this.prop.getProperty(key));
            }
        }
        return allProperties;
    }

    static {
        log = Logger.getLogger(String.valueOf(PropertiesManager.class));
        PropertiesManager.instance = null;
    }
}



