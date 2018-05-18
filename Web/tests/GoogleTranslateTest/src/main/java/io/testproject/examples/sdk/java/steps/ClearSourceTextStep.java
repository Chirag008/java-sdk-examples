package io.testproject.examples.sdk.java.steps;

import io.testproject.examples.sdk.java.pages.MainPage;
import io.testproject.java.sdk.framework.FunctionalStep;
import org.openqa.selenium.WebDriverException;

import java.util.function.Supplier;

public class ClearSourceTextStep extends FunctionalStep {

    public ClearSourceTextStep() {
    }


    @Override
    public String getDescription() {
        return "Clear source text";
    }

    @Override
    public boolean validate() {
        return true;
    }

    @Override
    public boolean run() {
        try {
            MainPage mainPage = new MainPage(_test);
            mainPage.clearSourceText();
            return pass();
        } catch (WebDriverException e) {
            return fail("Failed to type source text");
        }
    }
}
