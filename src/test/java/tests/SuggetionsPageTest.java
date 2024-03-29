package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.CreateTripPage;
import pageObjects.HomePage;
import pageObjects.SuggestionsPage;

public class SuggetionsPageTest extends BaseTest {

    @Test (description = "Verify website displays budget-appropriate trips.")
    public void tc01_BudgetOverrun() {
        HomePage hp = new HomePage(driver);
        hp.closePopUp();
        hp.clickGotItBtn();
        hp.clickBuildTripBtn();
        CreateTripPage ctp = new CreateTripPage(driver);
        ctp.waitToInspireMeBtn();
        ctp.clickInspireMeBtn();
        String startDate = ctp.startDate(22, "March", 2023);
        String endDate = ctp.endDate(27, "March", 2023);
        ctp.clickNextStep();
        ctp.clickFamilyTripBtn();
        ctp.clickBarMitzvahBtn();
        ctp.clickNextStep2();
        ctp.SpecifyYourInterest(1, 2, 3, 1, 2, 3, 1, 2);
        ctp.clickShowResults();
        SuggestionsPage sp = new SuggestionsPage(driver);
        sp.waitToHowMuchBtn();
        driver.navigate().refresh();
        sp.waitToHowMuchBtn();
        sp.sleep(2000);
        sp.clickHowMuchBtn();
        sp.sleep(5000);
        sp.scrollToHowMuchSlider();
        sp.selectHowMuch(-90);
        String howMuch = sp.getHowMuchSlider();
        long selectedCost = new Long(howMuch).longValue();
        sp.sleep(500);
        sp.clickSearchBtn2();
        sp.sleep(10000);
        String exceptions = sp.FindExceptions(selectedCost);
        System.out.println(" The exception is: " + exceptions);
        Assert.assertEquals(exceptions, "");
    }

    @Test (description = "Checking if the budget for the trip is the same on all pages.")
    public void tc02_tripBudgetPrice() {
        SuggestionsPage sp = new SuggestionsPage(driver);
        String expected = sp.getLondonBudgetPrice();
        sp.sleep(10000);
        sp.clickLondonTrip();
        sp.sleep(5000);
        driver.navigate().refresh();
        sp.sleep(10000);
        sp.scrollToExpectedCosts();
        String actual = sp.getTripBudgetPrice();
        Assert.assertEquals(actual, expected);
    }

    @Test(description = "Verify that the How much button displays the correct budget for the selected trip options.")
    public void tc03_howMuchBtn() {
        SuggestionsPage sp = new SuggestionsPage(driver);
        sp.clickShichorBtn();
        HomePage hp = new HomePage(driver);
        hp.clickBuildTripBtn();
        CreateTripPage ctp = new CreateTripPage(driver);
        ctp.waitToInspireMeBtn();
        ctp.clickInspireMeBtn();
        String startDate = ctp.startDate(22, "March", 2023);
        String endDate = ctp.endDate(27, "March", 2023);
        ctp.clickNextStep();
        ctp.clickFamilyTripBtn();
        ctp.clickBarMitzvahBtn();
        ctp.clickNextStep2();
        ctp.SpecifyYourInterest(2, 2, 2, 4, 4, 4, 6, 6);
        ctp.clickNextStep2();
        sp.waitToHowMuchBtn();
        driver.navigate().refresh();
        sp.sleep(1000);
        sp.clickHowMuchBtn();
        sp.sleep(5000);
        sp.scrollToHowMuchSlider();
        sp.selectHowMuch(-90);
        sp.sleep(500);
        String expected = sp.getHowMuchSlider();
        String actual = sp.getHowMuchBtn();
        actual = sp.getHowMuchBtn().substring(1).replace(",", "");
        Assert.assertEquals(actual, expected);
    }

    @Test (description = "Verify selecting the Who button on the suggestions page and selecting a specific number of people updates the Who button correctly.")
    public void tc04_whoBtn() {
        SuggestionsPage sp = new SuggestionsPage(driver);
        sp.clickWhoBtn();
        String expected = sp.selectWhoPeople(1, 1, 1, 1, 1, 1, 1, 1);
        String actual = sp.getWhoPeopleBtn();
        Assert.assertEquals(actual, expected);
    }

    @Test (description = "Verify selecting more than 9 people in 'Who's coming?' doesn't increase total count.")
    public void tc05_noMorePeopleAdded() {
        SuggestionsPage sp = new SuggestionsPage(driver);
        sp.clickWhoBtn();
        sp.selectWhoPeople(1, 1, 1, 1, 2, 1, 1, 1);
        String expected = sp.getNumAllPeople();
        System.out.println(expected);
        sp.selectWhoPeople(1, 1, 1, 1, 3, 1, 1, 1);
        String actual = sp.getNumAllPeople();
        System.out.println(actual);
        Assert.assertEquals(actual, expected);

    }

    @Test(description = "Verify error message when selecting more people than allowed (9) in the 'Who's coming?' field")
    public void tc06_errorTotalNumber() {
        SuggestionsPage sp = new SuggestionsPage(driver);
        sp.clickWhoBtn();
        sp.selectWhoPeople(1, 1, 1, 1, 3, 1, 1, 1);
        sp.sleep(700);
        String actual = sp.getErrorTotalNum();
        String expected = "The total number of participants shouldn`t be more than 9";
        Assert.assertEquals(actual, expected);
    }
}
