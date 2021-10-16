package pages;

import entities.Users;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

import static utils.Utils.*;

public class RegisterUserPage extends BasePage {

    public RegisterUserPage(WebDriver driver){
        super(driver);
    }

    //Locating the first name text box
    @FindBy(name = "customer.firstName")
    private WebElement txtFirstName;

    //Locating the last name text box
    @FindBy(name = "customer.lastName")
    private WebElement txtLastName;

    //Locating the address text box
    @FindBy(name = "customer.address.street")
    private WebElement txtAddress;

    //Locating the city text box
    @FindBy(name = "customer.address.city")
    private WebElement txtCity;

    //Locating the state text box
    @FindBy(name = "customer.address.state")
    private WebElement txtState;

    //Locating the zip code text box
    @FindBy(name = "customer.address.zipCode")
    private WebElement txtZipCode;

    //Locating the phone text box
    @FindBy(name = "customer.phoneNumber")
    private WebElement txtPhoneNumber;

    //Locating the ssn text box
    @FindBy(name = "customer.ssn")
    private WebElement txtSsn;

    //Locating the username text box
    @FindBy(name = "customer.username")
    private WebElement txtUsername;

    //Locating the password text box
    @FindBy(name = "customer.password")
    private WebElement txtPassword;

    //Locating the confirm password text box
    @FindBy(name = "repeatedPassword")
    private WebElement txtConfirmPassword;

    //Locating Register Button
    @FindBy(xpath = "//*[@id='customerForm']//input[@class='button']")
    private WebElement btnRegister;

    //Locating the first name error message
    @FindBy(id = "customer.firstName.errors")
    private WebElement lblFirstName;

    //Locating the last name error message
    @FindBy(id = "customer.lastName.errors")
    private WebElement lblLastName;

    //Locating the address error message
    @FindBy(id = "customer.address.street.errors")
    private WebElement lblAddress;

    //Locating the city error message
    @FindBy(id = "customer.address.city.errors")
    private WebElement lblCity;

    //Locating the state error message
    @FindBy(id = "customer.address.state.errors")
    private WebElement lblState;

    //Locating the zip code error message
    @FindBy(id = "customer.address.zipCode.errors")
    private WebElement lblZipCode;

    //Locating the ssn error message
    @FindBy(id = "customer.ssn.errors")
    private WebElement lblSsn;

    //Locating the username error message
    @FindBy(id = "customer.username.errors")
    private WebElement lblUsername;

    //Locating the password error message
    @FindBy(id = "customer.password.errors")
    private WebElement lblPassword;

    //Locating the confirm password error message
    @FindBy(id = "repeatedPassword.errors")
    private WebElement lblConfirmPassword;

    //Locating the title
    @FindBy(className = "title")
    private static WebElement lblTitle;

    @Step("Click register button")
    public void clickRegisterButton() {
        clickButton(btnRegister);
    }

    @Step("Fill in and register user with valid data")
    public void registerWithValidData(Users user){
        enterText(txtFirstName, user.getFirstName());
        enterText(txtLastName, user.getLastName());
        enterText(txtAddress, user.getAddress());
        enterText(txtCity, user.getCity());
        enterText(txtState, user.getState());
        enterText(txtZipCode, user.getZipCode());
        enterText(txtPhoneNumber, user.getPhoneNumber());
        enterText(txtSsn, user.getSsn());
        enterText(txtUsername, user.getUsername());
        enterText(txtPassword, user.getPassword());
        enterText(txtConfirmPassword, user.getPassword());
    }

    public List<String> getListErrorMessages() {
        List<WebElement> elements = new ArrayList<>();
        elements.add(lblFirstName);
        elements.add(lblLastName);
        elements.add(lblAddress);
        elements.add(lblCity);
        elements.add(lblState);
        elements.add(lblZipCode);
        elements.add(lblSsn);
        elements.add(lblUsername);
        elements.add(lblPassword);
        elements.add(lblConfirmPassword);
        return getListElementsContent(elements);
    }

    @Step("Fill in register form")
    public void fillInRegisterForm(Users user, boolean inconsistentPwd) {
        enterText(txtFirstName, user.getFirstName());
        enterText(txtLastName, user.getLastName());
        enterText(txtAddress, user.getAddress());
        enterText(txtCity, user.getCity());
        enterText(txtState, user.getState());
        enterText(txtZipCode, user.getZipCode());
        enterText(txtPhoneNumber, user.getPhoneNumber());
        enterText(txtSsn, user.getSsn());
        enterText(txtUsername, user.getUsername());
        enterText(txtPassword, user.getPassword());
        if(inconsistentPwd) {
            enterText(txtConfirmPassword, user.getUsername()+user.getPassword());
        } else { enterText(txtConfirmPassword, user.getPassword()); }
    }

    @Step("Get inconsistent passwords messages")
    public String getInconsistentPwdMessage() {
        return lblConfirmPassword.getText();
    }

    @Step("Register with password and password confirmation do not match")
    public String registerWithPasswordsDoNotMatch(Users user){
        enterText(txtFirstName, user.getFirstName());
        enterText(txtLastName, user.getLastName());
        enterText(txtAddress, user.getAddress());
        enterText(txtCity, user.getCity());
        enterText(txtState, user.getState());
        enterText(txtZipCode, user.getZipCode());
        enterText(txtPhoneNumber, user.getPhoneNumber());
        enterText(txtSsn, user.getSsn());
        enterText(txtUsername, user.getUsername());
        enterText(txtPassword, user.getPassword());
        enterText(txtConfirmPassword, user.getUsername()+user.getPassword());
        clickButton(btnRegister);
        return lblConfirmPassword.getText();
    }

    public void registerWithBlankValue() {
    }
}
