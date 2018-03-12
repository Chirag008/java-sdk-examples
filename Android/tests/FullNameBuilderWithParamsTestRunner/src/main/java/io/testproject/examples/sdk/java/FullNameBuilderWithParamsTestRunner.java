package io.testproject.examples.sdk.java;

import io.testproject.java.classes.DriverSettings;
import io.testproject.java.classes.MobileDriverSettings;
import io.testproject.java.enums.DriverType;
import io.testproject.java.sdk.generated.TestProjectRunner;

public class FullNameBuilderWithParamsTestRunner {
    static String devToken = "YOUR_DEV_TOKEN_GOES_HERE";

    public static void main(String[] args) throws Exception {
        DriverSettings driverSettings = getDriverSettings();

        try (TestProjectRunner runner =
                     new TestProjectRunner(devToken, driverSettings, true)) {
            // Creating an instance of the test
            // If you decide creating a constructor to take in the parameters, make sure to create a parameter-less constructor as well
            FullNameBuilderWithParamsTest test = new FullNameBuilderWithParamsTest();

            // Setting test parameters
            test.firstName = "John";
            test.lastName = "Smith";

            // Running the test
            runner.run(test);
        } catch (Throwable e) {
            // Handle failure...
        }
    }

    // Initializes driver settings
    private static DriverSettings getDriverSettings() {
        DriverSettings driverSettings = new DriverSettings();

        // Declare that Android driver is required
        driverSettings.setDriverType(DriverType.Appium_Android);

        // Specify target device UDID and Name
        // This information is conveniently visible at https://app.testproject.io/#/agents
        // Under your connected agent on the devices tab
        driverSettings.setDeviceUdid("DEVICE_UUID");
        driverSettings.setDeviceName("DEVICE_NAME");

        // Specify mobile app settings
        MobileDriverSettings mobileSettings = new MobileDriverSettings();
        mobileSettings.setAndroidPackage("io.testproject.demo");
        mobileSettings.setAndroidActivity(".MainActivity");
        driverSettings.setMobileSettings(mobileSettings);

        return driverSettings;
    }
}