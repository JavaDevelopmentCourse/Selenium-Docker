package utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.util.Properties;

public class Config {

    private static final Logger log= LoggerFactory.getLogger(Config.class);
    private static final String DEFAULT_PROPERTIES="config/default.properties";

private static Properties prop;
    public static void init()
    {
         prop = loadProperties();

         for (String key: prop.stringPropertyNames())
        {
                if (System.getProperties().containsKey(key))
                {
                    prop.setProperty(key,System.getProperty(key));
                }
        }
         log.info("Test properties");
        for (String key: prop.stringPropertyNames())
        {
            log.info("{}={}",key,prop.getProperty(key));
        }
    }

    public static String get(String key)
    {
        return prop.getProperty(key);
    }
    private static Properties loadProperties()
    {
        Properties properties=new Properties();
        try {
            InputStream resource = ResourceLoader.getResource(DEFAULT_PROPERTIES);
            properties.load(resource);
        } catch (Exception e) {
            log.error("unable to read the property file {}",DEFAULT_PROPERTIES,e);
        }
        return properties;
    }
}
