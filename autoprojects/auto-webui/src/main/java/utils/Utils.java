package utils;

import static enums.OsTypes.*;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class Utils {

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

    @Step("Get parentElement")
    public static WebElement getParent(WebElement elem) {
        return elem.findElement(By.xpath("./.."));
    }
}
