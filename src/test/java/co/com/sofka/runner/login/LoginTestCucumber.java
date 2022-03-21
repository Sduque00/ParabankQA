package co.com.sofka.runner.login;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        snippets = CucumberOptions.SnippetType.CAMELCASE,
        publish = true,
        features = {"src/test/resources/features/login/login.feature"},
        glue = "co.com.sofka.stepdefinition.login"

)
public class LoginTestCucumber {
}


