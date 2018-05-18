package io.testproject.examples.sdk.java.steps;

import io.testproject.examples.sdk.java.pages.MainPage;
import io.testproject.java.sdk.framework.FunctionalStep;
import org.openqa.selenium.WebDriverException;

public class TranslateStep extends FunctionalStep {

    public TranslateStep() {
    }

    @Override
    public String getDescription() {
        return String.format("Translate");
    }

    @Override
    public boolean validate() {
        return true;
    }

    @Override
    public boolean run() {
        try {
            MainPage mainPage = new MainPage(_test);
            mainPage.translate();
            return pass();
        } catch (WebDriverException e) {
            return fail("Failed to translate");
        }
    }
}
