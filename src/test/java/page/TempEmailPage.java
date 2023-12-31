package page;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class TempEmailPage extends AbstractPage {
    private static final Logger logger = LogManager.getLogger(TempEmailPage.class);

    //used another service instead of https://yopmail.com/en/ - probably got banned for no reason
    String TEMP_EMAIL_URL = "https://generator.email/email-generator";

    @FindBy(id = "email_ch_text")
    WebElement fieldTempEmail;

    @FindBy(xpath = "//h3[contains(text(), 'USD')]")
    WebElement priceAmount;

    public TempEmailPage(WebDriver driver) {
        super(driver);
    }

    public TempEmailPage openInNewTab() {
        logger.info("Opening TempEmailPage in a new tab");
        openNewTab();
        switchToNextTab();
        driver.get(TEMP_EMAIL_URL);
        return this;
    }

    public String getTempEMail() {
        logger.info("Getting temporary email address");
        return waitForWebElementVisible(fieldTempEmail).getText();
    }

    public String getCostFromEmail() {
        logger.info("Getting cost from email");
        Duration extendedTimeout = WAIT_TIMEOUT_SECONDS.plusSeconds(20);
        WebDriverWait wait = new WebDriverWait(driver, extendedTimeout);
        WebElement visibleElement = wait.until(ExpectedConditions.visibilityOf(priceAmount));
        String cost = visibleElement.getText();
        logger.info("Cost from email: {}", cost);
        return cost;
    }
}
