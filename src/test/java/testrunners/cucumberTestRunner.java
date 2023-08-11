package testrunners;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "src/test/java/features",
        glue = "stepDefinitions",
        plugin = {"pretty",
                "html:target/SystemTestReports/html/report.html",
                "json:target/SystemTestReports/json/report.json"})
public class cucumberTestRunner extends AbstractTestNGCucumberTests {
}
