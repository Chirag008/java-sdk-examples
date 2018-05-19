package io.testproject.examples.sdk.java.pages;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.testproject.java.sdk.base.codeblocks.TestBase;
import io.testproject.java.sdk.drivers.TestProjectAndroidDriver;
import io.testproject.java.sdk.drivers.TestProjectWebDriver;
import io.testproject.java.sdk.framework.BasePage;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LanguagesPage extends BasePage<TestProjectAndroidDriver<AndroidElement>> {
    private Logger log = LoggerFactory.getLogger(this.getClass());

    public LanguagesPage(TestBase test) throws WebDriverException {
        super(test);
    }

    @AndroidFindBy(id = "toolbar")
    private AndroidElement toolbar;

    @AndroidFindBy(id = "languages_search")
    private AndroidElement searchLangBtn;

    @AndroidFindBy(id = "search_src_text")
    private AndroidElement searchLangField;

    @AndroidFindBy(id = "android:id/text1")
    private AndroidElement firstSuggestion;


    @Override
    protected WebElement getIdentifyingElement() {
        return toolbar;
    }

    public void tapSearch() {
        searchLangBtn.click();
    }

    public void typeLanguageName(String language) {
        searchLangField.sendKeys(language);
    }

    public void tapFirstSuggestion() {
        firstSuggestion.click();
    }
}
