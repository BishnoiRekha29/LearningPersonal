package com.pagefact;

import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
@RunWith(Cucumber.class)
@CucumberOptions(
        //path of feature file
        features = "src/test/java/com/pagefact/features/Feature1.feature",
        //path of step definition file
        glue = {"com.pagefact.StepDefinitions"},
        tags="",
        monochrome=true,
        publish = true
)
public class TestRunner {
}
