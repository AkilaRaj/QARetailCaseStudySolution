package myretailTest;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class FindAllProductsOutOfStock {

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
		 
		 driver.findElement(By.id("findAllProductsOutOfStock")).click();
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
		 System.out.println("Table content is printed");
	 }
	 
	 @AfterClass
     public void teardown()  {
		 driver.quit();
      }
}
