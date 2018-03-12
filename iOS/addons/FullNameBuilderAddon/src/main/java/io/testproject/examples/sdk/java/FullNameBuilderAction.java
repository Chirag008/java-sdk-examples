package io.testproject.examples.sdk.java;

import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.testproject.java.annotations.ActionAnnotation;
import io.testproject.java.annotations.TestParameterAnnotation;
import io.testproject.java.enums.ExecutionResultType;
import io.testproject.java.enums.ParameterDirection;
import io.testproject.java.sdk.generated.codeblocks.Action;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@ActionAnnotation(
        name = "Verify full name generation",
        summary = "Inputs first and last name, making sure that full name is updated accordingly",
        description = "Input {{firstName}} and {{lastName}}, verify Full Name is {firstName}} {{lastName}}'",
        version = "0.1")

public class FullNameBuilderAction extends Action {
    @TestParameterAnnotation(direction = ParameterDirection.INPUT,	description = "First name")
    private String firstName;

    @TestParameterAnnotation(direction = ParameterDirection.INPUT,	description = "Last name")
    private String lastName;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    protected ExecutionResultType execute() throws Exception {
        // Get driver initialized by TestProject Agent
        // No need to specify browser type, it can be done later via UI
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
            this.setMessage("Full name = " + fullNameText);
            return ExecutionResultType.Passed;
        } else {
            this.setMessage("Full name = " + fullNameText);
            return ExecutionResultType.Failed;
        }
    }

}

