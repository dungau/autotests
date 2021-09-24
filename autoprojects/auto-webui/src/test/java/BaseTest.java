import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.*;
import sites.SwagLabsPage;

public class BaseTest {
    protected static WebDriver driver;

    @BeforeSuite(alwaysRun = true)
    public void setUp() {
        System.out.println("start suite");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        PageFactory.initElements(driver, SwagLabsPage.class);
    }

    @AfterSuite
    public void teardown() {
        if(null != driver) {
            driver.close();
            driver.quit();
        }
    }

    @BeforeClass
    public void openSite() {
        driver.get("https://parabank.parasoft.com/parabank/index.htm");
        driver.manage().window().maximize(); //maximize the window
    }
}
