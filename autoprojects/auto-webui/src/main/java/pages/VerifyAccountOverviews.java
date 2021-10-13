package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import utils.Utils;

import java.time.Duration;

import static utils.Utils.*;

public class VerifyAccountOverviews extends BasePage {

    public VerifyAccountOverviews(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//*[@id='leftPanel']/ul/li[1]/a")
    private WebElement linkOpenNewAccount;

    @FindBy(xpath = "//*[@id='rightPanel']/div/div/form/div/input[@type='submit' and not(@disabled)]")
    private WebElement buttonOpenNewAccount;

    @FindBy(xpath = "//*[@id='fromAccountId']")
    private WebElement existingAccountComboBox;

    @FindBy(xpath = "//*[@id='fromAccountId']/option[1]")
    private WebElement firstExistingAccountComboBoxOption;

    @FindBy(xpath = "//*[@id='newAccountId']")
    private WebElement linkAccountNumber;

    @FindBy(xpath = "//*[@id='leftPanel']/ul/li[2]/a")
    private WebElement linkAccountOverview;

    @Step("User (logged in) click on 'Open New Account' link")
    public String clickOnOpenNewAccountLink() {
        clickButton(linkOpenNewAccount);
        Utils.waitUntilXPathToBeMoreThan("//*[@id='fromAccountId']/option", 0);
        String selectedValue = (new Select(existingAccountComboBox)).getFirstSelectedOption().getText();
        return selectedValue;
    }

    @Step("User (logged in) click on 'Open New Account' button on 'Open New Account' page to open new account")
    public String clickOnOpenNewAccountButton() {
        // ajax/SPA
        Utils.waitUntilElementClickable(buttonOpenNewAccount);
        clickButton(buttonOpenNewAccount);
        Utils.waitUntilElementVisible(linkAccountNumber);
        String newId = linkAccountNumber.getText();
        return newId;
    }

    @Step("User (logged in) check the available balance on 'Accounts Overview' link")
    public void checkOnLinkAccountOverview() {
        clickButton(linkAccountOverview);
    }

    public WebElement getTableCell(String val) {
        Utils.waitUntilXPathToBeMoreThan("//*[@id='accountTable']/tbody/tr", 1);
        return this.driver.findElement(By.xpath("//*[@id='accountTable']/tbody/tr/td[1]/a[text()='" + val + "']"));
    }
}
