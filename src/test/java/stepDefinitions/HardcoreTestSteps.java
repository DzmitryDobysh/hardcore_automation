package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import model.FormDefaultModel;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import page.GoogleCloudMainPage;
import page.GoogleCloudPricingCalculatorPage;
import page.TempEmailPage;
import decorators.TempEmailPageDecorator;

import java.util.List;
import java.util.Map;

public class HardcoreTestSteps {

    private WebDriver driver;
    private GoogleCloudMainPage cloudMainPage;
    private GoogleCloudPricingCalculatorPage calculatorPage;
    private TempEmailPage tempEmailPage;
    private TempEmailPageDecorator tempEmailPageDecorator;
    private String costOnCalcPage;
    private String costOnEmailPage;


    @Before
    public void setUp() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @After
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Given("^I open google cloud main page$")
    public void iOpenGoogleCloudMainPage() {
        driver.get("https://cloud.google.com");
        cloudMainPage = new GoogleCloudMainPage(driver);
    }

    @When("I perform a search for {string} page")
    public void iPerformSearchForCloudCalculatorPage(String searchTerm) {
        cloudMainPage.searchTerm(searchTerm);
    }

    @Then("Click on cloud calculator page link")
    public void clickOnCloudCalculatorPageLink() {
        cloudMainPage.clickCalculatorPage();
    }

    @When("I enter all info with following data:")
    public void iEnterAllInfoWithData(DataTable dataTable) {
        List<Map<String, String>> data = dataTable.asMaps(String.class, String.class);

        FormDefaultModel formModel = new FormDefaultModel();
        formModel.setInstances(data.get(0).get("Instances"));
        formModel.setOs(data.get(0).get("OS"));
        formModel.setInstanceClass(data.get(0).get("Instance Class"));
        formModel.setSeries(data.get(0).get("Series"));
        formModel.setMachineType(data.get(0).get("Machine Type"));
        formModel.setGpus(data.get(0).get("GPUs"));
        formModel.setGpuType(data.get(0).get("GPU Type"));
        formModel.setSsd(data.get(0).get("SSD"));
        formModel.setLocation(data.get(0).get("Location"));
        formModel.setUsage(data.get(0).get("Usage"));

        calculatorPage = new GoogleCloudPricingCalculatorPage(driver);

        calculatorPage.switchToCalculatorIFrame()
                .fillInEstimationForm(formModel)
                .clickAddToEstimateButton();
    }

    @Then("All entered info is present and added to estimation")
    public void allEnteredInfoIsPresentAndAddedToEstimation() {
    }


    @When("I created temp email and send estimation to it")
    public void iFillInTheEstimationForm() {
        tempEmailPage = new TempEmailPage(driver);
        tempEmailPageDecorator = new TempEmailPageDecorator(tempEmailPage);
        tempEmailPageDecorator.openInNewTab();

        String tempEmail = tempEmailPageDecorator.getTempEMail();

        calculatorPage.switchToPreviousTab();
        calculatorPage.switchToCalculatorIFrame()
                .clickEmailEstimateButton()
                .enterTempEMail(tempEmail)
                .sendEmail();

        costOnCalcPage = calculatorPage.getTotalCost();
    }

    @Then("I check the total cost in the email against the calculated total cost")
    public void iCheckTheTotalCostInTheEmailAgainstTheCalculatedTotalCost() {
        tempEmailPageDecorator.switchToNextTab();
        costOnEmailPage = tempEmailPageDecorator.getCostFromEmail();

        Assert.assertTrue(costOnCalcPage.contains(costOnEmailPage), "Total Cost from email doesn't match the one from the Calculator page");
    }
}

