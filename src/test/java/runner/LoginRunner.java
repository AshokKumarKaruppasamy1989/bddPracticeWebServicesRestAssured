package runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/resources//features//GoogleAPI.feature",
glue = "stepDefinition",
monochrome = true,
plugin = {"pretty",
"html:target/cucumber-reports/cucumber.html",
"json:target/cucumber-reports/cucumber.json"})

public class LoginRunner extends AbstractTestNGCucumberTests {
	
	
}
