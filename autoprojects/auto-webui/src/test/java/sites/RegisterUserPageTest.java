package pages;

import entities.Users;
import enums.UserTypes;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utils.CommonElements;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static pages.InitPages.commonElements;
import static pages.InitPages.registerUserPage;
import static utils.Utils.clickButton;
import static utils.Utils.getElementContent;

public class RegisterUserPageTest extends pages.BaseTest {

    private final Users createdUser = Users.getUser(UserTypes.CREATED_USER);

    @BeforeMethod
    public void init() {
        commonElements = new CommonElements(driver);
        registerUserPage = new RegisterUserPage(driver);
    }

    @Description("Verify the validation error messages when registering user with blank value")
    @Test(priority = 1)
    public void userIsNotAbleToRegisterUserWithBlankValue() {
        clickButton(commonElements.linkRegister);
        registerUserPage.registerWithBlankValue();
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

    @Description("Verify the password confirmation error messages when registering user with passwords do not match")
    @Test(priority = 2)
    public void userIsNotAbleToRegisterUserWithPasswordsDoNotMatch() {
        clickButton(commonElements.linkRegister);
        String errors = registerUserPage.registerWithPasswordsDoNotMatch(createdUser);
        assertEquals(errors, "Passwords did not match.",
                "Incorrect error message for Password confirmation");
    }

    @Description("Verify user can register the account and log in to the system")
    @Test(priority = 3)
    public void userCanRegisterAccountAndLoginToSystem() {
        clickButton(commonElements.linkRegister);
        registerUserPage.registerWithValidData(createdUser);
        assertEquals(getElementContent(commonElements.lblTitle), "Welcome " + createdUser.getUsername(),
                "Unable to login with the created user");
    }

}