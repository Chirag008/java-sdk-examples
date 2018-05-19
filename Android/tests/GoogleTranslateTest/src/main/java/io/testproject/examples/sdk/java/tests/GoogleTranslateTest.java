package io.testproject.examples.sdk.java.tests;

import io.testproject.examples.sdk.java.TestParameters;
import io.testproject.examples.sdk.java.steps.*;
import io.testproject.java.annotations.TestAnnotation;
import io.testproject.java.annotations.TestParameterAnnotation;
import io.testproject.java.enums.ExecutionResultType;
import io.testproject.java.enums.ParameterDirection;
import io.testproject.java.enums.TakeScreenshotConditionType;
import io.testproject.java.sdk.framework.FunctionalTest;
import io.testproject.java.sdk.framework.FunctionalTestBuilder;
import io.testproject.java.sdk.framework.TestBuilder;
import io.testproject.java.sdk.framework.steps.ResetAppStep;
import io.testproject.java.sdk.framework.steps.SleepStep;
import io.testproject.java.sdk.generated.codeblocks.Test;

@TestAnnotation(
        name = "Translate text using Google Translate",
        summary = "Translates text using Google Translate and reverses translation comparing results",
        description = "Translate {{sourceText}} from {{sourceTextLanguage}} to {{translatedTextLanguage}}, reverse it and expect to match")
public class GoogleTranslateTest extends Test implements FunctionalTest {

    @TestParameterAnnotation(
            direction = ParameterDirection.INPUT,
            description = "Source text that needs to be translated",
            defaultValue = "Hello World!")
    private String sourceText;

    @TestParameterAnnotation(
            direction = ParameterDirection.INPUT,
            description = "Source text language",
            defaultValue = "English")
    private String sourceTextLanguage;

    @TestParameterAnnotation(
            direction = ParameterDirection.INPUT,
            description = "Translated text language",
            defaultValue = "Russian")
    private String translatedTextLanguage;

    public GoogleTranslateTest() {
    }

    public GoogleTranslateTest(String sourceText, String sourceTextLanguage, String translatedTextLanguage) {
        this.sourceText = sourceText;
        this.sourceTextLanguage = sourceTextLanguage;
        this.translatedTextLanguage = translatedTextLanguage;
    }

    @Override
    protected ExecutionResultType execute() throws Exception {
        TestBuilder testBuilder = new TestBuilder(this);
        ExecutionResultType result = addReusableSteps(testBuilder).run();

        if (result.equals(ExecutionResultType.Passed)) {
            setMessage("Test completed successfully");
        } else {
            setMessage("Test failed, see failed steps for details.");
        }

        return result;
    }

    @Override
    public FunctionalTestBuilder addReusableSteps(FunctionalTestBuilder functionalTestBuilder) {

        return functionalTestBuilder
                .addStep(new ResetAppStep())
                .addStep(new SetupStep())
                .addStep(new HomeStep())
                .addStep(new SelectSourceLanguageStep(sourceTextLanguage))
                .addStep(new SelectTargetLanguageStep(translatedTextLanguage))
                .addStep(new TypeSourceTextStep(sourceText))
                .addStep(new SubmitInputStep())
                .addStep(new GetTranslatedTextStep(TestParameters.TRANSLATED_TEXT), TakeScreenshotConditionType.Always)

                .addStep(new ResetAppStep())
                .addStep(new SetupStep())
                .addStep(new HomeStep())
                .addStep(new SelectSourceLanguageStep(translatedTextLanguage))
                .addStep(new SelectTargetLanguageStep(sourceTextLanguage))
                .addStep(new TypeSourceTextStep())
                .addStep(new SubmitInputStep())
                .addStep(new GetTranslatedTextStep(TestParameters.REVERSE_TRANSLATED_TEXT), TakeScreenshotConditionType.Always)

                .addStep(new CompareResultsStep(sourceText));
    }
}

