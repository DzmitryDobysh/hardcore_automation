package page;

import model.FormDefaultModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GoogleCloudPricingCalculatorPage extends AbstractPage {

    private static final Logger logger = LogManager.getLogger(GoogleCloudPricingCalculatorPage.class);

    @FindBy(xpath = "//iframe[contains(@src, '/products/calculator')]")
    WebElement frameMain;

    @FindBy(xpath = "//*[@id=\"tab-item-1\"]")
    WebElement buttonComputeEngine;

    @FindBy(xpath = "//input[@ng-model='listingCtrl.computeServer.quantity']")
    WebElement inputNumberOfInstances;

    @FindBy(xpath = "//input[@ng-model='listingCtrl.computeServer.label']")
    WebElement inputPurpose;

    @FindBy(xpath = "//md-select[@ng-model='listingCtrl.computeServer.os']")
    WebElement dropdownSoftware;
    @FindBy(xpath = "//md-option[@value='free']")
    WebElement itemSoftware;

    @FindBy(xpath = "//md-select[@placeholder='VM Class']")
    WebElement dropdownClass;
    @FindBy(xpath = "//md-select-menu[@style]/descendant::md-option[@value='regular']")
    WebElement itemClass;

    @FindBy(xpath = "//md-select[@placeholder='Series']")
    WebElement dropdownSeries;
    @FindBy(xpath = "//md-option[@value='n1']")
    WebElement itemSeries;

    @FindBy(xpath = "//md-select[@placeholder='Instance type']")
    WebElement dropdownMachineType;
    @FindBy(xpath = "//md-option[@value='CP-COMPUTEENGINE-VMIMAGE-N1-STANDARD-8']")
    WebElement itemMachineType;

    @FindBy(xpath = "//md-checkbox[@aria-label='Add GPUs' and @ng-model='listingCtrl.computeServer.addGPUs']")
    WebElement checkboxAddGPUs;
    @FindBy(xpath = "//md-select[@placeholder='Number of GPUs']")
    WebElement dropboxNumberOfGPUs;
    @FindBy(xpath = "//md-option[@value='1' and contains(@ng-repeat, 'gpuType')]")
    WebElement itemNumberOfGPUs;
    @FindBy(xpath = "//md-select[@placeholder='GPU type']")
    WebElement dropboxGPUType;
    @FindBy(xpath = "//md-option[@value='NVIDIA_TESLA_V100']")
    WebElement itemGPUType;

    @FindBy(xpath = "//md-select[@placeholder='Local SSD']")
    WebElement dropdownSSD;
    @FindBy(xpath = "//md-option[@value='2' and contains(@ng-repeat, 'item in listingCtrl.dynamicSsd.computeServer')]")
    WebElement itemSSD;

    @FindBy(xpath = "//md-select[@placeholder='Datacenter location'][@ng-model='listingCtrl.computeServer.location']")
    WebElement dropdownLocation;
    @FindBy(xpath = "//div[@class='md-select-menu-container cpc-region-select md-active md-clickable']//md-option[@value='europe-west3' and div[@class='md-text ng-binding'][normalize-space()='Frankfurt (europe-west3)']]")
    WebElement itemLocation;

    @FindBy(xpath = "//md-select[@placeholder='Committed usage']")
    WebElement dropdownUsage;
    @FindBy(xpath = "//div[@class='md-select-menu-container md-active md-clickable']/descendant::md-option[@ng-value='1'][@value='1']")
    WebElement itemUsage;

    @FindBy(xpath = "//button[@ng-click='listingCtrl.addComputeServer(ComputeEngineForm);']\n")
    WebElement buttonAddToEstimate;

    @FindBy(xpath = "//h2/b[@class='ng-binding']")
    WebElement textTotalCost;

    @FindBy(xpath = "//*[@id=\"Email Estimate\"]")
    WebElement buttonEmailEstimate;

    @FindBy(xpath = "//input[@ng-model='emailQuote.user.email']")
    WebElement inputEmail;

    @FindBy(xpath = "//button[@ng-click=\"emailQuote.emailQuote(true); emailQuote.$mdDialog.hide()\"]")
    WebElement buttonSendEmail;

    public GoogleCloudPricingCalculatorPage(WebDriver driver) {
        super(driver);
    }

    public GoogleCloudPricingCalculatorPage switchToCalculatorIFrame() {
        logger.debug("Switching to Calculator iFrame");
        driver.switchTo().frame(frameMain);
        driver.switchTo().frame("myFrame");
        return this;
    }

    public GoogleCloudPricingCalculatorPage fillInEstimationForm(FormDefaultModel formModel) {
        logger.debug("Filling in the estimation form");
        String instances = formModel.getInstances();

        waitForWebElementVisible(buttonComputeEngine).click();
        inputNumberOfInstances.sendKeys(instances);
        inputPurpose.clear();
        dropdownSoftware.click();
        waitForWebElementVisible(itemSoftware).click();
        dropdownClass.click();
        waitForWebElementVisible(itemClass).click();
        dropdownSeries.click();
        waitForWebElementVisible(itemSeries).click();
        dropdownMachineType.click();
        waitForWebElementVisible(itemMachineType).click();
        checkboxAddGPUs.click();
        dropboxGPUType.click();
        waitForWebElementVisible(itemGPUType).click();
        waitForWebElementVisible(dropboxNumberOfGPUs).click();
        waitForWebElementVisible(itemNumberOfGPUs).click();
        dropdownSSD.click();
        waitForWebElementVisible(itemSSD).click();
        dropdownLocation.click();
        waitForWebElementVisible(itemLocation).click();
        dropdownUsage.click();
        waitForWebElementVisible(itemUsage).click();
        return this;
    }

    public GoogleCloudPricingCalculatorPage clickAddToEstimateButton() {
        logger.debug("Clicking on 'Add to Estimate' button");
        buttonAddToEstimate.click();
        return this;
    }

    public String getTotalCost() {
        logger.debug("Getting the total cost");
        return waitForWebElementVisible(textTotalCost).getText();
    }

    public GoogleCloudPricingCalculatorPage clickEmailEstimateButton() {
        logger.debug("Clicking on 'Email Estimate' button");
        buttonEmailEstimate.click();
        return this;
    }

    public GoogleCloudPricingCalculatorPage enterTempEMail(String tempEmail) {
        logger.debug("Entering temporary email: {}", tempEmail);
        waitForWebElementVisible(inputEmail).sendKeys(tempEmail);
        return this;
    }

    public void sendEmail() {
        logger.debug("Sending email");
        buttonSendEmail.click();
    }

}
