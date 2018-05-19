package io.testproject.examples.sdk.java.pages;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.testproject.java.sdk.base.codeblocks.TestBase;
import io.testproject.java.sdk.drivers.TestProjectAndroidDriver;
import io.testproject.java.sdk.framework.BasePage;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MenuPage extends BasePage<TestProjectAndroidDriver<AndroidElement>> {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    public MenuPage(TestBase test) throws WebDriverException {
        super(test);
    }

    @AndroidFindBy(id = "drawer_layout")
    private AndroidElement menuDrawer;

    @AndroidFindBy(xpath = "//android.widget.CheckedTextView[@text = 'Home']")
    private AndroidElement home;

    @Override
    protected WebElement getIdentifyingElement() {
        return menuDrawer;
    }

    public void tapHome() {
        home.click();
    }
}
