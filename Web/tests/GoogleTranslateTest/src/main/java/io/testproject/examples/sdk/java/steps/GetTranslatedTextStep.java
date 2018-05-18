package io.testproject.examples.sdk.java.steps;

import io.testproject.examples.sdk.java.TestParameters;
import io.testproject.examples.sdk.java.pages.MainPage;
import io.testproject.java.sdk.framework.FunctionalStep;
import org.openqa.selenium.WebDriverException;
import org.springframework.util.StringUtils;

import java.util.concurrent.TimeUnit;

public class GetTranslatedTextStep extends FunctionalStep {

    private static final int MAX_WAIT = 5;
    private String outParameterName;

    public GetTranslatedTextStep() {
    }

    public GetTranslatedTextStep(String outParameterName) {
        this.outParameterName = outParameterName;
    }


    @Override
    public String getDescription() {
        return String.format("Get translated text");
    }

    @Override
    public boolean validate() {
        return true;
    }

    @Override
    public boolean run() {
        try {
            MainPage mainPage = new MainPage(_test);
            String translation = null;

            for (int i = 0; i < MAX_WAIT; i++) {
              translation = mainPage.getTranslation();

              if (StringUtils.isEmpty(translation)) {
                  TimeUnit.SECONDS.sleep(1);
              } else {
                  break;
              }
            }
            _log.info("Translated text: '{}'", translation);
            _test.setParameter(outParameterName, translation);
            return pass();
        } catch (WebDriverException e) {
           return fail("Failed to translate");
        } catch (InterruptedException e) {
            return fail("Failed to wait for translation (interrupted)");
        }
    }
}
