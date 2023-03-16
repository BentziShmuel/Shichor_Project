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

public class HomePage extends NavBar {

    @FindBy(css = "div.nav-right__user > button")
    private WebElement signInBtn;
    @FindBy(css = ".nav-right__user-profile")
    private WebElement profileBtn;
    @FindBy(css = ".hanukkah-popup__button.btn.btn-link")
    private WebElement popupBtn;
    @FindBy(css = ".form-error")
    private WebElement errorMsgLoginFiled;
    @FindBy(css = "div.app-header__desktop > nav > div > div.header-nav__item.header-dropdown ")
    private WebElement servicesBtn;
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

    public void waitToErrorMsgLoginFailed() {
        waitTo(errorMsgLoginFiled);
    }

    public void clickSignIn() {
        waitTo(signInBtn);
        click(signInBtn);
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

    public void closePopUp() { // Refresh page when there is a popup
        sleep(8000);
        List<WebElement> list = driver.findElements(By.tagName("picture"));
        for (WebElement el : list) {
            if (el.getAttribute("Class").toLowerCase().contains("popup")) {
                waitTo(el);
                driver.navigate().refresh();
                break;
            }
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

}




