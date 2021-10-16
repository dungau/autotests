package steps;

import entities.Users;
import enums.UserTypes;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.util.Strings;
import pages.BaseTest;
import pages.UpdateContactInfoPage;
import utils.CommonElements;

import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static pages.InitPages.commonElements;
import static pages.InitPages.updateContactInfoPage;
import static utils.Utils.getElementContent;

public class UpdateContactInfoPageDefinitions extends BaseTest {
    private final Users user = Users.getUser(UserTypes.LOGIN_USER);
    CommonElements commonElements = pageObjectManager.getCommonElements();
    UpdateContactInfoPage updateContactInfoPage = pageObjectManager.getUpdateContactInfoPage();

    @And("Click update contact info link")
    public void clickUpdateContactInfoLink() {
        commonElements.clickUpdateContactInfoLink();
    }

    @Then("User see all information are matched")
    public void userInformationAreMatched() {
        List<String> results = updateContactInfoPage.verifyTheContactInfo(user);
        for (String res: results) {
            assertTrue(Strings.isNotNullAndNotEmpty(res), res);
        }
    }

    @And("Update contact info with blank data")
    public void updateContactInfoWithBlankData(){
        updateContactInfoPage.updateWithBlankValue();
    }

    @Then("User see list validation error messages")
    public void userSeeListErrorMessages() {
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

    @And("Update contact info with valid data")
    public void updateContactInfoWithValidData() {
        Users updatedUser = Users.getUpdatedUser(UserTypes.UPDATED_USER);
        updateContactInfoPage.updateWithValidData(updatedUser);
    }

    @And("Revert contact info with origin data")
    public void revertContactInfoWithOriginData() {
        updateContactInfoPage.updateWithValidData(user);
    }

    @Then("User see the update successfully message")
    public void userSeeUpdateSuccessfullyMessage() {
        assertEquals(commonElements.getMessage(), "Profile Updated", "Update failed");
    }

}
