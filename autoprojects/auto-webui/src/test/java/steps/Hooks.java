package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import logs.BaseLog;
import org.openqa.selenium.WebDriver;
import pages.PageObjectManager;
import utils.PropertyLoader;

import static utils.PropertyLoader.getDomain;

public class Hooks {
    private static WebDriver driver;

    public static WebDriver getDriver() {
        return driver;
    }

    public static PageObjectManager getPageObjectManager() {
        return new PageObjectManager(driver);
    }

    @Before
    public void beforeScenario() {
        BaseLog.info("Scenario start");
        driver = PropertyLoader.getDriver();
        driver.manage().window().maximize(); //maximize the window
        driver.get(getDomain());
    }

    @After
    public void afterScenario() {
        if(null != driver) {
            driver.close();
            driver.quit();
        }
        BaseLog.info("Scenario end");
    }
}
