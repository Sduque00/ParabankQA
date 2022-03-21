package co.com.sofka.stepdefinition.customercare;

import co.com.sofka.model.customercare.CustomerCareModel;
import co.com.sofka.page.customercare.CustomerCarePage;
import co.com.sofka.stepdefinition.setup.WebUI;
import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Assertions;

import static co.com.sofka.util.Seconds.SEVEN_SECONDS;

public class CustomerCareStepDefinition extends WebUI {

    private static final Logger LOGGER = Logger.getLogger(CustomerCareStepDefinition.class);

    private CustomerCareModel customerCareModel;
    private CustomerCarePage customerCarePage;

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

    //CustomCare exitoso
    @When("el usuario ingresa los campos del formulario y confirma la accion")
    public void elUsuarioIngresaLosCamposDelFormularioYConfirmaLaAccion() {
        Faker faker = Faker.instance();
        try {
            customerCareModel = new CustomerCareModel();
            customerCareModel.setName(faker.name().fullName());
            customerCareModel.setEmail(faker.internet().emailAddress());
            customerCareModel.setPhone("3645964616");
            customerCareModel.setMessage("*Mensaje PQRS para customer care");

            customerCarePage = new CustomerCarePage(driver,customerCareModel);
            customerCarePage.fillCustomerCareForm();

        } catch (Exception exception){
            quiteDriver();
            Assertions.fail(exception.getMessage(), exception);
            LOGGER.error(exception.getMessage(), exception);
        }

    }
    @Then("el sistema debera mostrar un mensaje de sulicitud enviada")
    public void elSistemaDeberaMostrarUnMensajeDeSulicitudEnviada() {
        Assertions.assertEquals("Thank you " + customerCareModel.getName(), customerCarePage.isCustomerCareDone());
        quiteDriver();

    }

    //Customer care fallido email vacio

    @When("el usuario no ingresa el email")
    public void elUsuarioNoIngresaElEmail() {
        Faker faker = Faker.instance();
        try {
            customerCareModel = new CustomerCareModel();
            customerCareModel.setName(faker.name().fullName());
            customerCareModel.setPhone("3645964616");
            customerCareModel.setMessage("*Mensaje PQRS para customer care");

            customerCarePage = new CustomerCarePage(driver,customerCareModel, SEVEN_SECONDS.getValue());
            customerCarePage.fillCustomerCareEmailEmpty();

        } catch (Exception exception){
            quiteDriver();
            Assertions.fail(exception.getMessage(), exception);
            LOGGER.error(exception.getMessage(), exception);
        }

    }
    @Then("el sistema debera mostrar por pantalla un mensaje de email requerido")
    public void elSistemaDeberaMostrarPorPantallaUnMensajeDeEmailRequerido() {
        Assertions.assertEquals("Email is required.", customerCarePage.isEmailEmpty());
        quiteDriver();

    }
}
