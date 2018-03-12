package io.testproject.examples.sdk.java;

import io.appium.java_client.MobileElement;
import io.appium.java_client.ios.IOSElement;
import io.testproject.java.annotations.ActionAnnotation;
import io.testproject.java.annotations.TestParameterAnnotation;
import io.testproject.java.enums.ExecutionResultType;
import io.testproject.java.enums.ParameterDirection;
import io.testproject.java.sdk.generated.codeblocks.Action;
import io.testproject.java.sdk.generated.codeblocks.ElementAction;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

@ActionAnnotation(
        name = "Verify full name generation element",
        summary = "Inputs first and last name, making sure that full name is updated accordingly",
        description = "Input {{firstName}} and {{lastName}}, verify Full Name is {firstName}} {{lastName}}'",
        version = "0.1")

public class FullNameBuilderElementAction extends ElementAction {
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
        // Get element provided to action
        IOSElement element = this.getElement(IOSElement.class);

        // Make sure that we are on the right page
        if (!element.getAttribute("name").equals("personsContainer")) {
            this.setMessage("Provided element is not a persons container");
            return ExecutionResultType.Failed;
        }

        // Enter first and last names in appropriate fields
        element.findElement(By.id("firstName")).sendKeys(firstName);
        element.findElement(By.id("lastName")).sendKeys(lastName);

        // Setup the expected full name that should appear
        String expectedFullName = firstName + " " + lastName;

        // Find Full Name element
        IOSElement fullName = (IOSElement) element.findElement(By.id("fullName"));

        // Verify that Full Name equals a concatenation of First and Last
        String fullNameText = fullName.getText();
        if (fullNameText.equals(expectedFullName)) {
            this.setMessage("Full name = " + fullNameText);
            return ExecutionResultType.Passed;
        } else {
            this.setMessage("Full name = " + fullNameText);
            return ExecutionResultType.Failed;
        }
    }

}

