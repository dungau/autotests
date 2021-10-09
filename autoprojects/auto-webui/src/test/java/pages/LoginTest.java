package pages;

import entities.Users;
import static org.testng.Assert.*;

import enums.UserTypes;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.CommonElements;

import static pages.InitPages.commonElements;
import static utils.Utils.clickButton;
import static utils.Utils.getElementContent;
import static pages.InitPages.loginPage;

public class LoginTest extends BaseTest {

    @BeforeMethod
    public void init() {
        loginPage = new LoginPage(driver);
        commonElements = new CommonElements(driver);
    }

    @Description("Verify that user is able to login with valid credentials")
    @Test(priority = 4)
    public void userIsAbleToLoginWithValidCredentials() {
        Users loginUser = Users.getUser(UserTypes.LOGIN_USER);
        loginPage.loginWithValidCredentials(loginUser);
        assertEquals(getElementContent(commonElements.lblTitle), "Accounts Overview",
                "Login failed");
    }

    @Description("Verify that an error message shows when logged with blank credentials")
    @Test(priority = 1)
    public void userIsNotAbleToLoginWithBlankCredentials() {
        String errorMessage = loginPage.loginWithInvalidCredential(Users.getBlankCredential());
        assertEquals(errorMessage, "Please enter a username and password.",
                "The error message does not match.");
    }

    @Description("Verify that an error message shows when logged with invalid credentials")
    @Test(priority = 2)
    public void userIsNotAbleToLoginWithInvalidCredential() {
        String errorMessage = loginPage.loginWithInvalidCredential(Users.getUser(UserTypes.INVALID_USER));
        assertEquals(errorMessage, "The username and password could not be verified.",
                "The error message does not match.");
    }

    @Description("Verify user can logout")
    @Test(priority = 3)
    public void userCanLogout() {
        loginPage.loginWithValidCredentials(Users.getUser(UserTypes.LOGIN_USER));
        clickButton(commonElements.linkLogout);
        assertTrue(loginPage.verifyLoginButtonIsDisplay(), "Cannot logout the system");
    }
}
