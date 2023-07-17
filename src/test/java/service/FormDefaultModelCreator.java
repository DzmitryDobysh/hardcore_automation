package service;

import model.FormDefaultModel;

public class FormDefaultModelCreator {

    public static final String INSTANCES = "testdata.instances";
    public static FormDefaultModel createDefaultFormModel() {
        FormDefaultModel formModel = new FormDefaultModel();
        formModel.setInstances(TestDataReader.getTestData(INSTANCES));
        return formModel;
    }
}
