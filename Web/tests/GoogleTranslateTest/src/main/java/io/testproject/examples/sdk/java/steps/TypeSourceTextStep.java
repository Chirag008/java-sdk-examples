package io.testproject.examples.sdk.java.steps;

import io.testproject.examples.sdk.java.TestParameters;
import io.testproject.examples.sdk.java.pages.MainPage;
import io.testproject.java.sdk.framework.FunctionalStep;
import org.openqa.selenium.WebDriverException;
import org.springframework.util.StringUtils;

public class TypeSourceTextStep extends FunctionalStep {

    private String text;

    public TypeSourceTextStep() {

    }

    public TypeSourceTextStep(String textSupplier) {
        this.text = textSupplier;
    }


    @Override
    public String getDescription() {
        return String.format("Type source text %s", text);
    }

    @Override
    public boolean validate() {
        return true;
    }

    @Override
    public boolean run() {
        try {
            MainPage mainPage = new MainPage(_test);

            if (!StringUtils.isEmpty(_test.getParameter(TestParameters.TRANSLATED_TEXT))) {
                text = _test.getParameter(TestParameters.TRANSLATED_TEXT).toString();
            }

            mainPage.typeOriginalText(text);
            return pass();
        } catch (WebDriverException e) {
            return fail("Failed to type source text");
        }
    }
}
