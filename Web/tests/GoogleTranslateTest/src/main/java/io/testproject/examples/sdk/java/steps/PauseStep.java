package io.testproject.examples.sdk.java.steps;

import io.testproject.examples.sdk.java.pages.MainPage;
import io.testproject.java.sdk.framework.FunctionalStep;
import org.openqa.selenium.WebDriverException;

import java.util.concurrent.TimeUnit;

public class PauseStep extends FunctionalStep {

    private int seconds;

    public PauseStep() {
    }

    public PauseStep(int seconds) {
        this.seconds = seconds;
    }

    @Override
    public String getDescription() {
        return String.format("Pause %d seconds", seconds);
    }

    @Override
    public boolean validate() {
        return true;
    }

    @Override
    public boolean run() {
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            return fail("Interrupted");
        }

        return pass();
    }
}
