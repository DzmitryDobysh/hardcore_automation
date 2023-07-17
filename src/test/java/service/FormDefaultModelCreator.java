package service;

import model.FormDefaultModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class FormDefaultModelCreator {

    private static final Logger logger = LogManager.getLogger(FormDefaultModelCreator.class);
    public static final String INSTANCES = "testdata.instances";

    public static FormDefaultModel createDefaultFormModel() {
        logger.debug("Creating default form model");
        FormDefaultModel formModel = new FormDefaultModel();
        formModel.setInstances(TestDataReader.getTestData(INSTANCES));
        logger.debug("Default form model created: {}", formModel);
        return formModel;
    }
}
