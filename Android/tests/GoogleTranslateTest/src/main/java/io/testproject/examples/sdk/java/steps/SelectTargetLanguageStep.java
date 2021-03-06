package io.testproject.examples.sdk.java.steps;

import io.testproject.examples.sdk.java.pages.LanguagesPage;
import io.testproject.examples.sdk.java.pages.MainPage;
import io.testproject.examples.sdk.java.pages.SetupPage;
import io.testproject.java.sdk.framework.FunctionalStep;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriverException;
import org.springframework.util.StringUtils;

public class SelectTargetLanguageStep extends FunctionalStep {

    public static final int STALE_MAX_RETRIES = 15;
    private String language;

    public SelectTargetLanguageStep() {
    }

    public SelectTargetLanguageStep(String language) {
        this.language = language;
    }


    @Override
    public String getDescription() {
        return String.format("Select target language: %s", language);
    }

    @Override
    public boolean validate() {
        return !StringUtils.isEmpty(language);
    }

    @Override
    public boolean run() {
        try {
            MainPage mainPage = null;
            for (int i = 0; i < STALE_MAX_RETRIES; i++) {
                try {
                    mainPage = new MainPage(_test);
                    break;
                } catch (StaleElementReferenceException e) {
                    _log.warn("Ignoring StaleElementReferenceException...");
                }

            }

            if (mainPage == null) {
                return fail("Failed to select target language");
            }

            mainPage.tapTargetLanguage();

            LanguagesPage languagesPage = null;
            for (int i = 0; i < STALE_MAX_RETRIES; i++) {
                try {
                    languagesPage = new LanguagesPage(_test);
                    break;
                } catch (StaleElementReferenceException e) {
                    _log.warn("Ignoring StaleElementReferenceException...");
                }

            }

            if (languagesPage == null) {
                return fail("Failed to select target language");
            }

            languagesPage.tapSearch();
            languagesPage.typeLanguageName(language);
            languagesPage.tapFirstSuggestion();

            return pass();
        } catch (WebDriverException e) {
            _log.error("Failed to select target language: {}", language, e);
            return fail("Failed to select target language");
        }
    }
}
