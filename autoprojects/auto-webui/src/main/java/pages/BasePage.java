package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import static utils.PropertyLoader.getTimeOutInSeconds;

public class BasePage {
    protected WebDriver driver;

    //Constructor
    public BasePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, getTimeOutInSeconds()), this);
    }
}
