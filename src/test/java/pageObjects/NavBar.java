package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class NavBar extends BasePage {

    @FindBy(css = ".app-header__logo")
    private WebElement shichorBtn;
    @FindBy(css = "div.app-header__desktop > nav > div > a:nth-child(1)")
    private WebElement destinationsBtn;
    @FindBy(css = "#email")
    private WebElement emailFiled;
    @FindBy(css = "[type='password']")
    private WebElement passwordFiled;
    @FindBy(css = ".btn-blue.btn-fluid.btn")
    private WebElement loginBtn;
    @FindBy(css = "div.nav-right__user > div > div > div > button")
    private WebElement logOutBtn;
    @FindBy(css = ".btn-blue.btn-small.btn")
    private WebElement logOutOkBtn;
    @FindBy(css = "div.nav-right__user > button")
    private WebElement loginBtn2;
    @FindBy(css = ".form-error-message")
    private WebElement errorMsgInvalidEmail;
    @FindBy(css = "div.sign-in-form__fields > div:nth-child(1) > div.form-error-message")
    private WebElement errorMsgRequiredEmail;
    @FindBy(css = "div.input.input--password > div.form-error-message")
    private WebElement errorMsgRequiredPassword;
    @FindBy(css = "[href='#close']")
    private WebElement closeLoginBtn;
    @FindBy(css = ".form-error")
    private WebElement errorMsgLoginFiled;
    @FindBy(css = "div.nav-right__user > button")
    private WebElement signInBtn;
    @FindBy(css = ".nav-right__user-profile")
    private WebElement profileBtn;


    public NavBar(WebDriver driver) {
        super(driver);
    }

    public void clickDestinationsBtn() {
        click(destinationsBtn);
    }

    public void clickShichorBtn() {
        click(shichorBtn);
        sleep(1000);
    }

    public String getLoginBtn2Name() {
        return loginBtn2.getText();
    }

    public String getErrorMsgInvalidEmail() {
        waitTo(errorMsgInvalidEmail);
        return getText(errorMsgInvalidEmail);
    }

    public String getErrorMsgLoginFailed() {
        waitTo(errorMsgLoginFiled);
        return getText(errorMsgLoginFiled);
    }

    public String getErrorMsgRequiredEmail() {
        return getText(errorMsgRequiredEmail);
    }

    public String getErrorMsgRequiredPassword() {
        return getText(errorMsgRequiredPassword);
    }

    public void closeLogin() {
        waitTo(closeLoginBtn);
        click(closeLoginBtn);
    }

    public void login(String email, String password) {
        click(signInBtn);
        fillText(emailFiled, email);
        fillText(passwordFiled, password);
        click(loginBtn);
    }

    public void waitErrorRequiredEmail() {
        waitTo(errorMsgRequiredEmail);
    }

    public void waitErrorRequiredEPassword() {
        waitTo(errorMsgRequiredPassword);
    }

    public void fillEmailAndPassword(String email, String password) {
        waitTo(emailFiled);
        fillText(emailFiled, email);
        fillText(passwordFiled, password);
        click(loginBtn);
    }

    public void logout() {
        moveTo(profileBtn);
        waitTo(logOutBtn);
        click(logOutBtn);
        waitTo(logOutOkBtn);
        click(logOutOkBtn);
        waitTo(loginBtn2);
    }
}


