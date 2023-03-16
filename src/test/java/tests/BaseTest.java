package tests;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;


import utils.Utils;

public abstract class BaseTest {
    WebDriver driver;

    @BeforeClass
    public void setup() throws URISyntaxException, IOException {
        WebDriverManager webDriverManager = WebDriverManager
                .chromedriver()
                .browserInDocker()
                .enableVnc()
                .enableRecording();
        driver = webDriverManager
                .create();
        Desktop.getDesktop().browse(webDriverManager.getDockerNoVncUrl().toURI());
        driver.manage().window().maximize();
        driver.get("https://www.shichor.co.il/en");

    }


    @AfterMethod
    public void failedTest(ITestResult result) {
        //check if the test failed
        if (result.getStatus() == ITestResult.FAILURE) {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File srcFile = ts.getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(srcFile, new File("./ScreenShots/" + result.getName() + ".jpg"));
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            //result.getname() method will give you current test case name.
            //./ScreenShots/ tell you that, in your current directory, create folder ScreenShots. dot represents current directory
        }
    }


    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}


