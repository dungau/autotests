package logs;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import pages.BaseTest;

public class BaseTestListener extends BaseTest implements ITestListener {

    private static final String LOG_OUTPUT_LINE = "\n============= Test '%s' %s =============\n";

    //Image attachments for Allure
    @Attachment(value = "Page screenshot", type = "image/png")
    private byte[] saveScreenshotPNG(WebDriver driver) {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    //Text attachments for Allure
    @Attachment(value = "{0}", type = "text/plain")
    private String saveTextLog(String message) {
        return message;
    }

    @Override
    public void onTestStart(ITestResult result) {
        BaseLog.info(String.format("\n" + LOG_OUTPUT_LINE, getTestName(result), "STARTED"));
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        BaseLog.info(String.format(LOG_OUTPUT_LINE + "\n", getTestName(result), "PASSED"));
    }

    @Override
    public void onTestFailure(ITestResult result) {
        BaseLog.info(String.format(LOG_OUTPUT_LINE + "\n", getTestName(result), "FAILED"));

        //Allure ScreenShotRobot and SaveTestLog
        if (driver != null) {
            System.out.println("Screenshot captured for test case:" + getTestName(result));
            saveScreenshotPNG(driver);
        }

        //Save a log on allure.
        saveTextLog(getTestName(result) + " failed and screenshot taken!");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        BaseLog.info(String.format(LOG_OUTPUT_LINE + "\n", getTestName(result), "SKIPPED"));
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }


    private String getTestName(ITestResult result) {
        return result.getInstanceName() + "." + result.getMethod().getMethodName();
    }

}