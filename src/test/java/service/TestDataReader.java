package service;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ResourceBundle;

public class TestDataReader {

    private static final Logger logger = LogManager.getLogger(TestDataReader.class);
    private static ResourceBundle resourceBundle = ResourceBundle.getBundle(System.getProperty("environment"));

    public static String getTestData(String key) {
        logger.debug("Getting test data for key: {}", key);
        String testData = resourceBundle.getString(key);
        logger.debug("Test data for key '{}': {}", key, testData);
        return testData;
    }

}