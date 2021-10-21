package utils;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import java.io.IOException;
import java.util.Properties;

public class PropertyLoader {

    public static WebDriver getDriver() {

        String browser = readProperty("browserType");
        WebDriver driver = null;

        switch (browser) {
            case "firefox" -> {
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
            }
            case "chrome" -> {
                ChromeOptions options = new ChromeOptions();
//                options.addArguments("--headless");
                options.addArguments("--disable-gpu");
                WebDriverManager.chromedriver().setup();
                driver = new ChromeDriver(options);
            }
            case "ie" -> {
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
            }
        }
        return driver;
    }

    private static String readProperty(String name) {
        try {
            Properties props = new Properties();
            props.load(PropertyLoader.class.getResourceAsStream(getPropertiesPath()));
            return props.getProperty(name);
        }
        catch (IOException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static String getDomain() {
        return readProperty("domainUrl");
    }

    public static Integer getTimeOutInSeconds() {
        int timeOutInSeconds = 0;
        try{
            timeOutInSeconds = Integer.parseInt(
                    readProperty("timeOutInSeconds"));
        }
        catch (NumberFormatException ex){
            ex.printStackTrace();
        }
        return timeOutInSeconds;
    }

    private static String getPropertiesPath() {
        String env = System.getProperty("environment");
        return String.format("/%s-config.properties", env != null ? env : "test");
    }
}
