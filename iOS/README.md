# TestProject Java SDK - Quick Start for iOS

TestProject provides a unified test automation SDK with support for Android, iOS and Web applications by utilizing the open-source Selenium and Appium frameworks.

TestProject is OS agnostic and can run on Windows, Linux or Mac. 
It is a full stack automation framework with capabilities that allow automation test management, remote and local test execution, job scheduling, reporting dashboards, collaboration and more.

## How to start

### iOS Configuration

Follow the instructions on your TestProject account settings page and complete [iOS configuration](https://app.testproject.io/#/settings/ios) using you Apple Developer account.

### TestProject Agent

To kick-off automation development with TestProject, it is necessary to have an active TestProject account and the TestProject Agent installed. 
TestProject's Agent is a cross-platform desktop application, allowing you to create, debug and execute your test automation locally. 
TestProject Agent can be downloaded from [Agents](https://app.testproject.io/#/agents) page.

### TestProject Java SDK

This document describes the bare minimum steps to start developing tests using the Java SDK. 
You can download TestProject SDK for Java from the [Developers](https://app.testproject.io/#/developers) page and reference it in your project.

#### Reference in Gradle

Add the following line under the *dependencies* section in the *build.gradle* file of your project.

```gradle
dependencies {
    // Update the location of the sdk as required
    compile files('io.testproject.sdk.java.jar')
}
```

## Test Development

### Implement an automated test

The best way to start developing automated tests with TestProject is by reviewing a basic example source code [FullNameBuilderTest](tests/FullNameBuilderTest). This code represents a test that is executed on [TestProject's Demo](https://github.com/testproject-io/ios-demo-app) App for iOS. It inputs person's first and last names into an appropriate UI elements, expecting a full name label to change it's value accordingly.

#### Test class

In order to build a test that can be used in TestProject, the class has to extend and implement the *Test* class. This indicates to the platform that it is a Test that can be executed.

```java
public class FullNameBuilderTest extends Test
```

The logic of your Test should be implemented by overriding the *execute()* method which is an abstract method of the Test class. 

```java
@Override
protected ExecutionResultType execute() throws Exception
```

This method will be invoked by the platform to run the Test. You can create additional methods to split the logic but the *execute()* is the entry point of the test. The *execute()* method returns *ExecutionResultType* response which can be **Passed** or **Failed**.

#### Test logic

The following line of code retrieves a driver to automate the browser.

```java
// Get driver initialized by TestProject Agent
IOSDriver<IOSElement> driver = this.getIOSDriver(IOSElement.class);
```

The following code locates the *firstName* and *lastName* elements and uses the *sendKeys(keys)* method to simulate text typing in the element.

```java
// Find First name element and type 'John'
IOSElement firstName = driver.findElement(By.id("firstName"));
firstName.sendKeys("John");

// Find Last name element and type 'Smith'
IOSElement lastName = driver.findElement(By.id("lastName"));
lastName.sendKeys("Smith");
```

The following code locates the *fullName* element, retrieves its text and validates the result ('*fullName*' = '*firstName* *lastName*'). If the *fullName* text is as expected, method will return *ExecutionResultType.Passed*. In case the *fullName* has some other value, method returns *ExecutionResultType.Failed*.

```java
// Find Full Name element
IOSElement fullName = driver.findElement(By.id("fullName"));

// Verify that Full Name equals a concatenation of First and Last
String fullNameText = fullName.getText();
if (fullNameText.equals("John Smith")) {
    return ExecutionResultType.Passed;
} else {
    return ExecutionResultType.Failed;
}
```

### Debugging / Running

#### Runner Project

To debug or run the test locally, you need a [Runner](tests/FullNameBuilderTestRunner) project.

1. Create a new project.
1. Reference [TestProject SDK](https://app.testproject.io/#/developers).
1. Reference the project of your test.
1. Create a runnable class.
1. Invoke your test from the **Runner** project.

#### Development Token

Debugging or running a test locally requires communication with a TestProject Agent (since it is the execution engine). Agent will authorize development session request only when a development token is presented, which can be easily obtained from the [Developers](https://app.testproject.io/#/developers) page.

You should replace the placeholder with your own developer token:

```java
// Setting the development token
private final static String devToken = "YOUR_DEV_TOKEN_GOES_HERE";
```

That's it - now you can debug or run the **Runner** project to debug or run your test.

> After the test is uploaded to your TestProject account, we will run it for you (no **Runner** required).

### Using parameters and assertions in your tests

Let's make our example more generic. For that we will use parameters. To add parameters to your test, you simply need to add fields with relevant annotations. In addition, we will create assertions to separate the different stages of the test (each assertion will appear as a separate step in the future execution reports). 

Example full source code is available here: [FullNameBuilderWithParamsTest](tests/FullNameBuilderWithParamsTest).

#### Test Annotations

TestProject SDK provides annotations to describe the test and it's parameters:

 1. The ***TestAnnotation*** is used to better describe the Test and define how it will appear later in TestProject UI:
    * **name** - The name of the test (if omitted, the name of the class will be used).
    * **summary** - A quick summary of what the test does.
    * **description** - A description of the test which is shown in various places in TestProject platform (e.g. reporting dashboard). The description may contain placeholders {{propertyName}} that will be changed dynamically according to test parameters.
 1. The ***TestParameterAnnotation*** is used to better describe your Test inputs and outputs, in the example above there are two inputs - *url* and *expectedTitle*.
    * **description** - The description of the parameter
    * **direction** - Defines the parameter as an *input* or an *output* parameter. An *input* parameter will receive values when the test is executed while the *output* parameter value will be retrieved at the end of test execution (and can be used in other places later on in the automation scenario).

You can debug or run this example using it's [Runner](tests/FullNameBuilderWithParamsTestRunner) project.

## Addon development

Much like Tests you can develop custom Addons to extend TestProject and shape your automated testing solution for your needs. An Addon is a set of Actions (one or more) where each Action does a specific task, a common Addon scenario will be to extend basic set of Actions on complicated UI elements or make wrappers for user defined API. Once created, Actions can be used to design steps of automated tests.

### Addon Manifest

To start developing an Addon a manifest file is required. The manifest is a descriptor of your Addon, it contains a unique GUID for the addon and a list of required permissions. Create an Addon in the [Addons](https://app.testproject.io/#/addons/account) screen. Download the generated manifest and place it in your project resources folder.

### Implement the Addon

Lets create our first Addon. It will have an Action **FullNameBuilderAction** that fills in first and last names in [TestProject's Demo](https://github.com/testproject-io/ios-demo-app) App for iOS.

Example full source code is available here: [FullNameBuilderAddon](addons/FullNameBuilderAddon).

> NOTE: Each class that extends the **Action** class and has the ***ActionAnnotation*** will appear as an action of the Addon and will be available for use in TestProject.

#### The Action class

In order to build an action that can be used in TestProject, the class has to inherit the *Action* class. This indicates to the platform that it is an action that can be executed in addition to providing some base capabilities (like driver retrieval). The class is also decorated with the *@ActionAnnotation* annotation to provide relevant information about the action (more info on annotations can be found later in this document).

```java
@ActionAnnotation(
        name = "Verify full name generation",
        summary = "Inputs first and last name, making sure that full name is updated accordingly",
        description = "Input {{firstName}} and {{lastName}}, verify Full Name is {firstName}} {{lastName}}'",
        version = "0.1")

public class FullNameBuilderAction extends Action
```

##### Action parameters

The action below has several parameters to make it more generic.

```java
@TestParameterAnnotation(direction = ParameterDirection.INPUT,	description = "First name")
private String firstName;

@TestParameterAnnotation(direction = ParameterDirection.INPUT,	description = "Last name")
private String lastName;

@TestParameterAnnotation(direction = ParameterDirection.OUTPUT, description = "Full name")
public String fullName;
```

The logic of your action should be implemented by overriding the *execute()* method which is an abstract method of the Action class. This method will be invoked by the platform to run the action. You can create additional methods to split the logic but the *execute()* is the entry point of the action. The *execute()* method returns *ExecutionResultType* response which can be **Passed** or **Failed**.

```java
@Override
protected ExecutionResultType execute() throws Exception
```

#### Action logic

The following line of code retrieves the driver.

```java
// Get driver initialized by TestProject Agent
IOSDriver<IOSElement> driver = this.getIOSDriver(IOSElement.class);
```

Following code locates the relevant input fields, enters the data:

```java
// Enter first and last names in appropriate fields
driver.findElement(By.id("firstName")).sendKeys(firstName);
driver.findElement(By.id("lastName")).sendKeys(lastName);
```

> Keys are the action parameters and not hard coded values.

Next we will retrieve the full name label text:

```java
// Get person full name
this.fullName = driver.findElement(By.id("fullName")).getText();
```

> Retrieved value is stored in fullName parameter and will be available as test output.

We have to make sure that full name equals first name and last name concatenation:

```java
// Assign test result
boolean result = fullName.contains(expectedFullName);
```

The final section of the code sets a message for the action result and returns *ExecutionResultType* accordingly.

```java
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
```

#### Action Annotations

Code above uses annotations to describe the action.

 1. The ***ActionAnnotation*** is used to better describe your action and define how it will appear later in TestProject UI:
    * **name** - The name of the action (if omitted, the name of the class will be used).
    * **summary** - A quick summary of what the action does.
    * **description** - A description of the test which is shown in various places in TestProject platform (reports for example). The description can use placeholders {{propertyName}} do dynamically change the text according to test properties.
    * **version** - A version string which is used for future reference.
 1. The ***ActionParameterAnnotation*** is used to better describe your action's inputs and outputs, in the example above there are two parameters - *question* and *answer*.
    * **description** - The description of the parameter
    * **direction** - Defines the parameter as an *input* or an *output* parameter. An *input* parameter will able to receive values when it is being executed while the *output* parameter value will be retrieved at the end of test execution (and can be used in other places later on in the automation scenario).

> NOTE: Unlike tests, actions cannot use assertions because an action is a single generic reusable unit.

#### Runner

You can debug or run this action using [FullNameBuilderAddonRunner](addons/FullNameBuilderAddonRunner) project. 

### Element Actions

Actions can be element based, when their scope is limited to operations on a specific element and not the whole DOM. This allows creating smart crowd based addons for industry common elements and libraries.

*FullNameBuilderElementAction* is an example of an Element based Action and it's the second Action in the [FullNameBuilderAddon](addons/FullNameBuilderAddon).

```java
// Get element provided to action
IOSElement element = this.getElement(IOSElement.class);
```

Code above receives an element that was provided to the Action.

#### Runner

You can debug or run this action using [FullNameBuilderAddonRunner](addons/FullNameBuilderAddonRunner) project. Note the following lines:

```java
// Running action
runner.run(action, By.id("personsContainer"));
```

When the action is debugged using a Runner project, it's important to pass the element specification into the action. After the Addon is uploaded to Testproject this can be done via UI.

## Crowd Code / Addon Proxy

One of the greatest features of the TestProject environment is the ability to execute a code written by someone else. It can be your account colleagues writing actions that you can reuse, or TestProject community users.

Developer must download a binary file with the proxy class for the Test he wants to execute and reference it (e.g. in *build.gradle* dependencies section).

Assuming your account member uploaded the *FullNameBuilderAddon* to TestProject and you want to reuse it's code your Test, you can download it's proxy binary then create an instance from one of it's actions:

```java
FullNameBuilderActionProxy executerProxy = new FullNameBuilderActionProxy();
```

You can execute the proxy via an inherited method *executeProxy* from your Test:

```java
StepExecutionResult result = executeProxy(executerProxy);
```

## Packaging

In order to upload your Addons or Tests to TestProject, you have to package it. For Java it's quite simple, just export your code as an uber JAR file with dependencies, excluding TestProject SDK.

> ***Do not reference Selenium or Appium in your project, conflicts will occur and your project won't compile***

### Packaging with Gradle

Edit your **build.gradle** file and create or add to an existing *configurations* section:

```gradle
configurations {
    tpsdk
    compile.extendsFrom tpsdk
}
```

Add a *jar* section to describe the jar build process:

```gradle
// JAR Task
jar {
    archiveName "${rootProject.name}-${version}.jar"
    dependsOn configurations.runtime
    from {
        // Removes TestProject SDK from the final jar file
        (configurations.runtime - configurations.tpsdk).collect {
            it.isDirectory() ? it : zipTree(it)
        }
    }
}
```

Use *tpsdk* configuration to reference TestProject SDK dependency and *compile* configuration for any other dependencies:

```gradle
dependencies {
    // Any 3rd party dependencies
    compile some_library.jar

    // TestProject SDK
    // Update the location of the sdk as required
    tpsdk files('io.testproject.sdk.java.jar') 
}
```

Exported JAR file will contain your code and other dependencies.