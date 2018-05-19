package io.testproject.examples.sdk.java.pages;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.testproject.java.sdk.base.codeblocks.TestBase;
import io.testproject.java.sdk.drivers.TestProjectAndroidDriver;
import io.testproject.java.sdk.drivers.TestProjectWebDriver;
import io.testproject.java.sdk.framework.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MainPage extends BasePage<TestProjectAndroidDriver<AndroidElement>> {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    public MainPage(TestBase test) throws WebDriverException {
        super(test);
    }

    @AndroidFindBy(id = "toolbar_logo_image")
    private AndroidElement logoImage;

    @AndroidFindBy(id = "picker1")
    private AndroidElement sourceLanguage;

    @AndroidFindBy(id = "picker2")
    private AndroidElement targetLanguage;

    @AndroidFindBy(id = "touch_to_type_text")
    private AndroidElement sourceTextPlaceholder;

    @AndroidFindBy(xpath = "//android.widget.TextView[@resource-id = 'android:id/text1']")
    private AndroidElement translatedText;

    @Override
    protected WebElement getIdentifyingElement() {
        return logoImage;
    }

    public void tapSourceLanguage() {
        sourceLanguage.click();
    }

    public void tapTargetLanguage() {
        targetLanguage.click();
    }

    public void tapSourceTextPlaceholder() {
        sourceTextPlaceholder.click();
    }

    public String getTranslation() {
        return translatedText.getText();
    }
}
