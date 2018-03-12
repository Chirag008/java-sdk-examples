package io.testproject.examples.sdk.java;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import org.openqa.selenium.By;

import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.testproject.java.enums.ExecutionResultType;
import io.testproject.java.sdk.generated.codeblocks.Test;

public class FullNameBuilderTest extends Test {

    @Override
    protected ExecutionResultType execute() throws Exception {

        // Get driver initialized by TestProject Agent
        IOSDriver<IOSElement> driver = this.getIOSDriver(IOSElement.class);

        // Find First name element and type 'John'
        IOSElement firstName = driver.findElement(By.id("firstName"));
        firstName.sendKeys("John");

        // Find Last name element and type 'Smith'
        IOSElement lastName = driver.findElement(By.id("lastName"));
        lastName.sendKeys("Smith");

        // Find Full Name element
        IOSElement fullName = driver.findElement(By.id("fullName"));

        // Verify that Full Name equals a concatenation of First and Last
        String fullNameText = fullName.getText();
        if (fullNameText.equals("John Smith")) {
            return ExecutionResultType.Passed;
        } else {
            return ExecutionResultType.Failed;
        }
    }
}

