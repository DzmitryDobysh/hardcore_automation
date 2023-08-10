package testrunners;

import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = "src/test/java/features", glue = "stepDefinitions", plugin = "pretty")
public class cucumberTestRunner extends AbstractTestNGCucumberTests {
}
