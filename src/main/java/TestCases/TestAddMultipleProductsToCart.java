package TestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import Pages.AmazonHomePage;

public class TestAddMultipleProductsToCart {
	static WebDriver driver;
	private AmazonHomePage amazonHomePage;
    @Test(description = "Verify adding multiple products to the cart", priority = 2)

	public void testAddMultipleProductsToCart() throws InterruptedException {
		
		driver = new ChromeDriver();
        amazonHomePage = new AmazonHomePage(driver);
        
        amazonHomePage.searchForProduct("Keyboard");
        amazonHomePage.selectProduct();
        amazonHomePage.addToCart();
        
        amazonHomePage.searchForProduct("Bluetooth");
        amazonHomePage.selectProduct();
        amazonHomePage.addToCart();

        
        Assert.assertTrue(amazonHomePage.isProductAddedToCart("Keyboard"));
        Assert.assertTrue(amazonHomePage.isProductAddedToCart("Bluetooth"));
    }
}
