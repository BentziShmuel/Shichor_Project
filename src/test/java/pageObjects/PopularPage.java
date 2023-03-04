package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PopularPage extends BasePage {

    @FindBy(css = ".vector-icon.bookmark-button-2__icon.bookmark-button-2__filled")
    private List <WebElement> bookmarkBtn;
    @FindBy(css = ".btn-blue.btn-small.btn")
    private WebElement removeBtn;

    public PopularPage(WebDriver driver) {
        super(driver);
    }

    public void WaitToBookmarkBtn(){
        waitToList(bookmarkBtn);
    }

    public void clickAllBookmarkBtn(){
        List <WebElement> allBookmarkBtn = driver.findElements(By.cssSelector("[href='#heart-filled']"));
        for (WebElement el:allBookmarkBtn){
            click(el);
        }
    }

    public void removeAllBookmarkBtn(){
        sleep(1000);
        List <WebElement> allBookmarkBtn = driver.findElements(By.cssSelector("[href='#heart-filled']"));
        for (WebElement el:allBookmarkBtn){
            click(el);
            click(removeBtn);
        }
    }
}

