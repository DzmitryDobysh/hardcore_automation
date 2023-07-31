package test;

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
        return new TempEmailPage(driver);
    }
}
