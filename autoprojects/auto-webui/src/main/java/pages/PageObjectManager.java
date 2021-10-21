package pages;

import org.openqa.selenium.WebDriver;
import utils.CommonElements;

public class PageObjectManager {
    private final WebDriver driver;

    public PageObjectManager(WebDriver driver) {
        this.driver = driver;
    }

    public LoginPage getLoginPage(){
        return new LoginPage(driver);
    }

    public RegisterUserPage getRegisterUserPage() {
        return new RegisterUserPage(driver);
    }

    public CommonElements getCommonElements() {
        return new CommonElements(driver);
    }

    public UpdateContactInfoPage getUpdateContactInfoPage() {
        return new UpdateContactInfoPage(driver);
    }

    public VerifyAccountOverviewsPage getVerifyAccountOverviews() {
        return new VerifyAccountOverviewsPage(driver);
    }

}
