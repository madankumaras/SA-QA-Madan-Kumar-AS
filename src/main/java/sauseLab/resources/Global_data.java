package sauseLab.resources;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Global_data {
    private Properties prop;

    public Global_data() throws IOException {
        prop = new Properties();
        FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\sauseLab\\resources\\Globaldata.properties");
        prop.load(fis);
    }

    public String getBrowser() {
        return prop.getProperty("browser");
    }

    public String getBaseUrl() {
        return prop.getProperty("baseUrl");
    }
 
    
}
