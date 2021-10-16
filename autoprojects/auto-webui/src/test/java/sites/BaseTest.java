package sites;

import logs.BaseLog;
import logs.BaseTestListener;
import org.openqa.selenium.WebDriver;
import static utils.PropertyLoader.*;

import org.testng.annotations.*;

@Listeners(BaseTestListener.class)
public class BaseTest {
    protected static WebDriver driver;

    @BeforeSuite(alwaysRun = true)
    public void setUp() {
        BaseLog.info("Test suite start");
        driver = getDriver();
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
        driver = getDriver();
        driver.get(getDomain());
        driver.manage().window().maximize(); //maximize the window
    }

    @AfterClass
    public void afterClass() {
        driver.close();
        BaseLog.info("Test class end");
    }
}
