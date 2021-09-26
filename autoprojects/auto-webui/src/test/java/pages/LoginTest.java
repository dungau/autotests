package pages;

import entities.Users;
import static org.testng.Assert.*;

import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static pages.SwagLabsPage.loginPage;

public class LoginTest extends BaseTest {

    private Users user;

    @BeforeMethod
    public void init() {
        loginPage = new LoginPage(driver);
        user = new Users();
    }

    @Description("Verify that user is able to login with valid credentials")
    @Test
    public void testLoginWithValidCredentials() {
        loginPage.loginWithValidCredentials(user.getDefaultUser());
    }

    @Description("Verify that an error message shows when logged with blank credentials")
    @Test
    public void testLoginWithBlankCredentials() {
        String errorMessage = loginPage.loginWithInvalidCredential(user);
        assertEquals(errorMessage, "Please enter a username and password.",
                "The error message does not match.");
    }

    @Description("Verify that an error message shows when logged with invalid credentials")
    @Test
    public void testLoginWithInvalidCredential() {
        user.setUsername("test");
        user.setPassword("test");
        String errorMessage = loginPage.loginWithInvalidCredential(user);
        assertEquals(errorMessage, "The username and password could not be verified.",
                "The error message does not match.");
    }

}
