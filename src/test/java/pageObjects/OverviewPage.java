package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OverviewPage extends BasePage {
    @FindBy(css = "div:nth-child(2) > button > div.step-value")
    private WebElement whenDays;
    @FindBy(css = "div:nth-child(5) > button > div.step-value")
    private WebElement purposeBtn;


    public OverviewPage(WebDriver driver) {
        super(driver);
    }

    //Actions
    public void waitToWhenDaysFiled() {
        waitTo(whenDays);
    }

    public void waitToWhenDays() {
        waitTo(whenDays);
    }

    public void waitToPurposeBtn() {
        waitTo(purposeBtn);
    }

    //Validations
    public String getWhenDays() {
        return getText(whenDays).replaceAll("([a-z])", "").replaceAll("([A-Z])", "").replace(" ", "");
    }

    public String getNamePurposeBtn() {
        return getText(purposeBtn);
    }
}
