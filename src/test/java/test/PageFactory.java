package test;

import decorators.TempEmailPageDecorator;
import org.openqa.selenium.WebDriver;
import page.GoogleCloudMainPage;
import page.GoogleCloudPricingCalculatorPage;
import page.TempEmailPage;

public class PageFactory {
    public GoogleCloudMainPage createGoogleCloudMainPage(WebDriver driver) {
        return new GoogleCloudMainPage(driver);
    }

    public GoogleCloudPricingCalculatorPage createGoogleCloudPricingCalculatorPage(WebDriver driver) {
        return new GoogleCloudPricingCalculatorPage(driver);
    }

    public TempEmailPage createTempEmailPage(WebDriver driver) {
        TempEmailPage tempEmailPage = new TempEmailPage(driver);
        return new TempEmailPageDecorator(tempEmailPage);
    }
}
