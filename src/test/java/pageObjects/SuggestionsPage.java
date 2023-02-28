package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class SuggestionsPage extends BasePage {
    @FindBy(css = "div:nth-child(4) > button > div.step-value")
    private WebElement howMuchBtn;
    @FindBy(css = "div:nth-child(3) > button > div.step-value")
    private WebElement whoBtn;
    @FindBy(css = "div:nth-child(4) > button > div.step-value")
    private WebElement HowMuchBtn;
    @FindBy(xpath = "//*[@id=\"__next\"]/main/div/div[1]/div/div/div/form/div/div[2]/div/div[2]/button")
    private WebElement searchBtn2;
    @FindBy(css = ".rc-slider-handler-tooltip")
    private WebElement sliderPrice;
    @FindBy(css = "//*[@id=\"__next\"]/main/div/div[1]/div/div/div/form/div/div[3]/div/div/div[2]")
    private WebElement howMuchSlider;
    @FindBy(css = "#__next > main > div > div.trips-search-list > div > a:nth-child(1) > div")
    private WebElement londonTripBtn;
    @FindBy(css = "div:nth-child(5) > button > div.step-value")
    private WebElement purposeBtn;
    @FindBy(xpath = "//*[@id=\"__next\"]/main/div/div[2]/div/div[7]/div[2]/div/div[1]/div[2]/div[2]")
    private WebElement tripBudgetPrice;
    @FindBy(xpath = "//*[@id='__next']/main/div/div[2]/div/a[1]/div/div[2]/div[2]/div[2]")
    private WebElement londonPrice;
    @FindBy(css = ".trip-budget-desktop")
    private WebElement ExpectedCosts;
    @FindBy(css = "div.rc-slider-handler-tooltip > div")
    private WebElement sliderLine;
    @FindBy(css = "div.sb-desktop__bar-items > div:nth-child(3) > button > div.step-value > span")
    private WebElement whoPeopleBtn;

    public SuggestionsPage(WebDriver driver) {
        super(driver);
    }

    //Actions
    public void clickHowMuchBtn() {
        click(howMuchBtn);
    }

    public void clickWhoBtn() {
        click(whoBtn);
    }

    public void clickSearchBtn2() {
        click(searchBtn2);
    }

    public void selectHowMuch(int xOffset) {
        moveSlider(sliderPrice, xOffset, 0);
    }

    public void clickLondonTrip() {
        scrollToElement(londonTripBtn);
        click(londonTripBtn);
    }

    public void scrollToHowMuchSlider() {
        scrollDown(20);
    }

    public void waitToPurposeBtn() {
        waitTo(purposeBtn);
    }

    public void waitToWhoBtn() {
        waitTo(whoBtn);
    }

    public void waitToHowMuchBtn() {
        waitTo(howMuchBtn);
    }

    public void scrollToExpectedCosts() {
        scrollToElement(ExpectedCosts);
    }

    public void waitToLondonBudgetPrice() {
        waitTo(londonPrice);
    }

    public void waitToLondonTripBtn() {
        waitTo(londonTripBtn);
    }

    //Validations
    public String getHowMuchBtn(){
        String hmb = howMuchBtn.getText();
        return hmb;
    }

    public String getTripBudgetPrice() {
        String price = tripBudgetPrice.getText();
        return price;
    }

    public String getLondonBudgetPrice() {
        scrollToElement(londonPrice);
        String price = londonPrice.getText();
        return price;
    }

    public String getHowMuchSlider() {
        String num = sliderLine.getAttribute("aria-valuenow");
        return num;
    }

    public String FindExceptions(long selectedCost) { //Checks if there are trips that are more expensive than the selected budget
        String exceptions = "";
        String budget = "";
        List<WebElement> listEstimatedBudget = driver.findElements(By.cssSelector(".trip-card__price-value"));
        for (WebElement estimatedBudget : listEstimatedBudget) {
            budget = estimatedBudget.getText();
            String eb = estimatedBudget.getText().substring(1).replace(",", "");
            long price = new Long(eb).longValue();
            if (price > selectedCost) {
                List<WebElement> cities = driver.findElements(By.cssSelector(".trip-card"));
                for (WebElement city : cities) {
                    if (city.getText().contains(budget)) {
                        scrollToElement(city);
                        exceptions = exceptions + city.getText().substring(9);
                    }
                }
            }
        }
        return exceptions;
    }

    public String selectWhoPeople(int Y0_3, int Y4_8, int Y9_13, int Y14_17, int Y18_30, int Y31_45, int Y45_65, int Y65_120) {
        int[] peopleYO = {Y18_30, Y31_45, Y45_65, Y65_120, Y0_3, Y4_8, Y9_13, Y14_17};
        for (int i = 0; i < peopleYO.length; i++) {
            int num = peopleYO[i];
            List<WebElement> whoPeople = driver.findElements(By.cssSelector(".parties-input__field"));
            for (int j = i; j < whoPeople.size(); i++) {
                String who = whoPeople.get(j).findElement(By.cssSelector(".parties-input__field-value")).getText();
                int howPeople = Integer.parseInt(who);
                if (howPeople < num) {
                    for (int k = howPeople; k < num; k++) {
                        List<WebElement> plusAndMinus = whoPeople.get(i).findElements(By.cssSelector(".parties-input__field-button"));
                        click(plusAndMinus.get(1));
                    }
                }
                if (howPeople > num) {
                    for (int k = howPeople; k > num; k--) {
                        List<WebElement> plusAndMinus = whoPeople.get(i).findElements(By.cssSelector(".parties-input__field-button"));
                        click(plusAndMinus.get(0));
                    }
                }
                break;
            }
        }
        int numAdults = 0;
        int numKids = 0;
        for (int i = 0; i <= 3; i++) {
            numAdults = numAdults + peopleYO[i];
        }
        for (int i = 4; i <= 7; i++) {
            numKids = numKids + peopleYO[i];
        }
        String kids = " " + numKids + " kids";
        String adults = numAdults + " adults,";
        String whoPeople = adults + kids;
        return whoPeople;
    }

    public String getWhoPeopleBtn() {
        String whoPeople = whoPeopleBtn.getText();
        return whoPeople;
    }

    public String getWhatBtn() {
        String whatBtn = purposeBtn.getText();
        return whatBtn;
    }

    public String getNumAllPeople(){
        List<WebElement> numPeople = driver.findElements(By.cssSelector(".parties-input__field-value"));
        String numAllpeople ="";
        for (WebElement people:numPeople){
            numAllpeople = numAllpeople + people.getText();
        }
        return numAllpeople;
    }

    public String getErrorTotalNum(){
        String msg = driver.findElement(By.cssSelector(".Toastify__toast-body")).getText();
        return msg;
    }
}






