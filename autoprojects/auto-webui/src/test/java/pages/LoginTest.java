package pages;

import entities.Users;
import static org.testng.Assert.*;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static pages.SwagLabsPage.loginPage;


public class LoginTest extends BaseTest {

    private Users user;

    @BeforeTest
    public void beforeTest() {
        loginPage = new LoginPage(driver);
        user = new Users();
    }

    @Test
    public void testLoginWithValidCredentials() {
        loginPage.loginWithValidCredentials(user.getDefaultUser());
    }

    @Test
    public void testLoginWithInvalidCredentials() {
        String errorMessage = loginPage.loginWithInvalidCredential(user);
        assertEquals(errorMessage, "Please enter a username and password.",
                "Can not find the error message.");
    }

}
