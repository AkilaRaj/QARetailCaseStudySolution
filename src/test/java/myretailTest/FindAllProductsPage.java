package myretailTest;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.Assert;

public class FindAllProductsPage {

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
	 public void verifyAllProducts() {
		 try {
			driver.findElement(By.id("findAll")).click();
			 WebElement table = driver.findElement(By.tagName("table"));
			 List<WebElement> rows = table.findElements(By.tagName("tr"));
			 Iterator<WebElement> i = rows.iterator();
			 System.out.println("Table has following content");
			 while(i.hasNext()){
			     WebElement row=i.next();
			     List<WebElement> columns= row.findElements(By.tagName("td"));
			     Iterator<WebElement> j = columns.iterator();
			         while(j.hasNext()){
			             WebElement column = j.next();
			             System.out.print(column.getText());
			             System.out.print("    |  ");
			         }System.out.println("");
			     }
		} catch (StaleElementReferenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 System.out.println("Table content is printed");
	 }
	 
	 @Test
	 public void searchProduct() throws InterruptedException {
		 try {
			driver.findElement(By.id("productId")).sendKeys("010001");
			 driver.findElement(By.id("searchProduct")).click();
			 Assert.assertEquals("Milk", driver.findElement(By.xpath("//*[@id='productTable']/tbody/tr[2]/td[2]")).getText());
			 Assert.assertEquals("Full Cream Milk, 1 Gallon", driver.findElement(By.xpath("//*[@id='productTable']/tbody/tr[2]/td[3]")).getText());
			 Assert.assertEquals("Land O Lakes", driver.findElement(By.xpath("//*[@id='productTable']/tbody/tr[2]/td[4]")).getText());
			 Assert.assertEquals("100", driver.findElement(By.xpath("//*[@id='productTable']/tbody/tr[2]/td[5]")).getText());
			 Thread.sleep(3000);
		} catch (StaleElementReferenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 
	 @Test
	 public void searchWithoutProductId() throws InterruptedException {
		 
		 try {
			driver.findElement(By.id("findAll")).click();
			 driver.findElement(By.id("searchProduct")).click();
			 Assert.assertEquals("No product id entered to search!!!", driver.findElement(By.id("error-msg")).getText());
			 Thread.sleep(3000);
		} catch (StaleElementReferenceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 @AfterClass
     public void teardown()  {
		 driver.quit();
      }
}
