package TestCases;

import java.io.File;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Pages.AmazonHomePage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

    private WebDriver driver;
    private AmazonHomePage amazonHomePage;

    @BeforeClass
    public void setUp() {
        
        
        WebDriverManager.chromedriver().setup();
		
		driver=new ChromeDriver();
		driver.manage().window().maximize();
		captureScreenshot("Start_Of_Test");
  
    }
    
    @AfterClass
    public void tearDown() {
    	captureScreenshot("End_Of_Test");
        driver.quit();
    }
    @AfterMethod
    public void afterMethod(ITestResult result) {
        if (result.getStatus() == ITestResult.FAILURE) {
            captureScreenshot("Failure_" + result.getName());
        }
    }

    
    private void captureScreenshot(String screenshotName) {
        try {
            TakesScreenshot ts = (TakesScreenshot) driver;
            File source = ts.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(source, new File("./Screenshots/" + screenshotName + ".png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
