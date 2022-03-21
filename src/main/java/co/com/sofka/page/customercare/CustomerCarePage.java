package co.com.sofka.page.customercare;

import co.com.sofka.model.customercare.CustomerCareModel;
import co.com.sofka.page.common.CommonActionOnPages;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class CustomerCarePage extends CommonActionOnPages {
    private static final Logger LOGGER = Logger.getLogger(CustomerCarePage.class);
    private CustomerCareModel customerCareModel;
    private static final String MODEL_NULL_MESSAGE = "El modelo del formulario es nulo.";

    //For input test cases.

    @FindBy(id = "name")
    @CacheLookup
    private WebElement name;

    @FindBy(id = "email")
    @CacheLookup
    private WebElement email;

    @FindBy(id= "phone")
    @CacheLookup
    private WebElement phone;

    @FindBy(id = "message")
    @CacheLookup
    private WebElement message;

    @FindBy(linkText = "Contact Us")
    @CacheLookup
    private WebElement contactUs;

    @FindBy(xpath ="//input[@value='Send to Customer Care']")
    @CacheLookup
    private WebElement submit;


    //For Assertion test case.

    @FindBy(css = "#rightPanel > p:nth-child(2)")
    @CacheLookup
    private WebElement assertionCustomerCare;

    @FindBy(id = "email.errors")
    @CacheLookup
    private WebElement assertionEmailError;


    public CustomerCarePage(WebDriver driver, CustomerCareModel customerCareModel) {
        super(driver);
        pageFactoryInitElement(driver, this);
        this.customerCareModel = customerCareModel;
    }

    public CustomerCarePage(WebDriver driver, CustomerCareModel customerCareModel, int seconds) {
        super(driver, seconds, false);
        pageFactoryInitElement(driver, this);
        this.customerCareModel = customerCareModel;
    }

    //Funcionalidades del page.

    public void fillCustomerCareForm(){
        clickOn(contactUs);

        clearOn(name);
        typeOn(name,customerCareModel.getName());

        clearOn(email);
        typeOn(email, customerCareModel.getEmail());

        clearOn(phone);
        typeOn(phone, customerCareModel.getPhone());

        clearOn(message);
        typeOn(message, customerCareModel.getMessage());

        doSubmit(submit);

    }

    public void fillCustomerCareEmailEmpty(){
        clickOn(contactUs);

        clearOn(name);
        typeOn(name,customerCareModel.getName());

        clearOn(phone);
        typeOn(phone, customerCareModel.getPhone());

        clearOn(message);
        typeOn(message, customerCareModel.getMessage());

        doSubmit(submit);

    }

    public String isCustomerCareDone(){

        String customerCareDone = getText(assertionCustomerCare).trim();
        return customerCareDone;
    }

    public String isEmailEmpty(){

        String emailEmpty = getText(assertionEmailError).trim();
        return emailEmpty;
    }
}
