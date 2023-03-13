package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class BasePage {

    @FindBy(css = ".app-header__logo")
    private WebElement shichorBtn;
    @FindBy(css = "div.nav-right__user > button")
    private WebElement signInBtn;
    @FindBy(css = "#email")
    private WebElement emailFiled;
    @FindBy(css = "[type='password']")
    private WebElement passwordFiled;
    @FindBy(css = ".btn-blue.btn-fluid.btn")
    private WebElement loginBtn;
    @FindBy(css = "div.app-header__desktop > nav > div > a:nth-child(1)")
    private WebElement destinationsBtn;
    @FindBy(css = "[href='#close']")
    private WebElement closeLoginBtn;

    WebDriver driver;
    WebDriverWait wait;
    Actions actions;


    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
        actions = new Actions(driver);

    }
    public void clickShichorBtn() {
        click(shichorBtn);
        sleep(1000);
    }

    public void login(String email, String password) {
        click(signInBtn);
        fillText(emailFiled, email);
        fillText(passwordFiled, password);
        click(loginBtn);
    }

    public void closeLogin() {
        waitTo(closeLoginBtn);
        click(closeLoginBtn);
    }

    public void clickDestinationsBtn() {
        click(destinationsBtn);
    }


    public void fillText(WebElement el, String text) {
        highlightElement(el);
        el.clear();
        el.sendKeys(text);
    }

    public void clear(WebElement el) {
        el.clear();
    }

    public void click(WebElement el) {
        highlightElement(el);
        el.click();
        sleep(300);
    }

    public String getText(WebElement el) {
        highlightElement(el);
        return el.getText();
    }

    public void moveTo(WebElement el) {
        highlightElement(el);
        actions.moveToElement(el).build().perform();
    }

    public void sleep(long millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void waitTo(WebElement nameEl) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));  // wait הגדרת אובייקט
        wait.until(ExpectedConditions.elementToBeClickable(nameEl));
    }

    public void waitToList(List<WebElement> nameList) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));  // wait הגדרת אובייקט
        wait.until(ExpectedConditions.invisibilityOfAllElements(nameList));
    }

    public void waitToTitle(String title) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));
        wait.until(ExpectedConditions.titleContains(title));
    }

    private void highlightElement(WebElement element) {
        //keep the old style to change it back
        String originalStyle = element.getAttribute("style");
        String newStyle = "background-color:yellow; border: 2px solid " + "red" + ";" + originalStyle;
        JavascriptExecutor js = (JavascriptExecutor) driver;

        // Change the style
        js.executeScript("var tmpArguments = arguments;setTimeout(function () {tmpArguments[0].setAttribute('style', '" + newStyle + "');},0);",
                element);

        // Change the style back after few milliseconds
        js.executeScript("var tmpArguments = arguments;setTimeout(function () {tmpArguments[0].setAttribute('style', '"
                + originalStyle + "');},400);", element);

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

    public void moveSlider(WebElement element, int x, int y) {
        WebElement Slider = element;
        Actions moveSlider = new Actions(driver);
        Action action = moveSlider.dragAndDropBy(Slider, x, y).build();
        action.perform();
    }

    public void scrollToElement(WebElement element) { // scroll to element
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView();", element);
    }

    public void scrollDown(int X) {    // Scroll down on page
        ((JavascriptExecutor) driver).executeScript("window.scrollBy(0," + X + ")");
    }
}



