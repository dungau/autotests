package pages;

import io.github.bonigarcia.wdm.WebDriverManager;
import logs.BaseLog;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.*;

public class BaseTest {
    protected static WebDriver driver;

    @BeforeSuite(alwaysRun = true)
    public void setUp() {
        BaseLog.info("Test suite start");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }

    @AfterSuite
    public void teardown() {
        if(null != driver) {
            driver.quit();
        }
        BaseLog.info("Test suite end");
    }

    @BeforeClass
    public void beforeClass() {
        BaseLog.info("Test class start");
        driver.get("https://parabank.parasoft.com/parabank/index.htm");
        driver.manage().window().maximize(); //maximize the window
    }

    @AfterClass
    public void afterClass() {
        driver.close();
        BaseLog.info("Test class end");
    }
}
