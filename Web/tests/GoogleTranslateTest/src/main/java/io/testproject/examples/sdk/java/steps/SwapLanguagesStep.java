package io.testproject.examples.sdk.java.steps;

import io.testproject.examples.sdk.java.pages.MainPage;
import io.testproject.java.sdk.framework.FunctionalStep;
import org.openqa.selenium.WebDriverException;
import org.springframework.util.StringUtils;

public class SwapLanguagesStep extends FunctionalStep {

    public SwapLanguagesStep() {
    }

    @Override
    public String getDescription() {
        return "Swap languages";
    }

    @Override
    public boolean validate() {
        return true;
    }

    @Override
    public boolean run() {
        try {
            MainPage mainPage = new MainPage(_test);
            mainPage.swapLanguages();
            return pass();
        } catch (WebDriverException e) {
            return fail("Failed to swap languages");
        }
    }
}
