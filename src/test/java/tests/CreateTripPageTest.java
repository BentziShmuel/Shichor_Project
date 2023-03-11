package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pageObjects.CreateTripPage;
import pageObjects.HomePage;
import pageObjects.OverviewPage;

public class CreateTripPageTest extends BaseTest {


    @Test
    public void tc01_getTtitlePage() {
        HomePage hp = new HomePage(driver);
        hp.closePopUp();
        hp.clickGotItBtn();
        hp.clickBuildTripBtn();
        CreateTripPage ctp = new CreateTripPage(driver);
        ctp.sleep(4000);
        Assert.assertEquals(ctp.getTitlePage(), "Create trip - Shichor");
    }

    @Test
    public void tc02_parisBtn() {
        CreateTripPage ctp = new CreateTripPage(driver);
        ctp.clickStart();
        String expected = ctp.getNameParisBtn();
        ctp.clickParis();
        String actual = ctp.getNameStep();
        ctp.clickBackStep();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void tc03_larnacaBtn() {
        CreateTripPage ctp = new CreateTripPage(driver);
        String expected = ctp.getNameLarnacaBtn();
        ctp.clickLarnaca();
        String actual = ctp.getNameStep();
        ctp.clickBackStep();
        Assert.assertEquals(actual, expected);

    }

    @Test
    public void tc04_amsterdamBtn() {
        CreateTripPage ctp = new CreateTripPage(driver);
        String expected = ctp.getNameAmsterdamBtn();
        ctp.clickAmsterdam();
        String actual = ctp.getNameStep();
        ctp.clickBackStep();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void tc05_budapestBtn() {
        CreateTripPage ctp = new CreateTripPage(driver);
        String expected = ctp.getNameBudapestBtn();
        ctp.clickBudapest();
        String actual = ctp.getNameStep();
        ctp.clickBackStep();
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void tc06_returnDateErrorMsg() {
        CreateTripPage ctp = new CreateTripPage(driver);
        ctp.clickParis();
        ctp.clickPlanTripBtn();
        ctp.waitToReturnDateErrorMsg();
        String actual = ctp.getReturnDateErrorMsg();
        String expected = "Please, specify return date";
        Assert.assertEquals(actual, expected);
    }

    @Test
    public void tc07_whenBtn() {
        CreateTripPage ctp = new CreateTripPage(driver);
        String startDate = ctp.startDate(22, "March", 2023);
        String endDate = ctp.endDate(27, "March", 2023);
        String daysSelected = startDate + "-" + endDate;
        daysSelected = daysSelected.replace(" ", "");
        ctp.clickPlanTripBtn();
        OverviewPage op = new OverviewPage(driver);
        op.waitToWhenDaysFiled();
        driver.navigate().refresh();
        op.waitToWhenDays();
        String whenDays = op.getWhenDays();
        Assert.assertEquals(whenDays, daysSelected);
    }

    @Test
    public void tc08_purposeBtn() {
        CreateTripPage ctp = new CreateTripPage(driver);
        ctp.clickShichorBtn();
        HomePage hp = new HomePage(driver);
        hp.clickBuildTripBtn();
        ctp.waitToInspireMeBtn();
        ctp.clickInspireMeBtn();
        String startDate = ctp.startDate(22, "March", 2023);
        String endDate = ctp.endDate(27, "March", 2023);
        ctp.clickNextStep();
        ctp.clickFamilyTripBtn();
        ctp.clickBarMitzvahBtn();
        ctp.clickNextStep2();
        ctp.SpecifyYourInterest(1, 2, 3, 4, 5, 6, 7, 8);
        ctp.clickNextStep2();
        OverviewPage op = new OverviewPage(driver);
        op.waitToPurposeBtn();
        String actual = op.getNamePurposeBtn();
        String expected = "Family trip";
        Assert.assertEquals(actual, expected);
    }
}



