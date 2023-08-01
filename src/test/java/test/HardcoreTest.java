package test;

import decorators.TempEmailPageDecorator;
import model.FormDefaultModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.GoogleCloudMainPage;
import page.GoogleCloudPricingCalculatorPage;
import page.TempEmailPage;
import service.FormDefaultModelCreator;

public class HardcoreTest extends CommonConditions {

    private static final Logger logger = LogManager.getLogger(HardcoreTest.class);

    String SEARCH_TERM_MAIN_PAGE = "Google Cloud Platform Pricing Calculator";
    GoogleCloudMainPage cloudMainPage;
    GoogleCloudPricingCalculatorPage cloudCalculatorPage;
    TempEmailPage tempEmailPage;
    PageFactory pageFactory;

    @Test(description = "Fill in estimation form")
    public void fillInEstimationForm() {
        logger.info("Starting fillInEstimationForm test");

        FormDefaultModel formModel = FormDefaultModelCreator.createDefaultFormModel();

        pageFactory = new PageFactory();

        cloudMainPage = pageFactory.createGoogleCloudMainPage(driver)
                .openPage();
        logger.debug("Opened Google Cloud main page");

        cloudMainPage.searchTerm(SEARCH_TERM_MAIN_PAGE);
        logger.debug("Performed search with term: {}", SEARCH_TERM_MAIN_PAGE);

        cloudMainPage.clickCalculatorPage();
        logger.debug("Clicked on the calculator page");

        cloudCalculatorPage = pageFactory.createGoogleCloudPricingCalculatorPage(driver)
                .switchToCalculatorIFrame()
                .fillInEstimationForm(formModel)
                .clickAddToEstimateButton();
        logger.debug("Filled in the estimation form and clicked on 'Add to Estimate' button");

        logger.info("Finished fillInEstimationForm test");
    }

    @Test(dependsOnMethods = {"fillInEstimationForm"}, description = "Check Email total cost against calculated total cost")
    public void checkTotalCost() {
        logger.info("Starting checkTotalCost test");

        tempEmailPage = pageFactory.createTempEmailPage(driver);
        TempEmailPageDecorator tempEmailPageDecorator = new TempEmailPageDecorator(tempEmailPage);
        tempEmailPageDecorator.openInNewTab();
        logger.debug("Opened TempEmail page in a new tab");

        String tempEmail = tempEmailPageDecorator.getTempEMail();
        logger.debug("Got temporary email address: {}", tempEmail);

        cloudCalculatorPage.switchToPreviousTab();
        cloudCalculatorPage.switchToCalculatorIFrame()
                .clickEmailEstimateButton()
                .enterTempEMail(tempEmail)
                .sendEmail();
        String costOnCalcPage = cloudCalculatorPage.getTotalCost();

        tempEmailPageDecorator.switchToNextTab();
        String costOnEmailPage = tempEmailPageDecorator.getCostFromEmail();

        Assert.assertTrue(costOnCalcPage.contains(costOnEmailPage), "Total Cost from email doesn't match the one from the Calculator page");
        logger.debug("Total cost comparison: Calc Page: {}, Email Page: {}", costOnCalcPage, costOnEmailPage);

        String dateAndTimeFromEmail = tempEmailPageDecorator.getDateAndTimeFromEmail();
        logger.debug("Date and time from email: {}", dateAndTimeFromEmail);

        logger.info("Finished checkTotalCost test");
    }
}
