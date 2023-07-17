package test;

import model.FormDefaultModel;
import org.testng.Assert;
import org.testng.annotations.Test;
import page.GoogleCloudMainPage;
import page.GoogleCloudPricingCalculatorPage;
import page.TempEmailPage;
import service.FormDefaultModelCreator;

public class HardcoreTest extends CommonConditions {

    String SEARCH_TERM_MAIN_PAGE = "Google Cloud Platform Pricing Calculator";
    GoogleCloudMainPage cloudMainPage;
    GoogleCloudPricingCalculatorPage cloudCalculatorPage;
    TempEmailPage tempEmailPage;

    @Test(description = "Fill in estimation form")
    public void fillInEstimationForm() {
        FormDefaultModel formModel = FormDefaultModelCreator.createDefaultFormModel();

        cloudMainPage = new GoogleCloudMainPage(driver)
                .openPage()
                .searchTerm(SEARCH_TERM_MAIN_PAGE)
                .clickCalculatorPage();
        cloudCalculatorPage = new GoogleCloudPricingCalculatorPage(driver)
                .switchToCalculatorIFrame()
                .fillInEstimationForm(formModel)
                .clickAddToEstimateButton();
    }

    @Test(dependsOnMethods = {"fillInEstimationForm"},
            description = "Check Email total cost against calculated total cost")
    public void checkTotalCost() {
        tempEmailPage = new TempEmailPage(driver).openInNewTab();
        String tempEmail = tempEmailPage.getTempEMail();

        cloudCalculatorPage.switchToPreviousTab();
        cloudCalculatorPage.switchToCalculatorIFrame()
                .clickEmailEstimateButton()
                .enterTempEMail(tempEmail)
                .sendEmail();
        String costOnCalcPage = cloudCalculatorPage.getTotalCost();

        tempEmailPage.switchToNextTab();
        String costOnEmailPage = tempEmailPage.getCostFromEmail();

        Assert.assertTrue(costOnCalcPage.contains(costOnEmailPage), "Total Cost from email doesn't match the one from the Calculator page");
    }
}
