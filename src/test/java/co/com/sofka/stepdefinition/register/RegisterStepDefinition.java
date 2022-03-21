package co.com.sofka.stepdefinition.register;

import co.com.sofka.model.register.RegisterModel;
import co.com.sofka.page.register.RegisterPage;
import co.com.sofka.stepdefinition.setup.WebUI;
import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;

import static co.com.sofka.util.Seconds.SEVEN_SECONDS;

public class RegisterStepDefinition extends WebUI {

    private static final Logger LOGGER = Logger.getLogger(RegisterStepDefinition.class);

    private RegisterModel registerModel;
    private RegisterPage registerPage;


    @Given("que el usuario se encuentra en la pagina web de registro")
    public void queElUsuarioSeEncuentraEnLaPaginaWebDeRegistro() {
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

    //Registro exitoso
    @When("el usuario ingresa todos los campos del formulario y confirma la accion")
    public void elUsuarioIngresaTodosLosCamposDelFormularioYConfirmaLaAccion() {
        Faker faker = Faker.instance();
        try {
            registerModel = new RegisterModel();
            registerModel.setName(faker.name().firstName());
            registerModel.setLastName(faker.name().lastName());
            registerModel.setAddress(faker.address().fullAddress());
            registerModel.setCity(faker.address().city());
            registerModel.setState(faker.address().state());
            registerModel.setZipCode(faker.address().zipCode());
            registerModel.setPhone(faker.phoneNumber().phoneNumber());
            registerModel.setSSN(faker.idNumber().ssnValid());
            registerModel.setUserName(faker.name().username());
            registerModel.setPassword(faker.internet().password());
            registerModel.setConfirm(registerModel.getPassword());

            registerPage = new RegisterPage(driver,registerModel, SEVEN_SECONDS.getValue());
            registerPage.fillRegisterForm();

        } catch (Exception exception){
            quiteDriver();
            Assertions.fail(exception.getMessage(), exception);
            LOGGER.error(exception.getMessage(), exception);
        }
    }
    @Then("el sistema debera mostrar por pantalla un mensaje de bienvenida.")
    public void elSistemaDeberaMostrarPorPantallaUnMensajeDeBienvenida() {
        Assertions.assertEquals("Welcome " + registerModel.getUserName(), registerPage.isRegistrationDone());
        quiteDriver();
    }


    //Registro fallido por usuario existente

    @When("el usuario ingresa un username existente")
    public void elUsuarioIngresaUnUsernameExistente() {
        Faker faker = Faker.instance();
        try {
            registerModel = new RegisterModel();

            registerModel.setName(faker.name().firstName());
            registerModel.setLastName(faker.name().lastName());
            registerModel.setAddress(faker.address().fullAddress());
            registerModel.setCity(faker.address().city());
            registerModel.setState(faker.address().state());
            registerModel.setZipCode(faker.address().zipCode());
            registerModel.setPhone(faker.phoneNumber().phoneNumber());
            registerModel.setSSN(faker.idNumber().ssnValid());
            registerModel.setUserName("sduke0");
            registerModel.setPassword(faker.internet().password());
            registerModel.setConfirm(registerModel.getPassword());

            registerPage = new RegisterPage(driver,registerModel);
            registerPage.fillRegisterForm();

        } catch (Exception exception){
            quiteDriver();
            Assertions.fail(exception.getMessage(), exception);
            LOGGER.error(exception.getMessage(), exception);
        }
    }

    @Then("el sistema debera mostrar un mensaje de usuario existente")
    public void elSistemaDeberaMostrarUnMensajeDeUsuarioExistente() {
        Assertions.assertEquals("This username already exists." , registerPage.isUserNameExist());
        quiteDriver();
    }


}
