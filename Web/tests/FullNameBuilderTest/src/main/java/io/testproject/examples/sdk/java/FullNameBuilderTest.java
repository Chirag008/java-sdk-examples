package io.testproject.examples.sdk.java;

import io.testproject.java.enums.ExecutionResultType;
import io.testproject.java.sdk.generated.codeblocks.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FullNameBuilderTest extends Test {

    @Override
    protected ExecutionResultType execute() throws Exception {
        // Get driver initialized by TestProject Agent
        // No need to specify browser type, it can be done later via UI
        WebDriver driver = this.getWebDriver();

        // Navigate to TestProject Demo website
        driver.navigate().to("https://example.testproject.io/web/");

        // Find First name element and type 'John'
        WebElement firstName = driver.findElement(By.id("firstName"));
        firstName.sendKeys("John");

        // Find Last name element and type 'Smith'
        WebElement lastName = driver.findElement(By.id("lastName"));
        lastName.sendKeys("Smith");

        // Find Full Name element
        WebElement fullName = driver.findElement(By.id("fullName"));

        // Verify that Full Name equals a concatenation of First and Last
        String fullNameText = fullName.getText();
        if (fullNameText.equals("John Smith")) {
            return ExecutionResultType.Passed;
        } else {
            return ExecutionResultType.Failed;
        }
    }
}

