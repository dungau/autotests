import entities.Users;
import static org.testng.Assert.*;

import org.testng.annotations.Test;
import sites.pages.LoginPage;


public class LoginTest extends BaseTest {

    @Test
    public void testLoginWithValidCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        Users user = new Users().getDefaultUser();
        loginPage.loginWithValidCredentials(user);
    }

    @Test
    public void testLoginWithInvalidCredentials() {
        LoginPage loginPage = new LoginPage(driver);
        Users user = new Users();
        String errorMessage = loginPage.loginWithInvalidCredential(user);
        assertEquals(errorMessage, "Please enter a username and password.",
                "Can not find the error message.");
    }

}
