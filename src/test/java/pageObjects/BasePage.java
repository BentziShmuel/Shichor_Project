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

    WebDriver driver;
    WebDriverWait wait;
    Actions actions;


    public BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
        actions = new Actions(driver);

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

    /*
     * Call this method with your element and a color like (red,green,orange etc...)
     */
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



