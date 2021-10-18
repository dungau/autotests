package pages;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions( features = {"src/test/java/pages"}
        , glue = { "steps" })
public class BaseRunner extends AbstractTestNGCucumberTests {

}