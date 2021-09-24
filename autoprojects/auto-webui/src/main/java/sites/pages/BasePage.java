package sites.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import utils.GlobalVariables;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    //Constructor
    public BasePage(WebDriver driver){
        Reporter.log("Init drive");
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(GlobalVariables.LONG_WAIT));
        PageFactory.initElements(driver, this);
    }

}
