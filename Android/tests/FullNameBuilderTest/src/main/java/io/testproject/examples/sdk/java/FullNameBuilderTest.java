package io.testproject.examples.sdk.java;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.testproject.java.enums.ExecutionResultType;
import io.testproject.java.sdk.generated.codeblocks.Test;
import org.openqa.selenium.By;

public class FullNameBuilderTest extends Test {

    @Override
    protected ExecutionResultType execute() throws Exception {

        // Get driver initialized by TestProject Agent
        AndroidDriver<AndroidElement> driver = this.getAndroidDriver(AndroidElement.class);

        // Find First name element and type 'John'
        AndroidElement firstName = driver.findElement(By.id("firstName"));
        firstName.sendKeys("John");

        // Find Last name element and type 'Smith'
        AndroidElement lastName = driver.findElement(By.id("lastName"));
        lastName.sendKeys("Smith");

        // Find Full Name element
        AndroidElement fullName = driver.findElement(By.id("fullName"));

        // Verify that Full Name equals a concatenation of First and Last
        String fullNameText = fullName.getText();
        if (fullNameText.equals("John Smith")) {
            return ExecutionResultType.Passed;
        } else {
            return ExecutionResultType.Failed;
        }
    }
}

