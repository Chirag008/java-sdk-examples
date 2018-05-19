package io.testproject.examples.sdk.java.steps;

import io.testproject.examples.sdk.java.TestParameters;
import io.testproject.examples.sdk.java.pages.InputPage;
import io.testproject.examples.sdk.java.pages.MainPage;
import io.testproject.examples.sdk.java.pages.SetupPage;
import io.testproject.java.sdk.framework.FunctionalStep;
import org.openqa.selenium.WebDriverException;
import org.springframework.util.StringUtils;

public class TypeSourceTextStep extends FunctionalStep {

    private String text;

    public TypeSourceTextStep() {
    }

    public TypeSourceTextStep(String text) {
        this.text = text;
    }


    @Override
    public String getDescription() {
        return String.format("Type source text: '%s'", text);
    }

    @Override
    public boolean validate() {
        return !StringUtils.isEmpty(text) || !StringUtils.isEmpty(_test.getParameter(TestParameters.TRANSLATED_TEXT).toString());
    }

    @Override
    public boolean run() {
        try {
            MainPage mainPage = new MainPage(_test);
            mainPage.tapSourceTextPlaceholder();

            if (StringUtils.isEmpty(text)) {
                text = _test.getParameter(TestParameters.TRANSLATED_TEXT).toString();
            }

            InputPage inputPage = new InputPage(_test);
            inputPage.typeSourceText(text);
            return pass();
        } catch (WebDriverException e) {
            _log.error("Failed to tap Done", e);
            return fail("Failed to tap Done");
        }
    }
}
