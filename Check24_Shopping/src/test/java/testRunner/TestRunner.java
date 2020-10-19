package testRunner;

import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		features = "feature",
		glue = "stepDefinition",
		format = {"pretty"},
		tags= {"@DesktopTest"},
		monochrome = true
		
 )

public class TestRunner {

}
