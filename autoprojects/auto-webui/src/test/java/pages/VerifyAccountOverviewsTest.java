package pages;

import entities.Users;

import static org.testng.Assert.*;

import enums.UserTypes;
import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.CommonElements;

import static pages.InitPages.commonElements;
import static pages.InitPages.loginPage;
import static pages.InitPages.verifyAccountOverviews;
import static utils.Utils.*;

public class VerifyAccountOverviewsTest extends BaseTest {
    private final Users user = Users.getUser(UserTypes.VALID_LOGIN_USER);

    private String existingAccountNumber = "";
    private String newAccountId = "";

    @FindBy(className = "title")
    private WebElement lblTitle;

    @BeforeMethod
    public void init() {
        loginPage = new LoginPage(driver);
        commonElements = new CommonElements(driver);
        verifyAccountOverviews = new VerifyAccountOverviews(driver);
    }

    @Description("Verify that user is able to login with valid credentials")
    @Test(priority = 1)
    public void userIsAbleToLoginWithValidCredentials() {
        loginPage.loginWithValidCredentials(this.user);
        assertEquals(getElementContent(commonElements.lblTitle), "Accounts Overview",
                "Login failed");
    }

    @Description("Verify that user can click on \"Open New Account\", then the browser navigated to \"Open New Account\" page")
    @Test(priority = 2)
    public void userIsAbleToGoToOpenNewAccountPage() {
        this.existingAccountNumber = verifyAccountOverviews.clickOnOpenNewAccountLink();
        assertEquals(getElementContent(commonElements.lblTitle), "Open New Account",
                "Cannot go to Open New Account page");
    }

    @Description("Verify that user can open new account with default parameters")
    @Test(priority = 3)
    public void userIsAbleToOpenNewAccount() {
        this.newAccountId = verifyAccountOverviews.clickOnOpenNewAccountButton();
        assertEquals(getElementContent(commonElements.lblTitle), "Account Opened!",
                "Cannot go to Open New Account page");
    }

    @Description("Verify account id, Amount = $100.00, Balance = $100.00")
    @Test(priority = 4)
    public void verifyNewAccountInfo() {
        verifyAccountOverviews.checkOnLinkAccountOverview();
        WebElement specCell = verifyAccountOverviews.getTableCell(this.newAccountId);
        WebElement specRow = getParent(getParent(specCell));
        WebElement balanceCell = specRow.findElement(By.xpath("./td[2]"));
        WebElement availCell = specRow.findElement(By.xpath("./td[3]"));
        if (!specCell.getText().equals(this.newAccountId) ||
                !balanceCell.getText().equals("$100.00") ||
                !availCell.getText().equals("$100.00")) {
            Assert.fail("Cannot verify new account id: " + this.newAccountId);
        }
        Assert.assertTrue(true);
    }
}