package io.testproject.examples.sdk.java.steps;

import io.testproject.java.sdk.framework.FunctionalStep;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;

import java.net.MalformedURLException;
import java.net.URL;

public class OpenGoogleTranslateStep extends FunctionalStep {

    private String appUrl;

    public OpenGoogleTranslateStep() {
    }

    public OpenGoogleTranslateStep(String appUrl) {
        this.appUrl = appUrl;
    }

    @Override
    public String getDescription() {
        return String.format("Navigate to Google Translate at %s", appUrl.toString());
    }

    @Override
    public boolean validate() {
        try {
            URL url = new URL(appUrl);
            return true;
        } catch (MalformedURLException e) {
            _log.error("URL is malformed", e);
            return false;
        }
    }

    @Override
    public boolean run() {

        WebDriver driver = null;
        try {
            driver = _test.getWebDriver();
        } catch (Exception e) {
            return fail("Failed to initialize driver");
        }

        try {
            driver.navigate().to(appUrl);
            return pass();
        } catch (WebDriverException e) {
            return fail("Failed navigating to Google Translate at " + appUrl);
        }
    }
}
