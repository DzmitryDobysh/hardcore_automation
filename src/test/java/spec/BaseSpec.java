package spec;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;

public class BaseSpec {

    protected WebDriver driver;

    protected final Duration WAIT_TIMEOUT_SECONDS = Duration.ofSeconds(15);

    protected WebElement waitForWebElementVisible(WebElement element) {
        return new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).until(ExpectedConditions.visibilityOf(element));
    }

    protected BaseSpec(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    protected void openNewTab() {
        ((JavascriptExecutor) driver).executeScript("window.open()");
    }

    public void switchToPreviousTab() {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        String currentTab = driver.getWindowHandle();
        int currentTabIndex = tabs.indexOf(currentTab);
        driver.switchTo().window(tabs.get(currentTabIndex - 1));
    }

    public void switchToNextTab() {
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        String currentTab = driver.getWindowHandle();
        int currentTabIndex = tabs.indexOf(currentTab);
        driver.switchTo().window(tabs.get(currentTabIndex + 1));
    }
}
