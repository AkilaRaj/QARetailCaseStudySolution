package myretailTest;

import org.testng.annotations.*;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;

public class LandingPageTest {
	public String baseUrl;
	public WebDriver driver;
	
	@BeforeClass 
	public void setup() {		
		driver = new FirefoxDriver();
		driver.get("http://localhost:8080/myRetail/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS); 
	}
	
	 @Test
	 public void verifyPageTitle() {
		  String expectedTitle = "myRetail Product Lookup";
		  String actualTitle = driver.findElement(By.xpath("html/body/div[1]/div/h1")).getText();
		  Assert.assertEquals(actualTitle, expectedTitle);
     }
	 
	 @Test
	 public void verifyThreeButtons() {	 
		 Assert.assertEquals("Find All Products", driver.findElement(By.id("findAll")).getText());
		 Assert.assertEquals("Find All Products in Stock", driver.findElement(By.id("findAllProductsInStock")).getText());
		 Assert.assertEquals("Find All Products out of Stock", driver.findElement(By.id("findAllProductsOutOfStock")).getText());	 
	 }
	 
	 @Test
	 public void verifySearchProduct() {
		 Assert.assertEquals("Search", driver.findElement(By.id("searchProduct")).getText());
		 Assert.assertEquals("Product Id", driver.findElement(By.cssSelector("#productId")).getAttribute("placeholder"));
	 }
	 
	 @Test
	 public void verifyTableHeader() {		 
		 Assert.assertEquals("Product Details", driver.findElement(By.xpath("html/body/div[3]/div/h3")).getText());
		 Assert.assertEquals("Product Id", driver.findElement(By.xpath("//*[@id='productTable']/tbody/tr/th[1]")).getText());
	//	 Assert.assertEquals("Product Name", driver.findElement(By.id("//*[@id='productTable']/tbody/tr/th[2]")).getText());
		 Assert.assertEquals("Product Description", driver.findElement(By.xpath("//*[@id='productTable']/tbody/tr/th[3]")).getText());
		 Assert.assertEquals("Manufacturer", driver.findElement(By.xpath("//*[@id='productTable']/tbody/tr/th[4]")).getText());
		 Assert.assertEquals("Quantity Available", driver.findElement(By.xpath("//*[@id='productTable']/tbody/tr/th[5]")).getText()); 
	 }
	 
	 @AfterClass
     public void teardown()  {
		 driver.quit();
      }
}