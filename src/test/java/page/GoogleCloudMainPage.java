package page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleCloudMainPage extends AbstractPage {
    private static final Logger logger = LogManager.getLogger(GoogleCloudMainPage.class);
    String GC_BASE_URL = "https://cloud.google.com/";

    @FindBy(name = "q")
    WebElement searchInput;

    @FindBy(xpath = "//div[@class='devsite-cse-results']")
    WebElement resultsPage;

    @FindBy(xpath = "//a[@href='https://cloud.google.com/products/calculator' and b[text()='Google Cloud Pricing Calculator']]")
    WebElement resultPattern;

    public GoogleCloudMainPage(WebDriver driver) {
        super(driver);
    }

    public GoogleCloudMainPage openPage() {
        logger.info("Opening Google Cloud main page: {}", GC_BASE_URL);
        driver.get(GC_BASE_URL);
        return this;
    }

    public GoogleCloudMainPage searchTerm(String term) {
        logger.info("Performing search with term: {}", term);
        waitForWebElementVisible(searchInput).sendKeys(term + Keys.ENTER);
        return this;
    }

    public GoogleCloudMainPage clickCalculatorPage() {
        logger.info("Clicking on the Google Cloud Pricing Calculator page");
        waitForWebElementVisible(resultsPage);
        waitForWebElementVisible(resultPattern).click();
        return this;
    }
}
