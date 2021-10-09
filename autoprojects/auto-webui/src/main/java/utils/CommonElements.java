package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

public class CommonElements extends BasePage {

    //Locating register link
    @FindBy(linkText = "Register")
    public WebElement linkRegister;

    //Locating logout link
    @FindBy(linkText = "Log Out")
    public WebElement linkLogout;

    //Locating update contact info
    @FindBy(linkText = "Update Contact Info")
    public WebElement linkUpdateContactInfo;

    //Locating the title
    @FindBy(className = "title")
    public WebElement lblTitle;

    public CommonElements(WebDriver driver) {
        super(driver);
    }
}
