package fiery;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Assignment
{

	public static void main(String[] args) throws Throwable
	{
		String expectedAddToCart = "Added to Cart";
		WebDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		//Lauching Amazon India
		driver.get("https://www.amazon.in/");
		
		// Searching "Wrist Watches"
	    WebElement searchBar  =	driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
		searchBar.sendKeys("Wrist Watches");
		searchBar.submit();
		
		//In Filter Selecting "Leather" under Watch Band Material
		WebElement leatherFilter = driver.findElement(By.xpath("//span[text() = 'Leather']"));
		leatherFilter.click();
		
		//In Filter Selecting "Fastrack" in the Brand
		WebElement fastrackFilter = driver.findElement(By.xpath("//li[@id='p_123/230542']/span/a/span"));
		fastrackFilter.click();
		
		//Adding Cart 1st product in the 1st Row
		driver.findElement(By.xpath("//span[text()='Fastrack']/../../../following-sibling::div[@data-cy='price-recipe']/div/div/a/span/span/span[text() = '1,017']")).click();	
		Thread.sleep(3000);
		
		String parentId = driver.getWindowHandle();
		Set<String> allId = driver.getWindowHandles();
		for(String id : allId)
		{
			if(!id.equals(allId))
			{
				// Navigating to 2nd page 	By using getHandleMethod() 
				driver.switchTo().window(id);
			}
		}
		Thread.sleep(3000);
		
		
		WebElement addCartButton = 	driver.findElement(By.id("add-to-cart-button"));
	
		Actions act = new Actions(driver);
		act.doubleClick(addCartButton).perform();
			
		String actualaddToCartMessage = driver.findElement(By.xpath("//h1[@role='status']")).getText();
		
		if(actualaddToCartMessage.contains(expectedAddToCart))
		{
			System.out.println("Product is Succesfuuly Add to Cart");
		}
		else
		{
			System.out.println("Product is not Succesfuuly Add to Cart");
		}
		
	}
}
