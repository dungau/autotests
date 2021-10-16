package pages;

import core.Hooks;
import org.openqa.selenium.WebDriver;

public class BaseTest {
    protected PageObjectManager pageObjectManager;
    protected WebDriver driver;

    public BaseTest() {
        driver = Hooks.getDriver();
        pageObjectManager = Hooks.getPageObjectManager();
    }
}