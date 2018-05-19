package io.testproject.examples.sdk.java;

import io.testproject.examples.sdk.java.tests.GoogleTranslateTest;
import io.testproject.java.classes.DriverSettings;
import io.testproject.java.classes.MobileDriverSettings;
import io.testproject.java.enums.DriverType;
import io.testproject.java.sdk.generated.TestProjectRunner;

public class GoogleTranslateTestRunner {
    // Setting the development token
    private final static String devToken = System.getenv("TP_DEV_KEY");

    public static void main(String[] args) throws Exception {
        // Running the test using TestProjectRunner (the last 'true' parameter is there to enable logging)
        try (TestProjectRunner runner = new TestProjectRunner(devToken,
                getDriverSettings(), true)) {

            // Create test
            GoogleTranslateTest test = new GoogleTranslateTest(
                    "Hello World!",
                    "English",
                    "Russian");

            // Run test
            runner.run(test);

        } catch (Throwable e) {
            // Handle failure...
        }
    }

    // Initializes driver settings
    private static DriverSettings getDriverSettings() {
        DriverSettings driverSettings = new DriverSettings();
        driverSettings.setDriverType(DriverType.Appium_Android);

        driverSettings.setDeviceUdid(System.getenv("TP_DEVICE_UDID"));
        driverSettings.setDeviceName(System.getenv("TP_DEVICE_UDID"));

        MobileDriverSettings mobileSettings = new MobileDriverSettings();
        mobileSettings.setAndroidPackage("com.google.android.apps.translate");
        mobileSettings.setAndroidActivity("com.google.android.apps.translate.TranslateActivity");
        driverSettings.setMobileSettings(mobileSettings);
        return driverSettings;
    }
}