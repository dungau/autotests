package steps;

import entities.Users;
import enums.UserTypes;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pages.BaseTest;
import pages.LoginPage;
import pages.VerifyAccountOverviewsPage;

import static utils.Utils.getParent;

public class VerifyAccountOverviewsPageDefinitions extends BaseTest {
    LoginPage loginPage = pageObjectManager.getLoginPage();
    private final Users user = Users.getUser(UserTypes.VALID_LOGIN_USER);
    VerifyAccountOverviewsPage verifyAccountOverviews = pageObjectManager.getVerifyAccountOverviews();
    String newAccountId = "";

    private String existingAccountNumber = "";

    @When("^User enter valid credential test09")
    public void userEnterValidCredential() {
        loginPage.enterUserCredential(user);
    }

    @And("Click on Open new Account link")
    public void userIsAbleToGoToOpenNewAccountPage(){
        this.existingAccountNumber = verifyAccountOverviews.clickOnOpenNewAccountLink();
    }

    @And("Click on Open new Account button")
    public void userIsAbleToOpenNewAccount() {
        this.newAccountId = verifyAccountOverviews.clickOnOpenNewAccountButton();
    }

    @And("Click on Accounts Overview link")
    public void verifyNewAccountInfo() {
        verifyAccountOverviews.checkOnLinkAccountOverview();
    }

    @Then("Verify the account information is corrected")
    public void userSeeListErrorMessages() {
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