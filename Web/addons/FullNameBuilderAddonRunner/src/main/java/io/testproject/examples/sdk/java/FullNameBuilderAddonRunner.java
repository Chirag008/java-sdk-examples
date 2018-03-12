package io.testproject.examples.sdk.java;

import io.testproject.java.classes.DriverSettings;
import io.testproject.java.enums.DriverType;
import io.testproject.java.sdk.generated.TestProjectRunner;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class FullNameBuilderAddonRunner {
    // Setting the development token
    private final static String devToken = "YOUR_DEV_TOKEN_GOES_HERE";

    public static void main(String[] args) throws Exception {
        // Creating driver settings to be used when running the test
        DriverSettings driverSettings = new DriverSettings(DriverType.Chrome);

        try (TestProjectRunner runner =
                     new TestProjectRunner(devToken, driverSettings, true)) {
            // Using the *getAgent* method provided by TestProjectRunner
            // gives access to the relevant driver in order to perform a "stage preparation" sequence before running the action.
            WebDriver driver = runner.getAgent().getWebDriver();

            // Set initial URL
            String URL = "https://example.testproject.io/web/";

            // Navigating to URL
            driver.navigate().to(URL);

            // Run Action
            runFullNameBuilderAction(runner);

            // Refresh page - discard state
            driver.navigate().refresh();

            // Run Element Action
            runFullNameBuilderElementAction(runner);

        } catch (Throwable e) {
            System.out.println("Execution failed: " + e.getMessage());
        }
    }

    private static void runFullNameBuilderAction(TestProjectRunner runner) {
        // Create action
        FullNameBuilderAction action = new FullNameBuilderAction();

        // Set parameters
        action.setFirstName("Elvis");
        action.setLastName("Presley");

        // Running action
        runner.run(action);
    }

    private static void runFullNameBuilderElementAction(TestProjectRunner runner) {
        // Create action
        FullNameBuilderElementAction action = new FullNameBuilderElementAction();

        // Set parameters
        action.setFirstName("John");
        action.setLastName("Smith");

        // Running action
        runner.run(action, By.id("personsContainer"));
    }
}