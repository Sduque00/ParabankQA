package co.com.sofka.stepdefinition.login;

import co.com.sofka.model.login.LoginModel;
import co.com.sofka.page.login.LoginPage;
import co.com.sofka.stepdefinition.setup.WebUI;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;

import static co.com.sofka.util.Seconds.SEVEN_SECONDS;

public class LoginStepDefinition extends WebUI {

    private static final Logger LOGGER = Logger.getLogger(LoginStepDefinition.class);

    private LoginModel loginModel;
    private LoginPage loginPage;


    @Given("que el usuario se encuentra en la pagina web")
    public void queElUsuarioSeEncuentraEnLaPaginaWeb() {
        try {
            setUpLog4j2();
            setUpWebDriver();
            generalSetUp();


        } catch (Exception exception){
            quiteDriver();
            Assertions.fail(exception.getMessage(), exception);
            LOGGER.error(exception.getMessage(), exception);

        }
    }

    //Login exitoso

    @When("el usuario ingresa usuario y clave y confirma la accion")
    public void elUsuarioIngresaUsuarioYClaveYConfirmaLaAccion() {
        try {
            loginModel = new LoginModel();
            loginModel.setUserName("sduke0");
            loginModel.setPassword("123456");

            loginPage = new LoginPage(driver,loginModel);
            loginPage.fillLoginForm();

        } catch (Exception exception){
            quiteDriver();
            Assertions.fail(exception.getMessage(), exception);
            LOGGER.error(exception.getMessage(), exception);
        }

    }

    @Then("el sistema debera mostrar un mensaje de bienvenida.")
    public void elSistemaDeberaMostrarUnMensajeDeBienvenida() {
        Assertions.assertEquals("Welcome Samuel Duque", loginPage.isLoginDone());
        quiteDriver();

    }

    //Login fallido contraseña incorrecta
    @When("el usuario ingresa una clave incorrecta")
    public void elUsuarioIngresaUnaClaveIncorrecta() {
        try {
            loginModel = new LoginModel();
            loginModel.setUserName("sduke0");
            loginModel.setPassword("123abc");

            loginPage = new LoginPage(driver,loginModel, SEVEN_SECONDS.getValue());
            loginPage.fillLoginForm();

        } catch (Exception exception){
            quiteDriver();
            Assertions.fail(exception.getMessage(), exception);
            LOGGER.error(exception.getMessage(), exception);
        }

    }

    @Then("el sistema debera mostrar un mensaje de clave incorrecta")
    public void elSistemaDeberaMostrarUnMensajeDeClaveIncorrecta() {
        Assertions.assertEquals("Error!", loginPage.isPasswordIncorrect());
        quiteDriver();

    }

}
