package io.testproject.examples.sdk.java.steps;

import io.testproject.examples.sdk.java.TestParameters;
import io.testproject.examples.sdk.java.pages.SetupPage;
import io.testproject.java.sdk.framework.FunctionalStep;
import org.openqa.selenium.WebDriverException;

public class CompareResultsStep extends FunctionalStep {

    private String sourceText;
    private int seconds;

    public CompareResultsStep() {
    }

    public CompareResultsStep(String sourceText) {
        this.sourceText = sourceText;
    }

    @Override
    public String getDescription() {
        return String.format("Compare '%s' to '%s'", sourceText, _test.getParameter(TestParameters.REVERSE_TRANSLATED_TEXT));
    }

    @Override
    public boolean validate() {
        return true;
    }

    @Override
    public boolean run() {
        String reverseTranslation = _test.getParameter(TestParameters.REVERSE_TRANSLATED_TEXT).toString();
        if (sourceText.equals(reverseTranslation)) {
            return pass();
        }

        return fail(String.format("%s is not equal equal %s", sourceText, reverseTranslation));
    }
}
