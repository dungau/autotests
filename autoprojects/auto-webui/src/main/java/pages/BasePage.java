package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import utils.Utils;

import static utils.PropertyLoader.getTimeOutInSeconds;

public class BasePage {
    protected WebDriver driver;

    //Constructor
    public BasePage(WebDriver driver){
        this.driver = driver;
        Utils.setupWait(this.driver);
        PageFactory.initElements(new AjaxElementLocatorFactory(driver, getTimeOutInSeconds()), this);
    }

    public WebElement getPageTitleElementWithText(String text) {
        return this.driver.findElement(By.xpath("//*[@id='rightPanel']/div/div/h1[text()='" + text + "']"));
    }

    public WebElement getPageTitleElement() {
        return this.driver.findElement(By.xpath("//*[@id='rightPanel']/div/div/h1"));
    }
}
