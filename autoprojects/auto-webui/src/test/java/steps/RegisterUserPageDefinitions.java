package steps;

import entities.Users;
import enums.UserTypes;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import pages.RegisterUserPage;
import pages.BaseTest;
import utils.CommonElements;

import java.util.List;

import static org.testng.Assert.assertEquals;

public class RegisterUserPageDefinitions extends BaseTest {
    private final Users createdUser = Users.getUser(UserTypes.CREATED_USER);
    CommonElements commonElements = pageObjectManager.getCommonElements();
    RegisterUserPage registerUserPage = pageObjectManager.getRegisterUserPage();

    @Given("User navigate to register page")
    public void userNavigateToRegisterPage() {
        commonElements.clickRegisterLink();
    }

    @And("Click register button")
    public void clickRegisterButton() {
        registerUserPage.clickRegisterButton();
    }

    @Then("User see list error messages")
    public void userSeeListErrorMessages() {
        List<String> lstErrors = registerUserPage.getListErrorMessages();
        assertEquals(lstErrors.get(0), "First name is required.",
                "Incorrect error message for First Name");
        assertEquals(lstErrors.get(1), "Last name is required.",
                "Incorrect error message for Last Name");
        assertEquals(lstErrors.get(2), "Address is required.",
                "Incorrect error message for Address");
        assertEquals(lstErrors.get(3), "City is required.",
                "Incorrect error message for City");
        assertEquals(lstErrors.get(4), "State is required.",
                "Incorrect error message for State");
        assertEquals(lstErrors.get(5), "Zip Code is required.",
                "Incorrect error message for Zip Code");
        assertEquals(lstErrors.get(6), "Social Security Number is required.",
                "Incorrect error message for SSN");
        assertEquals(lstErrors.get(7), "Username is required.",
                "Incorrect error message for Username");
        assertEquals(lstErrors.get(8), "Password is required.",
                "Incorrect error message for Password");
        assertEquals(lstErrors.get(9), "Password confirmation is required.",
                "Incorrect error message for Password confirmation");
    }

    @When("User enter data with inconsistent passwords")
    public void userEnterDataWithInconsistentPasswords() {
        registerUserPage.fillInRegisterForm(createdUser, true);
    }

    @Then("User see the inconsistent passwords message")
    public void userSeeInconsistentPasswordsMessage() {
        assertEquals(registerUserPage.getInconsistentPwdMessage(), "Passwords did not match.",
                "Incorrect error message for Password confirmation");
    }

    @When("User enter valid data")
    public void userEnterValidData() {
        registerUserPage.fillInRegisterForm(createdUser, false);
    }

    @Then("User can see welcome message with registered username")
    public void userSeeWelcomeMessage() {
        assertEquals(commonElements.getMessage(), "Welcome " + createdUser.getUsername(),
                "Unable to login with the created user");
    }

}
