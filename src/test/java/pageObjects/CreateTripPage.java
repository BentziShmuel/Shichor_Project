package pageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class CreateTripPage extends BasePage {

    @FindBy(css = "div:nth-child(1) > div.flows__item-btn.hide-xs.show-md > button")
    private WebElement startBtn;
    @FindBy(xpath = "//*[@id='__next']/main/div/div/div/form/div/div/div/div[3]/div/div/div")
    private WebElement destinationFiled;
    @FindBy(xpath = "//*[@id='__next']/main/div/div/div/form/div/div/div/div[3]/div/div/div/div")
    private WebElement destinationFiled2;
    @FindBy(css = "div.hide-xs.show-md > div > button")
    private WebElement nextStepBtn;
    @FindBy(css = ".create-trip-back")
    private WebElement backStep;
    @FindBy(css = "div.destination-form__list-items > button:nth-child(1)")
    private WebElement parisBtn;
    @FindBy(css = "div.destination-form__list-items > button:nth-child(2)")
    private WebElement larnacaBtn;
    @FindBy(css = "div.destination-form__list-items > button:nth-child(3)")
    private WebElement amsterdamBtn;
    @FindBy(css = "div.destination-form__list-items > button:nth-child(4)")
    private WebElement budapestBtn;
    @FindBy(css = "div.hide-xs.show-md > div > button")
    private WebElement PlanTripBtn;
    @FindBy(css = ".Toastify__toast-body")
    private WebElement ReturnDateErrorMsg;
    @FindBy(css = "div:nth-child(2) > div.flows__item-btn.hide-xs.show-md > button")
    private WebElement inspireMeBtn;
    @FindBy(css = "div.purpose-form__body > div > ul > li:nth-child(2)")
    private WebElement familyTripBtn;
    @FindBy(css = "form > div > div > div > div.purpose-form__body > div > div > ul > li:nth-child(2) > button > span")
    private WebElement barMitzvahBtn;
    @FindBy(css = ".btn-blue.btn")
    private WebElement nextStepBtn2;

    public CreateTripPage(WebDriver driver) {
        super(driver);
    }

    //Actions
    public void clickStart() {
        waitTo(startBtn);
        click(startBtn);
    }

    public void clickDestinationFiled() {
        click(destinationFiled);
    }

    public void clickBackStep() {
        click(backStep);
    }

    public void clickParis() {
        click(parisBtn);
    }

    public void clickLarnaca() {
        click(larnacaBtn);
    }

    public void clickAmsterdam() {
        click(amsterdamBtn);
    }

    public void clickBudapest() {
        click(budapestBtn);
    }

    public void clickPlanTripBtn() {
        click(PlanTripBtn);
    }

    public void waitToReturnDateErrorMsg() {
        waitTo(ReturnDateErrorMsg);
    }

    public void clickInspireMeBtn() {
        click(inspireMeBtn);
    }

    public void clickNextStep() {
        click(nextStepBtn);
    }

    public void clickFamilyTripBtn() {
        click(familyTripBtn);
    }

    public void clickBarMitzvahBtn() {
        click(barMitzvahBtn);
    }

    public void clickNextStep2() {
        click(nextStepBtn2);
    }

    public void waitToInspireMeBtn() {
        waitTo(inspireMeBtn);
    }

    public void clickShowResults() {
        click(nextStepBtn2);
    }

    public void SpecifyYourInterest(int Museums0_10, int Sights0_10, int Events0_10, int Gastronomy0_10, int Shopping0_10, int Nightlife0_10, int Sports0_10, int Recreation0_10) {
        int[] slidersInterest = {Museums0_10, Sights0_10, Events0_10, Gastronomy0_10, Shopping0_10, Nightlife0_10, Sports0_10, Recreation0_10};
        for (int i = 0; i < slidersInterest.length; i++) {
            int numInterest = slidersInterest[i];
            List<WebElement> allSlide = driver.findElements(By.cssSelector(".interest-slider__handle"));
            for (int j = i; j == i; j++) {
                for (int k = 0; k < 150; k = k + 3) {
                    int valueNow = 0;
                    moveSlider(allSlide.get(j), k, 0);
                    List<WebElement> ariaValuenow = driver.findElements(By.cssSelector("[aria-valuenow]"));
                    for (int l = i; l == i; l++) {
                        String SValueNow = ariaValuenow.get(l).getAttribute("aria-valuenow");
                        valueNow = Integer.parseInt(SValueNow);
                    }
                    if (valueNow == numInterest) {
                        break;
                    }
                }
            }
        }
    }

    //Validations
    public String startDate(int day, String month, int year) {
        String startDate = "";
        String date = month + " " + Integer.toString(day) + ", " + Integer.toString(year);
        List<WebElement> calendarMonth = driver.findElements(By.cssSelector(".flatpickr-day"));
        for (int i = 0; i < calendarMonth.size(); i++) {
            if (!(calendarMonth.get(i).getAttribute("class").contains("disabled"))) {
                if (calendarMonth.get(i).getAttribute("aria-label").equalsIgnoreCase(date)) {
                    startDate = calendarMonth.get(i).getAttribute("aria-label").replaceAll("([a-z])", "").replaceAll("([A-Z])", "");
                    startDate = startDate.substring(0, startDate.length() - 6);
                    click(calendarMonth.get(i));
                    break;
                }
            }
        }
        return startDate;
    }

    public String endDate(int day, String month, int year) {
        String endDate = "";
        String date = month + " " + Integer.toString(day) + ", " + Integer.toString(year);
        List<WebElement> calendarMonth2 = driver.findElements(By.cssSelector(".flatpickr-day"));
        for (int i = 0; i < calendarMonth2.size(); i++) {
            if (!(calendarMonth2.get(i).getAttribute("class").contains("disabled"))) {
                if (calendarMonth2.get(i).getAttribute("aria-label").equalsIgnoreCase(date)) {
                    endDate = calendarMonth2.get(i).getAttribute("aria-label").replaceAll("([a-z])", "").replaceAll("([A-Z])", "");
                    endDate = endDate.substring(0, endDate.length() - 6);
                    click(calendarMonth2.get(i));
                    break;
                }
            }
        }
        return endDate;
    }

    public String getNameBudapestBtn() {
        return getText(budapestBtn);
    }

    public String getNameAmsterdamBtn() {
        return getText(amsterdamBtn);
    }

    public String getNameLarnacaBtn() {
        return getText(larnacaBtn);
    }

    public String getNameParisBtn() {
        return getText(parisBtn);
    }

    public String getNameStep() {
        return getText(backStep);
    }

    public String getTitlePage() {
        return driver.getTitle();
    }

    public String getReturnDateErrorMsg() {
        return getText(ReturnDateErrorMsg);
    }

    public String getFamilyTripBtn() {
        String familyTrip = familyTripBtn.getText();
        return familyTrip;
    }
}

