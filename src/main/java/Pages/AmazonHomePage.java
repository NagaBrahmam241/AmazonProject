package Pages;

import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AmazonHomePage {

	private static WebDriver driver;
	String url = "https://www.amazon.in/";

	
	public AmazonHomePage(WebDriver driver) {
		this.driver = driver;
	}

	public void naviagteToHomePage() {
		if (!this.driver.getCurrentUrl().equals(this.url)) {
			this.driver.get(this.url);
		}
	}

	public void searchForProduct(String productName) throws InterruptedException {
		WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
		searchBox.sendKeys(productName);
		
		Thread.sleep(5000);
		searchBox.submit();
		System.out.println("Product found");
	}

	public void selectProduct() throws InterruptedException {
		WebElement firstProduct = driver.findElement(By.xpath("(//div[@class='a-section'])[2]"));
		firstProduct.click();
		Thread.sleep(2000);

	}

	public void addToCart() throws InterruptedException {
		String originalWindowHandle = driver.getWindowHandle();

		Set<String> windowHandles = driver.getWindowHandles();
		for (String handle : windowHandles) {
			if (!handle.equals(originalWindowHandle)) {
				driver.switchTo().window(handle);
				WebElement addtoCartBtn = driver.findElement(By.xpath("//input[@id='add-to-cart-button']"));
				addtoCartBtn.click();
				Thread.sleep(2000);
				driver.close();
				driver.switchTo().window(originalWindowHandle);
				break;
			}
		}
	}

	public static void navigateToCart() throws InterruptedException {
		try {
			driver.navigate().refresh();
			driver.findElement(By.xpath("//*[@id=\"nav-cart-count-container\"]")).click();
			Thread.sleep(3000);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public boolean isProductAddedToCart(String string) {
		List<WebElement> productNameList = driver.findElements(By.xpath("//div[4]/div/div[2]/ul/li"));
		for(WebElement pro:productNameList) {
			if(pro.getText().contains(string));
			System.out.println("Given product added to cart : " + string);
			return true;
		}
		System.out.println("Given product Not added to cart");
		return false;
	}

	public static void editCartItemQuantity(String productName, int quantity) throws InterruptedException {
		List<WebElement> productNameList = driver.findElements(By.xpath("//div[4]/div/div[2]/ul/li"));
		for(WebElement pro:productNameList) {
			if(pro.getText().contains(productName)){
				driver.findElement(By.xpath("//select[@id='quantity']//following-sibling::span/child::span/child::span")).click();
				Thread.sleep(2000);
				
				WebElement El = driver.findElement(By.xpath("//*[@id='a-popover-1']/div/div/ul/li[' +(quantity + 1)+ ']"));
		        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
		        jsExecutor.executeScript("arguments[0].click();",El);

				Thread.sleep(2000);
				System.out.println("Given product added to cart : " + productName);
			}
		}

	}

	public int getCartItemQuantity() {
		List<WebElement> cartQtyEl = driver.findElements(By.xpath("//span[@class='a-button-text a-declarative']"));
		System.out.println(cartQtyEl.size());
		return cartQtyEl.size();
	}

	public static Object getItemQuantity(String string) {
		List<WebElement> productNameList = driver.findElements(By.xpath("//div[4]/div/div[2]/ul/li"));
		for(WebElement pro:productNameList) {
			if(pro.getText().contains(string)){
				WebElement qtyEl = driver.findElement(By.xpath("//*[@id=\"a-autoid-0-announce\"]/span[2]"));
				String qty = qtyEl.getText();
				System.out.println("Quantity of given product : " + qty);
			}
		}
		return string;
	}


	/*public static void main(String[] args) throws InterruptedException {
		WebDriverManager.chromedriver().setup();

		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.amazon.in/");
		
		AmazonHomePage amazonHomePage = new AmazonHomePage(driver);
		amazonHomePage.searchForProduct("Keyboard");
        amazonHomePage.selectProduct();
        amazonHomePage.addToCart();
        amazonHomePage.navigateToCart();
        amazonHomePage.searchForProduct("Bluetooth");
        amazonHomePage.selectProduct();
        amazonHomePage.addToCart();
        amazonHomePage.navigateToCart();
        amazonHomePage.getCartItemQuantity();
        amazonHomePage.isProductAddedToCart("Keyboard");
        amazonHomePage.editCartItemQuantity("Keyboard", 4);
        amazonHomePage.getItemQuantity("Keyboard");
        
	}*/

}
