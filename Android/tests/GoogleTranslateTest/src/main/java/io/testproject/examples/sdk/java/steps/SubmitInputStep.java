package io.testproject.examples.sdk.java.steps;

import io.testproject.examples.sdk.java.pages.InputPage;
import io.testproject.examples.sdk.java.pages.LanguagesPage;
import io.testproject.examples.sdk.java.pages.MainPage;
import io.testproject.java.sdk.framework.FunctionalStep;
import org.openqa.selenium.WebDriverException;
import org.springframework.util.StringUtils;

public class SubmitInputStep extends FunctionalStep {

    public SubmitInputStep() {
    }


    @Override
    public String getDescription() {
        return "Submit input";
    }

    @Override
    public boolean validate() {
        return true;
    }

    @Override
    public boolean run() {
        try {

            InputPage inputPage = new InputPage(_test);
            inputPage.hitEnter();

            return pass();
        } catch (WebDriverException e) {
            _log.error("Failed to submit input", e);
            return fail("Failed to submit input");
        }
    }
}
