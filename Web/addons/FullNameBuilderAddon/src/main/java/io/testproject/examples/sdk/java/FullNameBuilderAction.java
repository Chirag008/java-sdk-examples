package io.testproject.examples.sdk.java;

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

    @TestParameterAnnotation(direction = ParameterDirection.OUTPUT,	description = "Full name")
    private String fullName;

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

    public String getFullName() {
        return fullName;
    }

    @Override
    protected ExecutionResultType execute() throws Exception {
        // Get driver initialized by TestProject Agent
        // No need to specify browser type, it can be done later via UI
        WebDriver driver = this.getWebDriver();

        // Make sure that we are on the right page
        if (!driver.getTitle().contains("TestProject Demo")) {
            this.setMessage("Current page is not TestProject Demo page");
            return ExecutionResultType.Failed;
        }

        // Enter first and last names in appropriate fields
        driver.findElement(By.id("firstName")).sendKeys(firstName);
        driver.findElement(By.id("lastName")).sendKeys(lastName);

        // Setup the expected full name that should appear
        String expectedFullName = firstName + " " + lastName;

        // Add person to list
        driver.findElement(By.id("addPerson")).click();

        // Get person full name
        fullName = driver.findElement(By.xpath("//*[@id='personsList']/li[@class = 'list-group-item']"))
                .getText();

        // Assign test result
        boolean result = fullName.contains(expectedFullName);

        if (result) {
            // Setting the general test message
            setMessage(String.format("The actual full name is '%s' and it does contain '%s'", fullName,
                    expectedFullName));

            // Assert success for the entire test
            return ExecutionResultType.Passed;
        }
        else {
            // Setting the general test message
            setMessage(String.format("The actual full name is '%s' which does not contain '%s'", fullName, expectedFullName));

            // Assert failure for the entire test
            return ExecutionResultType.Failed;
        }
    }

}

