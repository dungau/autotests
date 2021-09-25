package pages;

import entities.Users;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static utils.Utils.enterText;
import static utils.Utils.clickButton;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver){
        super(driver);
    }

    //Locating the username text box
    @FindBy(name = "username")
    private static WebElement txtUsername;

    //Locating the password text box
    @FindBy(name = "password")
    private static WebElement txtPassword;

    //Locating Login Button
    @FindBy(xpath = "*//input[@class='button']")
    private static WebElement btnLogin;

    @FindBy(className = "error")
    private static WebElement lblError;

    @Step("Login with valid credentials")
    public static void loginWithValidCredentials(Users user){
        enterText(txtUsername, user.getUsername());
        enterText(txtPassword, user.getPassword());
        clickButton(btnLogin);
    }

    @Step("Login with invalid credentials")
    public static String loginWithInvalidCredential(Users user){
        enterText(txtUsername, user.getUsername());
        enterText(txtPassword, user.getPassword());
        clickButton(btnLogin);
        return lblError.getText();
    }
}
