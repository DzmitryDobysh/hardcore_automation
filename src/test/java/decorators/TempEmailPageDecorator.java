package decorators;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.TempEmailPage;

import java.time.Duration;

public class TempEmailPageDecorator extends TempEmailPage {

    private static final Logger logger = LogManager.getLogger(TempEmailPageDecorator.class);
    private TempEmailPage decoratedPage;

    public TempEmailPageDecorator(TempEmailPage decoratedPage) {
        super(decoratedPage.getDriver());
        this.decoratedPage = decoratedPage;
    }

    @FindBy(css = "#email-table > div:nth-child(1) > div:nth-child(3)")
    WebElement dateAndTimeElement;

    @Override
    public TempEmailPage openInNewTab() {
        return decoratedPage.openInNewTab();
    }

    @Override
    public String getTempEMail() {
        return decoratedPage.getTempEMail();
    }

    public String getDateAndTimeFromEmail() {
        logger.info("Getting date and time from email");
        Duration extendedTimeout = WAIT_TIMEOUT_SECONDS.plusSeconds(20);
        WebDriverWait wait = new WebDriverWait(decoratedPage.getDriver(), extendedTimeout);
        WebElement visibleElement = wait.until(ExpectedConditions.visibilityOf(dateAndTimeElement));
        String dateAndTime = visibleElement.getText();
        logger.info("Date and time of received email: {}", dateAndTime);
        return dateAndTime;
    }
}
