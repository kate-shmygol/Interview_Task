package com.yandex.testTask;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class LoginTest {
/* автотест на вход в почтовый ящик на www.yandex.ru.  */
// Scenario:
// open GoogleChrome browser and expand to full screen
// go to www.yandex.ru
// find an input mail field
// type in the input field "testmail"
// press button ВОЙТИ
// find an input password field
// type in the input field "testpassword"
// press button ВОЙТИ

WebDriver driver;

	@BeforeMethod
	public void setUp() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.navigate().to("https://www.yandex.ru/");
	}

	@Test
	public void loginTest() {
		click(By.cssSelector("[href='https://passport.yandex.ru/auth?origin=home_yandexid&retpath=https%3A%2F%2Fyandex.ru&backpath=https%3A%2F%2Fyandex.ru']"));
		type(By.id("passp-field-login"), "testmail");
		click(By.id("passp:sign-in"));
		type(By.id("passp-field-passwd"), "testpassword");
		click(By.id("passp:sign-in"));
	}

	public void type(By locator, String text) {
		click(locator);
		driver.findElement(locator).sendKeys(text);
		click(locator);
	}

	public void click(By locator) {
		driver.findElement(locator).click();
	}

	@AfterMethod
	public void tearDown() {
		driver.quit();
	}
}
