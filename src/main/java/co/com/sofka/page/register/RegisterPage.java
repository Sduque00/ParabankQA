package co.com.sofka.page.register;

import co.com.sofka.model.register.RegisterModel;
import co.com.sofka.page.common.CommonActionOnPages;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;



public class RegisterPage extends CommonActionOnPages {
    private static final Logger LOGGER = Logger.getLogger(RegisterPage.class);
    private RegisterModel registerModel;
    private static final String MODEL_NULL_MESSAGE = "El modelo del formulario es nulo.";

    //For input test cases.

    @FindBy(id = "customer.firstName")
    @CacheLookup
    private WebElement name;

    @FindBy(id = "customer.lastName")
    @CacheLookup
    private WebElement lastName;

    @FindBy(id = "customer.address.street")
    @CacheLookup
    private WebElement address;

    @FindBy(id = "customer.address.city")
    @CacheLookup
    private WebElement city;

    @FindBy(id = "customer.address.state")
    @CacheLookup
    private WebElement state;

    @FindBy(id = "customer.address.zipCode")
    @CacheLookup
    private WebElement zipCode;

    @FindBy(id = "customer.phoneNumber")
    @CacheLookup
    private WebElement phone;

    @FindBy(id = "customer.ssn")
    @CacheLookup
    private WebElement SSN;

    @FindBy(id = "customer.username")
    @CacheLookup
    private WebElement userName;

    @FindBy(id = "customer.password")
    @CacheLookup
    private WebElement password;

    @FindBy(id = "repeatedPassword")
    @CacheLookup
    private WebElement confirm;

    @FindBy(xpath = "//div[@id='loginPanel']/p[2]/a")
    @CacheLookup
    private WebElement register;

    @FindBy(xpath = "//input[@value='Register']")
    @CacheLookup
    private WebElement submit;


    //For Assertion test case.

    @FindBy(css = ".title")
    @CacheLookup
    private WebElement assertionRegister;

    @FindBy(id = "customer.username.errors")
    @CacheLookup
    private WebElement assertionUserNameExist;


    public RegisterPage(WebDriver driver, RegisterModel registerModel) {
        super(driver);
        pageFactoryInitElement(driver, this);
        this.registerModel = registerModel;
    }

    public RegisterPage(WebDriver driver, RegisterModel registerModel, int seconds) {
        super(driver, seconds, false);
        pageFactoryInitElement(driver, this);
        this.registerModel = registerModel;
    }

    //Funcionalidades del page.
    public void fillRegisterForm(){
        clickOn(register);

        clearOn(name);
        typeOn(name, registerModel.getName());

        clearOn(lastName);
        typeOn(lastName, registerModel.getLastName());

        clearOn(address);
        typeOn(address, registerModel.getAddress());

        clearOn(city);
        typeOn(city, registerModel.getCity());

        clearOn(state);
        typeOn(state, registerModel.getState());

        clearOn(zipCode);
        typeOn(zipCode, registerModel.getZipCode());

        clearOn(phone);
        typeOn(phone, registerModel.getPhone());

        clearOn(phone);
        typeOn(SSN, registerModel.getSSN());

        clearOn(userName);
        typeOn(userName, registerModel.getUserName());

        clearOn(password);
        typeOn(password, registerModel.getPassword());

        clearOn(confirm);
        typeOn(confirm, registerModel.getConfirm());

        doSubmit(submit);


    }

    public String isRegistrationDone(){

        String registerDone = getText(assertionRegister).trim();
        return registerDone;
    }

    public String isUserNameExist(){

        String UserNameExist = getText(assertionUserNameExist).trim();
        return UserNameExist;
    }




}


