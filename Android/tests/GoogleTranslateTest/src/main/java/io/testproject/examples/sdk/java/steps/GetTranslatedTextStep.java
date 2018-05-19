package io.testproject.examples.sdk.java.steps;

import io.testproject.examples.sdk.java.pages.MainPage;
import io.testproject.java.sdk.framework.FunctionalStep;
import org.openqa.selenium.WebDriverException;

public class GetTranslatedTextStep extends FunctionalStep {

    private String parameterName;

    public GetTranslatedTextStep() {
    }

    public GetTranslatedTextStep(String parameterName) {
        this.parameterName = parameterName;
    }

    @Override
    public String getDescription() {
        return "Get translated text";
    }

    @Override
    public boolean validate() {
        return true;
    }

    @Override
    public boolean run() {
        try {
            MainPage mainPage = new MainPage(_test);
            String translation = mainPage.getTranslation();

            _test.setParameter(parameterName, translation);
            _log.info("Setting test parameter {} = '{}'", parameterName, translation);
            return pass();
        } catch (WebDriverException e) {
            _log.error("Failed to get translated text", e);
            return fail("Failed to get translated text");
        }
    }
}
