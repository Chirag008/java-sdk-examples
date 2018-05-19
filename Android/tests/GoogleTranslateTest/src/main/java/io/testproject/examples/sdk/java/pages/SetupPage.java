package io.testproject.examples.sdk.java.pages;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.testproject.java.sdk.base.codeblocks.TestBase;
import io.testproject.java.sdk.drivers.TestProjectAndroidDriver;
import io.testproject.java.sdk.drivers.TestProjectWebDriver;
import io.testproject.java.sdk.framework.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class SetupPage extends BasePage<TestProjectAndroidDriver<AndroidElement>> {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    public SetupPage(TestBase test) throws WebDriverException {
        super(test);
    }

    @AndroidFindBy(xpath = "//android.widget.TextView[@text = 'Set up Google Translate']")
    private AndroidElement title;

    @AndroidFindBy(id = "button_done")
    private AndroidElement done;

    @AndroidFindBy(id = "onboarding_checkbox")
    private AndroidElement onboardingCheckbox;

    @Override
    protected WebElement getIdentifyingElement() {
        return title;
    }

    public void tapDone() {
        done.click();
    }

    public void disableTranslateOffline() {
        onboardingCheckbox.click();
    }
}
