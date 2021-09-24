package utils;

import static enums.OsTypes.*;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Utils {

    protected static WebDriver driver;
    private static WebDriverWait wait;

    public static void navigateToURL(String strAddress)
    {
        System.out.println("Navigate to the " + strAddress);
        try{
            driver.get(strAddress); //navigate to URL
            driver.manage().window().maximize(); //maximize the window
        }catch(Exception ex){System.out.println("Can't navigate to an address URL: " + strAddress + ". " + ex.getMessage());}
    }

    //wait for an element to be found
    public static WebElement waitElement(By strLocation) throws Exception
    {
        System.out.println("Waiting for the element with locator " + strLocation + " is present.");
        try{
            WebElement e = wait.until(ExpectedConditions.visibilityOfElementLocated(strLocation));
            return e;
        }
        catch(Exception ex){
            System.out.println("Not able to find the element with locator " + strLocation + ". " + ex.getMessage());
            return null;
        }
    }

    @Step("click on an element")
    public static void clickButton(WebElement element)
    {
        element.click();
    }

    @Step("enter text to an element")
    public static void enterText(WebElement element, String strData)
    {
        clearDataBasedOnOS(element);
        element.sendKeys(strData);
    }

    @Step("clear data on element base on OS")
    private static void clearDataBasedOnOS (WebElement element) {
        switch (getOs()) {
            case WIN -> {
                element.sendKeys(Keys.chord(Keys.CONTROL, "a"));
                element.sendKeys(Keys.BACK_SPACE);
            }
            case MAC -> {
                element.sendKeys(Keys.chord(Keys.COMMAND, "a"));
                element.sendKeys(Keys.DELETE);
            }
        }
    }
}
