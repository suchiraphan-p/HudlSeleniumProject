package Utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {
    public static final String BASE_URL = "base.url";
    Properties prop = new Properties();

    public PropertiesLoader() {
        InputStream input = null;
        try {
            // default applicable everywhere
            input = getClass().getClassLoader().getResourceAsStream("config.properties");
            prop.load(input);

            String baseUrl = System.getProperty(BASE_URL);
            if (baseUrl != null)
                prop.setProperty(BASE_URL, baseUrl);

            System.out.println(prop);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
