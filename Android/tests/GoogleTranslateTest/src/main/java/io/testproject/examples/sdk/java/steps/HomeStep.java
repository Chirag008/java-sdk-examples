package io.testproject.examples.sdk.java.steps;

import io.testproject.examples.sdk.java.pages.MenuPage;
import io.testproject.examples.sdk.java.pages.SetupPage;
import io.testproject.java.sdk.framework.FunctionalStep;
import org.openqa.selenium.WebDriverException;

public class HomeStep extends FunctionalStep {

    public HomeStep() {
    }


    @Override
    public String getDescription() {
        return "Tap Home";
    }

    @Override
    public boolean validate() {
        return true;
    }

    @Override
    public boolean run() {
        try {
            MenuPage menuPage = new MenuPage(_test);
            menuPage.tapHome();
            return pass();
        } catch (WebDriverException e) {
            _log.error("Failed to tap Home", e);
            return fail("Failed to tap Home");
        }
    }
}
