package io.testproject.examples.sdk.java.pages;

import io.testproject.java.sdk.base.codeblocks.TestBase;
import io.testproject.java.sdk.drivers.TestProjectWebDriver;
import io.testproject.java.sdk.framework.BasePage;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.internal.Coordinates;
import org.openqa.selenium.support.FindBy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class MainPage extends BasePage<TestProjectWebDriver> {

    // EST = Element Selector Template
    public static final String EST_LANGUAGE = "//div[contains(@class, 'goog-menuitem-content') and text() = '%s']";

    private Logger log = LoggerFactory.getLogger(this.getClass());

    public MainPage(TestBase test) throws WebDriverException {
        super(test);
    }

    @FindBy(id = "gt-appname")
    private WebElement appName;

    @FindBy(id = "gt-sl-gms")
    private WebElement sourceLanguagesMenu;

    @FindBy(id = "gt-tl-gms")
    private WebElement targetLanguagesMenu;

    @FindBy(id = "goog-menuitem-group-2")
    private WebElement languages;

    @FindBy(id = "source")
    private WebElement sourceText;

    @FindBy(id = "gt-submit")
    private WebElement submit;

    @FindBy(id = "result_box")
    private WebElement resultBox;

    @FindBy(id = "gt-swap")
    private WebElement swapLanguages;

    @Override
    protected WebElement getIdentifyingElement() {
        return appName;
    }

    public void expandSourceLanguagesList() {
        sourceLanguagesMenu.click();
    }

    public void expandTargetLanguagesList() {
        targetLanguagesMenu.click();
    }

    public void selectLanguage(String language) {
        // Language is dynamic -> can't be a page object
        // Page has two drops down, one might be hidden, testing visibility

        String xpath = String.format(EST_LANGUAGE, language);
        List<WebElement> elements = languages.findElements(By.xpath(xpath));
        for (WebElement element : elements) {
            if (element.isDisplayed()) {
                element.click();
                break;
            }
        }
    }

    public void typeOriginalText(String text) {
        sourceText.sendKeys(text);
    }

    public void translate() {
        submit.click();
    }

    public String getTranslation() {
        return resultBox.getText();
    }

    public void swapLanguages() {
        swapLanguages.click();
    }

    public void clearSourceText() {
        sourceText.clear();
    }
}
