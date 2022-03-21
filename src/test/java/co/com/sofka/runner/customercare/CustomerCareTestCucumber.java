package co.com.sofka.runner.customercare;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        publish = true,
        features = {"src/test/resources/features/customercare/customercare.feature"},
        glue = "co.com.sofka.stepdefinition.customercare"

)

public class CustomerCareTestCucumber {
}
