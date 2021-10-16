package sites;

import entities.Users;
import enums.UserTypes;
import io.qameta.allure.Description;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.util.Strings;
import pages.LoginPage;
import pages.RegisterUserPage;
import utils.CommonElements;

import static utils.Utils.clickButton;

import java.util.List;

import static org.testng.Assert.*;
import static pages.InitPages.*;
import static utils.Utils.getElementContent;

public class UpdateContactInfoPageTest extends BaseTest {

    private final Users user = Users.getUser(UserTypes.LOGIN_USER);

    @BeforeMethod
    public void init() {
        loginPage = new LoginPage(driver);
        commonElements = new CommonElements(driver);
        registerUserPage = new RegisterUserPage(driver);
    }

    @Description("Verify the logged user contact info")
    @Test(priority=1)
    public void verifyLoggedUserContactInfo() {
        loginPage.loginWithValidCredentials(user);
        clickButton(commonElements.linkUpdateContactInfo);
        List<String> results = updateContactInfoPage.verifyTheContactInfo(user);
        for (String res: results) {
            assertTrue(Strings.isNotNullAndNotEmpty(res), res);
        }
    }

    @Description("Update with blank value")
    @Test(priority=3)
    public void userCannotUpdateInfoWithBlankValue() {
        clickButton(commonElements.linkUpdateContactInfo);
        updateContactInfoPage.updateWithBlankValue();
        List<String> lstErrors = updateContactInfoPage.getListErrorMessages();
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
    }

    @Description("Update with valid value")
    @Test(priority=2)
    public void userCanUpdateInfoWithValidValue() {
        Users updatedUser = Users.getUpdatedUser(UserTypes.UPDATED_USER);
        updateContactInfoPage.updateWithValidData(updatedUser);
        assertEquals(getElementContent(commonElements.lblTitle), "Profile Updated", "Update failed");
    }

    @Description("Revert user contact info")
    @Test(priority=4)
    public void revertUserContactInfo() {
        clickButton(commonElements.linkUpdateContactInfo);
        updateContactInfoPage.updateWithValidData(user);
        assertEquals(getElementContent(commonElements.lblTitle), "Profile Updated", "Update failed");
    }
}