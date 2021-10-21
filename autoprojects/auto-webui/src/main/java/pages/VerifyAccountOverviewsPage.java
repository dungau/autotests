package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import utils.Utils;

import static utils.Utils.*;

public class VerifyAccountOverviewsPage extends BasePage {

    public VerifyAccountOverviewsPage(WebDriver driver){
        super(driver);
    }

    @FindBy(xpath = "//*[@id='leftPanel']/ul/li[1]/a")
    private WebElement lnkOpenNewAccount;

    private static final String btnOpenNewAccountXPath = "//*[@id='rightPanel']/div/div/form/div/input[@type='submit']";
    @FindBy(xpath = VerifyAccountOverviewsPage.btnOpenNewAccountXPath)
    private WebElement btnOpenNewAccount;

    @FindBy(xpath = "//*[@id='fromAccountId']")
    private WebElement cbxExistingAccount;

    @FindBy(xpath = "//*[@id='fromAccountId']/option[1]")
    private WebElement cbxOptFirstExistingAccount;

    @FindBy(xpath = "//*[@id='newAccountId']")
    private WebElement lnkAccountNumber;

    @FindBy(xpath = "//*[@id='leftPanel']/ul/li[2]/a")
    private WebElement lnkAccountOverview;

    @Step("User (logged in) click on 'Open New Account' link")
    public String clickOnOpenNewAccountLink() {
        clickButton(lnkOpenNewAccount);
        Utils.waitUntilXPathToBeMoreThan("//*[@id='fromAccountId']/option", 0);
        String selectedValue = (new Select(cbxExistingAccount)).getFirstSelectedOption().getText();
        return selectedValue;
    }

    @Step("User (logged in) click on 'Open New Account' button on 'Open New Account' page to open new account")
    public String clickOnOpenNewAccountButton() {
        // ajax/SPA
        Utils.waitUntilElementClickable(VerifyAccountOverviewsPage.btnOpenNewAccountXPath);
        clickButton(btnOpenNewAccount);
        Utils.waitUntilElementVisible(lnkAccountNumber);
        String newId = lnkAccountNumber.getText();
        return newId;
    }

    @Step("User (logged in) check the available balance on 'Accounts Overview' link")
    public void checkOnLinkAccountOverview() {
        clickButton(lnkAccountOverview);
    }

    public WebElement getTableCell(String val) {
        Utils.waitUntilXPathToBeMoreThan("//*[@id='accountTable']/tbody/tr", 1);
        return this.driver.findElement(By.xpath("//*[@id='accountTable']/tbody/tr/td[1]/a[text()='" + val + "']"));
    }
}
