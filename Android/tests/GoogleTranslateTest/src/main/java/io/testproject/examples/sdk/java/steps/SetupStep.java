package io.testproject.examples.sdk.java.steps;

import io.testproject.examples.sdk.java.pages.SetupPage;
import io.testproject.java.sdk.framework.FunctionalStep;
import org.openqa.selenium.WebDriverException;

import java.util.concurrent.TimeUnit;

public class SetupStep extends FunctionalStep {

    private int seconds;

    public SetupStep() {
    }


    @Override
    public String getDescription() {
        return "Set up Google Translate";
    }

    @Override
    public boolean validate() {
        return true;
    }

    @Override
    public boolean run() {
        try {
            SetupPage setupPage = new SetupPage(_test);
            setupPage.disableTranslateOffline();
            setupPage.tapDone();
            return pass();
        } catch (WebDriverException e) {
            _log.error("Failed to tap Done", e);
            return fail("Failed to tap Done");
        }
    }
}
