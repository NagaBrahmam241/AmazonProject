package TestCases;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import Pages.AmazonHomePage;

public class TestEditItemQuantityInCart {
	static WebDriver driver;
	
    @Test(description = "Verify editing the quantity of an item in the cart", priority = 3)

	public void testEditItemQuantityInCart() throws InterruptedException {
		
		driver = new ChromeDriver();
        AmazonHomePage amazonHomePage = new AmazonHomePage(driver);

        
        AmazonHomePage.navigateToCart();
        AmazonHomePage.editCartItemQuantity("Keyboard", 3); 

       
        Assert.assertEquals(AmazonHomePage.getItemQuantity("YourProduct"), 3);
    }


}
