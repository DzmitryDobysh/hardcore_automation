package test;

import driver.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;

import java.io.File;

@Listeners({util.TestListener.class})
public class CommonConditions {
    protected WebDriver driver;

    @BeforeTest(alwaysRun = true)
    public void browserSetup() {
        createScreenshotsFolder();
        driver = DriverSingleton.getDriver();
    }

    @AfterTest(alwaysRun = true)
    public void browserExit() {
        DriverSingleton.closeDriver();
    }

    private void createScreenshotsFolder() {
        File screenshotsFolder = new File(".//target/screenshots");
        boolean folderCreated = screenshotsFolder.mkdirs();
        if (folderCreated || screenshotsFolder.exists()) {
            System.out.println("Screenshots folder created successfully.");
        } else {
            System.err.println("Failed to create screenshots folder.");
        }
    }
}
