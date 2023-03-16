package tests;

import jdk.jfr.Description;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pageObjects.HomePage;

public class homePageTest extends BaseTest {

    @Test (description = "Verify successful login with valid credentials")
    public void tc01_login() {
        HomePage hp = new HomePage(driver);
        hp.closePopUp();
        hp.login("pborsud@gmail.com", "By0527160957");
        Assert.assertTrue(hp.getProfilBtnName().toLowerCase().contains("hi"));
    }

    @Test (description = "Logout test")
    public void tc02_logout() {
        HomePage hp = new HomePage(driver);
        hp.logout();
        Assert.assertTrue(hp.getLoginBtn2Name().toLowerCase().contains("in"));
    }


    @Test (description = "Verify error message displayed when trying to login with invalid email address")
    public void tc03_invalidEmailMsg() {
        HomePage hp = new HomePage(driver);
        hp.login("e", "By123456");
        String actual = hp.getErrorMsgInvalidEmail();
        String expected = "Please enter a valid email address";
        Assert.assertEquals(actual, expected);
        hp.closeLogin();
    }


    @Test (dataProvider = "getData")
    @Description("This test verifies that attempting to log in with invalid credentials results in an error message 'Unable to log in with provided credentials'.")
    public void tc04_LoginFailed(String email, String password) {
        HomePage hp = new HomePage(driver);
        hp.login(email, password);
        hp.waitToErrorMsgLoginFailed();
        String actual = hp.getErrorMsgLoginFailed();
        hp.closeLogin();
        String expected = "Unable to log in with provided credentials.";
        Assert.assertEquals(actual, expected);

    }
    @DataProvider
    public Object[][] getData() {
        Object[][] myData = {
                {"1@gmail.com", "Bb123456"},
                {"2@gmail.com", "Bb1234"},
                {"pborsud@gmail.com", "Bb123456"},
        };
        return myData;
    }
    @Test(description = "Verify error message when trying to login without entering email and password")
    public void tc05_getErrorRequiredMsg() {
        HomePage hp = new HomePage(driver);
        hp.login("", "");
        hp.waitErrorRequiredEmail();
        String actual = hp.getErrorMsgRequiredEmail();
        String expected = "Required";
        Assert.assertEquals(actual, expected);
        hp.waitErrorRequiredEPassword();
        actual = hp.getErrorMsgRequiredPassword();
        Assert.assertEquals(actual, expected);
    }
}

