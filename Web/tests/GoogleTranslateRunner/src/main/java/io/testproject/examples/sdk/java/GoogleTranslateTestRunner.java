package io.testproject.examples.sdk.java;

import io.testproject.examples.sdk.java.tests.GoogleTranslateTest;
import io.testproject.java.classes.DriverSettings;
import io.testproject.java.enums.DriverType;
import io.testproject.java.sdk.generated.TestProjectRunner;

public class GoogleTranslateTestRunner {
    // Setting the development token
    private final static String devToken = System.getenv("TP_DEV_KEY");

    public static void main(String[] args) throws Exception {
        // Creating driver settings to be used when running the test
        DriverSettings driverSettings = new DriverSettings(DriverType.Chrome);

        // Running the test using TestProjectRunner (the last 'true' parameter is there to enable logging)
        try (TestProjectRunner runner = new TestProjectRunner(devToken,
                driverSettings, true)) {

            // Create test
            GoogleTranslateTest test = new GoogleTranslateTest(
                    "https://translate.google.com/",
                    "Hello World!",
                    "English",
                    "Russian");

            // Run test
            runner.run(test);

        } catch (Throwable e) {
            // Handle failure...
        }
    }
}