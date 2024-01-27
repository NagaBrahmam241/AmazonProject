package TestCases;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import Pages.AmazonHomePage;




public class TestAddSingleProductToCart {
	static WebDriver driver;
	
    @Test(description = "Verify adding a single product to the cart", priority = 1)

    public void testAddingSingleProductToCart() throws InterruptedException {
       
		
        AmazonHomePage amazonHomePage = new AmazonHomePage(driver);

        
        amazonHomePage.searchForProduct("Keyboard");
        amazonHomePage.selectProduct();
        amazonHomePage.addToCart();

        Assert.assertTrue(amazonHomePage.isProductAddedToCart("Keyboard"));
    }
	
	
}
