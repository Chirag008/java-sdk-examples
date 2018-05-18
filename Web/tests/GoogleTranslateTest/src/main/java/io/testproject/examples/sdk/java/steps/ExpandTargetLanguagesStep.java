package io.testproject.examples.sdk.java.steps;

import io.testproject.examples.sdk.java.pages.MainPage;
import io.testproject.java.sdk.framework.FunctionalStep;
import org.openqa.selenium.WebDriverException;

public class ExpandTargetLanguagesStep extends FunctionalStep {

    public ExpandTargetLanguagesStep() {
    }

    @Override
    public String getDescription() {
        return "Expand target languages list";
    }

    @Override
    public boolean validate() {
        return true;
    }

    @Override
    public boolean run() {
        try {
            MainPage mainPage = new MainPage(_test);
            mainPage.expandTargetLanguagesList();
            return pass();
        } catch (WebDriverException e) {
           return fail("Failed to expand languages list");
        }
    }
}
