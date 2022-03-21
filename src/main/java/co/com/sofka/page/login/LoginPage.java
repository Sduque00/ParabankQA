package co.com.sofka.page.login;

import co.com.sofka.model.login.LoginModel;
import co.com.sofka.page.common.CommonActionOnPages;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends CommonActionOnPages {
    private static final Logger LOGGER = Logger.getLogger(LoginPage.class);
    private LoginModel loginModel;
    private static final String MODEL_NULL_MESSAGE = "El modelo del formulario es nulo.";

    //For input test cases.

    @FindBy(xpath = "//input[@name='username']")
    @CacheLookup
    private WebElement userName;

    @FindBy(xpath = "//input[@name='password']")
    @CacheLookup
    private WebElement password;

    @FindBy(css = ".button:nth-child(1)")
    @CacheLookup
    private WebElement logIn;


    //For Assertion test case.

    @FindBy(css = ".smallText")
    @CacheLookup
    private WebElement assertionLogin;

    @FindBy(css =".title")
    @CacheLookup
    private WebElement assertionPassIncorrect;



    public LoginPage(WebDriver driver, LoginModel loginModel) {
        super(driver);
        pageFactoryInitElement(driver, this);
        this.loginModel = loginModel;
    }

    public LoginPage(WebDriver driver, LoginModel loginModel, int seconds) {
        super(driver, seconds, false);
        pageFactoryInitElement(driver, this);
        this.loginModel = loginModel;
    }


    //Funcionalidades del page.

    public void fillLoginForm(){

        clearOn(userName);
        typeOn(userName, loginModel.getUserName());

        clearOn(password);
        typeOn(password, loginModel.getPassword());

        doSubmit(logIn);

    }

    public String isLoginDone(){

        String loginDone = getText(assertionLogin).trim();
        return loginDone;
    }

    public String isPasswordIncorrect(){

        String passwordIncorrect = getText(assertionPassIncorrect).trim();
        return passwordIncorrect;
    }

}
