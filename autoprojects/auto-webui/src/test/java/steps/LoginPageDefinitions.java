package steps;

import entities.Users;
import enums.UserTypes;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.LoginPage;
import pages.BaseTest;
import utils.CommonElements;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class LoginPageDefinitions extends BaseTest {

    private Users loginUser;
    LoginPage loginPage = pageObjectManager.getLoginPage();
    CommonElements commonElements = pageObjectManager.getCommonElements();

    @Given("^User in Login page")
    public void userInLoginPage() {
        assertTrue(loginPage.verifyLoginButtonIsDisplay(), "Cannot logout the system");
    }

    @When("^User enter valid credential")
    public void userEnterValidCredential() {
        loginUser = Users.getUser(UserTypes.LOGIN_USER);
        loginPage.enterUserCredential(loginUser);
    }

    @And("^Click Login button")
    public void clickLoginButton() {
        loginPage.clickLoginButton();
    }

    @Then("^User in Welcome page")
    public void userIsAbleToLoginSuccessfully() {
        assertEquals(commonElements.getMessage(), "Accounts Overview",
                "Login failed");
    }

    @When("^User enter blank credential")
    public void userEnterBlankCredential() {
        loginUser = Users.getBlankData();
        loginPage.enterUserCredential(loginUser);
    }

    @Then("^User get the validate error message")
    public void userGetValidateErrorMessage() {
        assertEquals(loginPage.getErrorMessage(), "Please enter a username and password.",
                "The error message does not match.");
    }

    @When("^User enter invalid credential")
    public void userEnterInvalidCredential() {
        loginUser = Users.getUser(UserTypes.INVALID_USER);
        loginPage.enterUserCredential(loginUser);
    }

    @Then("^User get the wrong credential message")
    public void userGetWrongCredentialMessage() {
        assertEquals(loginPage.getErrorMessage(), "The username and password could not be verified.",
                "The error message does not match.");
    }

    @And("^Click logout button")
    public void userClickLogoutButton() {
        commonElements.clickLogoutButton();
    }

}
