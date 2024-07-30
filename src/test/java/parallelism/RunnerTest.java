package parallel;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(glue={"parallel"}, features = "C:\\Users\\Admin\\IdeaProjects\\Assignment4_C\\src\\test\\java\\parallel", plugin = { "pretty", "html:target/site/cucumber-pretty",
        "json:target/cucumber.json" })
public class RunnerTest {
}