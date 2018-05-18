package io.testproject.examples.sdk.java.steps;

import io.testproject.examples.sdk.java.TestParameters;
import io.testproject.examples.sdk.java.pages.MainPage;
import io.testproject.java.sdk.framework.FunctionalStep;
import org.openqa.selenium.WebDriverException;
import org.springframework.util.StringUtils;

import java.util.function.Supplier;

public class CompareResultsStep extends FunctionalStep {

    private String originalText;

    public CompareResultsStep() {
    }

    public CompareResultsStep(String originalText) {
        this.originalText = originalText;
    }

    @Override
    public String getDescription() {
        return String.format("Compare '%s' to '%s'", originalText, _test.getParameter(TestParameters.REVERSE_TRANSLATED_TEXT));
    }

    @Override
    public boolean validate() {

        if (StringUtils.isEmpty(originalText)) {
            return false;
        }

        return true;

    }

    @Override
    public boolean run() {
        String reverseTranslation = _test.getParameter(TestParameters.REVERSE_TRANSLATED_TEXT).toString();
        if (originalText.equals(reverseTranslation)) {
            return pass();
        }

        return fail(String.format("%s is not equal equal %s", originalText, reverseTranslation));
    }
}
