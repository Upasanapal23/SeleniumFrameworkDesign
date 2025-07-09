package utils;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ConfigReader {

    private Properties properties;
    private static ConfigReader configReader;

    private ConfigReader() {
        Logger logger = Logger.getLogger(ConfigReader.class.getName());
        properties = new Properties();
        try (FileInputStream fis = new FileInputStream(System.getProperty("user.dir") + "/src/main/java/resources/config.properties")) {
            properties.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
            logger.log(Level.SEVERE, "Failed to load config.properties file.", e);
 throw new RuntimeException("Failed to load config.properties file.", e);
        }
    }

    // Singleton pattern: get single instance
    public static ConfigReader getInstance() {
        if (configReader == null) {
            configReader = new ConfigReader();
        }
        return configReader;
    }
    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public String getBrowser() {
        return properties.getProperty("browser");
    }

    public String getBaseUrl() {
        return properties.getProperty("url");
    }

    public String getUsername() {
        return properties.getProperty("username");
    }

    public String getPassword() {
        return properties.getProperty("password");
    }

    public String getLoginUrl() {
        return properties.getProperty("loginUrl");
    }

    /**
     *  notes Gets the explicit wait timeout from the config.properties file.
     * Returns a default value of 20 seconds if the property is not found or invalid.
     */
    
    public long getExplicitWaitTimeout() {
        String timeout = properties.getProperty("explicitWaitTimeout");
        if (timeout != null) {
            try {
                return Long.parseLong(timeout);
            } catch (NumberFormatException e) {
                
            	// Log or handle the exception if the value is not a valid number
            }
        }
        
        return 20; // Default to 20 seconds if the property is not found or invalid
    }
}