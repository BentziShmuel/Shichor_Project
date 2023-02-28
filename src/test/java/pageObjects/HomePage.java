package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomePage extends BasePage {

    @FindBy(css = "div.nav-right__user > button")
    private WebElement signInBtn;
    @FindBy(css = "#email")
    private WebElement emailFiled;
    @FindBy(css = "[type='password']")
    private WebElement passwordFiled;
    @FindBy(css = ".btn-blue.btn-fluid.btn")
    private WebElement loginBtn;
    @FindBy(css = ".nav-right__user-profile")
    private WebElement profileBtn;
    @FindBy(css = ".hanukkah-popup__popup")
    private WebElement popupPage;
    @FindBy(css = ".hanukkah-popup__button.btn.btn-link")
    private WebElement popupBtn;
    @FindBy(css = "div.nav-right__user > div > div > div > button")
    private WebElement logOutBtn;
    @FindBy(css = ".btn-blue.btn-small.btn")
    private WebElement logOutOkBtn;
    @FindBy(css = "div.nav-right__user > button")
    private WebElement loginBtn2;
    @FindBy(css = ".form-error-message")
    private WebElement errorMsgInvalidEmail;
    @FindBy(css = ".form-error")
    private WebElement errorMsgLoginFiled;
    @FindBy(css = "div.sign-in-form__fields > div:nth-child(1) > div.form-error-message")
    private WebElement errorMsgRequiredEmail;
    @FindBy(css = "div.input.input--password > div.form-error-message")
    private WebElement errorMsgRequiredPassword;
    @FindBy(css = "div.app-header__desktop > nav > div > div.header-nav__item.header-dropdown ")
    private WebElement servicesBtn;
    @FindBy(tagName = "picture")
    private List<WebElement> listPctr;
    @FindBy(css = "div.home-hero__button-container > a.btn-blue.btn.btn-link > span")
    private WebElement plannedTripsBtn;
    @FindBy(css = "a.btn-blue-outlined.btn.btn-link")
    private WebElement buildTripBtn;
    @FindBy(css = ".btn-secondary.cookies-policy-banner__button.btn")
    private WebElement gotItBtn;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    //Actions
    public void clickPopupBtn() {
        click(popupBtn);
    }

    public void logout() {
        moveTo(profileBtn);
        waitTo(logOutBtn);
        click(logOutBtn);
        waitTo(logOutOkBtn);
        click(logOutOkBtn);
        waitTo(loginBtn2);
    }

    public void waitToErrorMsgLoginFailed() {
        waitTo(errorMsgLoginFiled);
    }

    public void fillEmailAndPassword(String email, String password) {
        waitTo(emailFiled);
        fillText(emailFiled, email);
        fillText(passwordFiled, password);
        click(loginBtn);
    }

    public void clickSignIn() {
        waitTo(signInBtn);
        click(signInBtn);
    }

    public void waitErrorRequiredEmail() {
        waitTo(errorMsgRequiredEmail);
    }

    public void waitErrorRequiredEPassword() {
        waitTo(errorMsgRequiredPassword);
    }

    public void clickPlannedTrips() {
        click(plannedTripsBtn);
    }

    public void clickBuildTripBtn() {
        sleep(1000);
        click(buildTripBtn);
    }

    public void clickGotItBtn() {
        try {
            waitTo(gotItBtn);
            click(gotItBtn);
        } catch (Exception e) {
        }
    }

    //Validations
    public boolean isObjExists(WebDriver driver, WebElement el) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));  // wait הגדרת אובייקט
            wait.until(ExpectedConditions.elementToBeClickable(el));
            return true;  // true אם מצא את האובייקט תחזיר
        } catch (NoSuchElementException exception) {
            return false;  // תחזיר תשובה שלילית exception אם הגיע ל
        }
    }

    public String getProfilBtnName() {
        waitTo(profileBtn);
        return profileBtn.getText();
    }

    public String getLoginBtnName() {
        return loginBtn.getText();
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
}




