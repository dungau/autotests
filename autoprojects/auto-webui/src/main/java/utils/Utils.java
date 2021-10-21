package utils;

import static enums.OsTypes.*;
import static utils.PropertyLoader.getTimeOutInSeconds;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    @Step("click on an element")
    public static void clickButton(WebElement element)
    {
        element.click();
    }
    private static WebDriverWait wait = null;

    public static void setupWait(WebDriverWait wait) {
        Utils.wait = wait;
    }

    public static void setupWait(WebDriver driver) {
        Utils.wait = new WebDriverWait(driver, Duration.ofSeconds(getTimeOutInSeconds()));
    }

    public static WebDriverWait getWait(WebDriverWait wait) {
        return Utils.wait;
    }

    public static void /* just wait, and do nothing! */ waitUntilXPathToBeMoreThan(String xPath, int numOfElem) {
        Utils.wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(By.xpath(xPath), numOfElem));
    }

    public static void /* just wait, and do nothing! */ waitUntilElementClickable(WebElement elem) {
        Utils.wait.until(ExpectedConditions.elementToBeClickable(elem));
    }

    public static void /* just wait, and do nothing! */ waitUntilElementClickable(String xPath) {
        Utils.wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xPath)));
    }

    public static void /* just wait, and do nothing! */ waitUntilElementVisible(WebElement elem) {
        Utils.wait.until(ExpectedConditions.visibilityOf(elem));
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

    @Step("Verify the element is displayed")
    public static boolean isElementDisplayed(WebElement element) {
        return element.isDisplayed();
    }

    @Step("Verify the element content")
    public static String verifyElementContent(WebElement element, String strData) {
        String errorMessage = "";
        boolean elementDisplayed = isElementDisplayed(element);
        if(elementDisplayed){
            String actualContent = element.getText();
                if (!element.getText().equals(strData)) {
                    errorMessage = "The field value is incorrect, expected is " + strData +
                            " , actual is " + actualContent + ".";
                }
        }else{
            errorMessage = "The field does not exist.";
        }
        return errorMessage;
    }

    @Step("Get element content")
    public static String getElementContent(WebElement element) {
        return element.getText();
    }

    @Step("Get list elements content")
    public static List<String> getListElementsContent(List<WebElement> elements) {
        List<String> errorMessages = new ArrayList<>();
        for (WebElement e: elements) {
            errorMessages.add(getElementContent(e));
        }
        return errorMessages;
    }

    public static void waitPageLoad() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
    }

    @Step("Get parentElement")
    public static WebElement getParent(WebElement elem) {
        return elem.findElement(By.xpath("./.."));
    }
}
