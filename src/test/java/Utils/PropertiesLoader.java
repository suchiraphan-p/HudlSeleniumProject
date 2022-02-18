package Utils;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {
    Properties prop = new Properties();

    public PropertiesLoader() {
        InputStream input = null;
        try {
            // default applicable everywhere
            input = getClass().getClassLoader().getResourceAsStream("config.properties");
            prop.load(input);
            System.out.println(prop);

        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            try {
                input.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Object loadProperty(String key) {
        return prop.getProperty(key);
    }
}
