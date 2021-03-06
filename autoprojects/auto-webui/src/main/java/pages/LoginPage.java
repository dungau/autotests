package pages;

import entities.Users;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import static utils.Utils.enterText;
import static utils.Utils.clickButton;
import static utils.Utils.isElementDisplayed;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver){
        super(driver);
    }

    //Locating the username text box
    @FindBy(name = "username")
    private WebElement txtUsername;

    //Locating the password text box
    @FindBy(name = "password")
    private WebElement txtPassword;

    //Locating Login Button
    @FindBy(xpath = "*//input[@class='button']")
    private WebElement btnLogin;

    //Locating error label
    @FindBy(className = "error")
    private WebElement lblError;

    @Step("Login with valid credentials")
    public void loginWithValidCredentials(Users user) {
        enterText(txtUsername, user.getUsername());
        enterText(txtPassword, user.getPassword());
        clickButton(btnLogin);
    }

    @Step("Login with invalid credentials")
    public String loginWithInvalidCredential(Users user) {
        enterText(txtUsername, user.getUsername());
        enterText(txtPassword, user.getPassword());
        clickButton(btnLogin);
        return lblError.getText();
    }

    @Step("Verify Login button is displayed")
    public boolean verifyLoginButtonIsDisplay() {
        return isElementDisplayed(btnLogin);
    }
}
