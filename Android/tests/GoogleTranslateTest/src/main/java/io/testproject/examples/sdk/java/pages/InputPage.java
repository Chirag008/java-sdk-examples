package io.testproject.examples.sdk.java.pages;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.testproject.java.sdk.base.codeblocks.TestBase;
import io.testproject.java.sdk.drivers.TestProjectAndroidDriver;
import io.testproject.java.sdk.drivers.TestProjectWebDriver;
import io.testproject.java.sdk.framework.BasePage;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class InputPage extends BasePage<TestProjectAndroidDriver<AndroidElement>> {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    public InputPage(TestBase test) throws WebDriverException {
        super(test);
    }

    @AndroidFindBy(id = "edit_input")
    private AndroidElement editInput;

    @Override
    protected WebElement getIdentifyingElement() {
        return editInput;
    }

    @AndroidFindBy(id = "edit_input")
    private AndroidElement sourceText;

    public void typeSourceText(String text) {
        sourceText.sendKeys(text);
    }

    public void hitEnter() {
        driver.pressKeyCode(66);
    }

}
