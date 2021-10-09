package pages;

import entities.Users;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;

import static utils.Utils.*;

public class UpdateContactInfoPage extends BasePage {

    public UpdateContactInfoPage(WebDriver driver){
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

    //Locating Update Button
    @FindBy(xpath = "//*[@name='contact']//input[@class='button']")
    private WebElement btnUpdate;

    //Locating the first name error message
    @FindBy(id = "//*[@name='customer.firstName']/parent::td/following-sibling::td//span")
    private WebElement lblFirstName;

    //Locating the last name error message
    @FindBy(id = "//*[@name='customer.lastName']/parent::td/following-sibling::td//span")
    private WebElement lblLastName;

    //Locating the address error message
    @FindBy(id = "//*[@name='customer.address.street']/parent::td/following-sibling::td//span")
    private WebElement lblAddress;

    //Locating the city error message
    @FindBy(id = "//*[@name='customer.address.city']/parent::td/following-sibling::td//span")
    private WebElement lblCity;

    //Locating the state error message
    @FindBy(id = "//*[@name='customer.address.state']/parent::td/following-sibling::td//span")
    private WebElement lblState;

    //Locating the zip code error message
    @FindBy(id = "//*[@name='customer.address.zipCode']/parent::td/following-sibling::td//span")
    private WebElement lblZipCode;

    @Step("Fill in and update user with valid data")
    public void updateWithValidData(Users user) {
        enterText(txtFirstName, user.getFirstName());
        enterText(txtLastName, user.getLastName());
        enterText(txtAddress, user.getAddress());
        enterText(txtCity, user.getCity());
        enterText(txtState, user.getState());
        enterText(txtZipCode, user.getZipCode());
        enterText(txtPhoneNumber, user.getPhoneNumber());
        clickButton(btnUpdate);
    }

    @Step("Update with blank value")
    public void updateWithBlankValue() {
        enterText(txtFirstName, "");
        enterText(txtLastName, "");
        enterText(txtAddress, "");
        enterText(txtCity, "");
        enterText(txtState, "");
        enterText(txtZipCode, "");
        enterText(txtPhoneNumber, "");
    }

    @Step("Get list errors message")
    public List<String> getListErrorMessages() {
        List<WebElement> elements = new ArrayList<>();
        elements.add(lblFirstName);
        elements.add(lblLastName);
        elements.add(lblAddress);
        elements.add(lblCity);
        elements.add(lblState);
        elements.add(lblZipCode);
        return getListElementsContent(elements);
    }

    @Step("Verify the contact info")
    public List<String> verifyTheContactInfo(Users users) {
        List<String> results = new ArrayList<>();
        results.add(verifyElementContent(txtFirstName, users.getFirstName()));
        results.add(verifyElementContent(txtLastName, users.getLastName()));
        results.add(verifyElementContent(txtAddress, users.getAddress()));
        results.add(verifyElementContent(txtCity, users.getCity()));
        results.add(verifyElementContent(txtState, users.getState()));
        results.add(verifyElementContent(txtZipCode, users.getZipCode()));
        results.add(verifyElementContent(txtPhoneNumber, users.getPhoneNumber()));
        return results;
    }
}
