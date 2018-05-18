package io.testproject.examples.sdk.java.steps;

import io.testproject.examples.sdk.java.pages.MainPage;
import io.testproject.java.sdk.framework.FunctionalStep;
import org.openqa.selenium.WebDriverException;
import org.springframework.util.StringUtils;

public class SelectLanguageStep extends FunctionalStep {

    private String language;

    public SelectLanguageStep() {
    }

    public SelectLanguageStep(String language) {
        this.language = language;
    }


    @Override
    public String getDescription() {
        return String.format("Select language: %s", language);
    }

    @Override
    public boolean validate() {
        if (StringUtils.isEmpty(language)) {
            return false;
        }

        return true;
    }

    @Override
    public boolean run() {
        try {
            MainPage mainPage = new MainPage(_test);
            mainPage.selectLanguage(language);
            return pass();
        } catch (WebDriverException e) {
            return fail("Failed to select language");
        }
    }
}
