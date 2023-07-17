package service;

import model.FormDefaultModel;

public class FormDefaultModelCreator {
    public static FormDefaultModel createDefaultFormModel() {
        FormDefaultModel formModel = new FormDefaultModel();
        formModel.setInstances("4");
        return formModel;
    }
}
