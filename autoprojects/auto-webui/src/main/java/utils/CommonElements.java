package utils;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.BasePage;

import static utils.Utils.clickButton;
import static utils.Utils.getElementContent;

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

    @Step("Click logout button")
    public void clickLogoutButton() {
        clickButton(linkLogout);
    }

    @Step("Click register link")
    public void clickRegisterLink() {
        clickButton(linkRegister);
    }

    @Step("Click update contact info link")
    public void clickUpdateContactInfoLink() {
    clickButton(linkUpdateContactInfo);
    }

    @Step("Get message")
    public String getMessage() {
        Utils.waitPageLoad();
        return getElementContent(lblTitle);
    }
}
