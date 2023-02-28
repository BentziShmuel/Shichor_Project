package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class DestinationsPage extends BasePage {

    @FindBy(css = ".form-input__input.react-autosuggest__input.destinations-header__input")
    private WebElement searchDestinationsFiled;
    @FindBy(css = ".destinations-header__suggestion-wrapper")
    private List<WebElement> optionsDestination;
    @FindBy(css = "div > button:nth-child(3)")
    private WebElement nextPageBtn;
    @FindBy(css = ".react-autosuggest__empty-text")
    private WebElement errorMsgType1;
    @FindBy(css = "div.form-error")
    private WebElement errorMsgType2;
    @FindBy(css = "div.destinations-header__form-control > button")
    private WebElement searchBtn;


    public DestinationsPage(WebDriver driver) {
        super(driver);
    }

    //Actions
    public void searchDestination(String name) {
        fillText(searchDestinationsFiled, name);
        sleep(3000);
        List<WebElement> listOptions = driver.findElements(By.cssSelector(".destinations-header__suggestion-wrapper"));
        for (WebElement option : listOptions) {
            if (option.getText().toLowerCase().contains(name)) {
                click(option);
                break;
            }
        }
    }

    public void clickNextPage() {
        click(nextPageBtn);
    }

    public void clickSearchBtn() {
        click(searchBtn);
    }

    public void clearSearchFiled() {
        clear(searchDestinationsFiled);
    }

    public void selectPopular(String namePopularDestination){
        List<WebElement> PopularDestinations = driver.findElements(By.cssSelector(".destinations-page-item__link.d-block.flex-fill"));
        for (WebElement el:PopularDestinations){
            System.out.println(getText(el));
            if (el.getText().toLowerCase().contains(namePopularDestination)){
                scrollToElement(el);
                click(el);
                break;
            }
        }
    }

    //Validations
    public String getErrorMsgType1() {
        return getText(errorMsgType1);
    }

    public String getErrorMsgType2() {
        return getText(errorMsgType2);
    }

    public String getTitlePage() {
        return driver.getTitle().toLowerCase();
    }

    public String getNameAllDestinations() {
        sleep(1000);
        String allDestinationsName = "";
        List<WebElement> listDestinations = driver.findElements(By.cssSelector("#__next  main  div.destinations-page-item__desc div"));
        for (WebElement destination : listDestinations) {
            scrollToElement(destination);
            allDestinationsName = allDestinationsName + destination.getText();
        }
        return allDestinationsName;
    }
}
