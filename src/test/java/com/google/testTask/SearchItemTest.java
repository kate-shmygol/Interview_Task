package com.google.testTask;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class SearchItemTest {
	/* В поисковую строку вводятся слова  «купить кофемашину bork c804»,
	результатов больше 10 и в выдаче присутствует mvideo.ru. */

	// Scenario:
	// open GoogleChrome browser and expand to full screen
	// go to www.google.com
	// find an input field
	// type in the input field "купить кофемашину bork c804"
	// press Enter
	// check that the number of results is greater than 10
	// check that mvideo.ru is in the results list

	WebDriver driver;
	List<WebElement> list;

	@BeforeMethod
	public void setUp() {
		// open GoogleChrome browser
		driver = new ChromeDriver();
		// expand browser to full screen
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		// go to www.google.com
		driver.navigate().to("https://www.google.com/");
	}

	@Test
	public void searchItemTest() {
		// type in the input field "купить кофемашину bork c804"
		type(By.className("gLFyf"), "купить кофемашину bork c804");
		// press Enter
		Actions actions = new Actions(driver);
		actions.sendKeys(Keys.ENTER).build().perform();
		// results - list of links
		this.list = driver.findElements(By.cssSelector("div:nth-child(3) div:nth-child(3) cite"));
		getQuantityResults();
		isNeededShopInList("mvideo.ru");
	}

	// number of results is GREATER than 10
	public void getQuantityResults() {
		WebElement element = driver.findElement(By.cssSelector("td:nth-child(3) > .fl"));
//		System.out.println("element: " + element.getText());
		// check next page (2) of results
		Assert.assertEquals("2", element.getText());
	}

	public void isNeededShopInList(String shopName) {
		for(WebElement element : this.list) {
			if (element.getText().contains(shopName)) {
				System.out.println("Shop mvideo.ru IS in the results list");
				Assert.assertTrue(element.getText().contains(shopName));
			} else {
				System.out.println("Shop mvideo.ru ISN'T in the results list");
				Assert.assertFalse(element.getText().contains(shopName));
			}
		}
	}

	public void type(By locator, String text) {
		click(locator);
		driver.findElement(locator).clear();
		driver.findElement(locator).sendKeys(text);
	}

	public void click(By locator) {
		driver.findElement(locator).click();
	}

	@AfterMethod(enabled = false)
	public void tearDown() {
		driver.quit();
	}
}
