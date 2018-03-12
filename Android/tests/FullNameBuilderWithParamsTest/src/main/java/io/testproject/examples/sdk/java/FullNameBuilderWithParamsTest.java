package io.testproject.examples.sdk.java;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.testproject.java.annotations.TestAnnotation;
import io.testproject.java.annotations.TestParameterAnnotation;
import io.testproject.java.enums.ExecutionResultType;
import io.testproject.java.enums.ParameterDirection;
import io.testproject.java.enums.TakeScreenshotConditionType;
import io.testproject.java.sdk.generated.codeblocks.Test;
import org.openqa.selenium.By;

@TestAnnotation(
        name = "Full Name Builder",
        summary = "Fills in first & last names and validates the generated full name",
        description = "Enter '{{firstName}}' into the first name field & '{{lastName}}' into the last name field")
public class FullNameBuilderWithParamsTest extends Test {

    @TestParameterAnnotation(
            direction = ParameterDirection.INPUT,
            description = "First name to input",
            defaultValue = "John")
    public String firstName;

    @TestParameterAnnotation(
            direction = ParameterDirection.INPUT,
            description = "Last name to input",
            defaultValue = "Smith")
    public String lastName;

    @TestParameterAnnotation(direction = ParameterDirection.OUTPUT,
            description = "Full name (builder result)")
    public String fullName;

    @Override
    protected ExecutionResultType execute() throws Exception {

        // Get driver initialized by TestProject Agent
        AndroidDriver<AndroidElement> driver = this.getAndroidDriver(AndroidElement.class);

        // Find First name element and type in firstName
        AndroidElement firstNameElement = driver.findElement(By.id("firstName"));
        firstNameElement.sendKeys(this.firstName);
        // Assert success if value is in place
        this.assertThat("First name contains: " + this.firstName, TakeScreenshotConditionType.Always)
                .assertTrue(firstNameElement.getText().equals(this.firstName));

        // Find Last name element and type in lastName
        AndroidElement lastNameElement = driver.findElement(By.id("lastName"));
        lastNameElement.sendKeys(this.lastName);
        // Assert success if value is in place
        this.assertThat("Last name contains: " + this.lastName, TakeScreenshotConditionType.Always)
                .assertTrue(lastNameElement.getText().equals(this.lastName));

        // Find Full Name element
        AndroidElement fullName = driver.findElement(By.id("fullName"));

        // Verify that Full Name equals a concatenation of First and Last
        this.fullName = fullName.getText();
        if (this.fullName.equals("John Smith")) {
            this.setMessage("Full name = " + this.fullName);
            return ExecutionResultType.Passed;
        } else {
            this.setMessage(String.format("Full name: %s; Expected: %s %s", this.fullName, this.firstName, this.lastName));
            return ExecutionResultType.Failed;
        }
    }
}

