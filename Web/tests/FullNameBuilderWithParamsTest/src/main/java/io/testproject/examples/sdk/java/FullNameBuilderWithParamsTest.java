package io.testproject.examples.sdk.java;

import io.testproject.java.annotations.TestAnnotation;
import io.testproject.java.annotations.TestParameterAnnotation;
import io.testproject.java.enums.ExecutionResultType;
import io.testproject.java.enums.ParameterDirection;
import io.testproject.java.sdk.generated.codeblocks.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

@TestAnnotation(
        name = "Verify full name generation",
        summary = "Inputs first and last name, making sure that full name is updated accordingly",
        description = "Input {{firstName}} and {{lastName}}, verify Full Name is {firstName}} {{lastName}}'")
public class FullNameBuilderWithParamsTest extends Test {

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

    private WebDriver driver;

    @Override
    protected ExecutionResultType execute() throws Exception {
        // Get driver initialized by TestProject Agent
        // No need to specify browser type, it can be done later via UI
        driver = this.getWebDriver();

        // Assign the destination URL
        String URL = "https://example.testproject.io/web/";

        // Navigate to the provided URL
        driver.navigate().to(URL);

        // Asserting the navigation step and validating that the navigation was a success
        this.assertThat(String.format("Navigate to %s", URL)).assertEquals(URL, driver.getCurrentUrl(), "Didn't get where I wanted");

        // Enter first and last names in appropriate fields
        driver.findElement(By.id("firstName")).sendKeys(firstName);
        driver.findElement(By.id("lastName")).sendKeys(lastName);

        // Setup the expected full name that should appear
        String expectedFullName = firstName + " " + lastName;

        // Add person to list
        driver.findElement(By.id("addPerson")).click();

        // Get person full name
        String fullName = driver.findElement(By.xpath("//*[@id='personsList']/li[@class = 'list-group-item']")).getText();

        // Assign test result
        boolean result = fullName.contains(expectedFullName);

        // Asserting the full name validation step
        this.assertThat(String.format("Check that full name in page contains '%s'", expectedFullName)).assertTrue(result, "The full name does not contain the expected text");

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

