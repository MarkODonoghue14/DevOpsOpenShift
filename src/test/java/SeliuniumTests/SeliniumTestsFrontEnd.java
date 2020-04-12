package SeliuniumTests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class SeliniumTestsFrontEnd {
		
		
		@Test
		public void testCreateBook() {
			
			System.setProperty("webdriver.chrome.driver", "C:/Users/Mark/Downloads/chromedriver/chromedriver.exe");
			
			WebDriver driver = new ChromeDriver();		
			driver.manage().window().maximize();
			
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			
			driver.get("http://localhost:3000");
			System.out.println(driver.getTitle());
			
			WebElement createPlaylistBtn = driver.findElement(By.className("addButton"));
			createPlaylistBtn.click();
			
			driver.findElement(By.id("title")).sendKeys("Test book title");
			driver.findElement(By.id("description")).sendKeys("Test book description");
			driver.findElement(By.id("genre")).sendKeys("Test book genre");
			driver.findElement(By.id("author")).sendKeys("Test book author");
			
			driver.close();
		}

	}
