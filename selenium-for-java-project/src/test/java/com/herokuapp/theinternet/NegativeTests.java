package com.herokuapp.theinternet;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class NegativeTests {

	@Test(priority = 1,groups = { "negativeTests", "smokeTests" })
	public void incorrectUsernameTest() {
		System.out.println("starting incorrect username test");

		// create a driver
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		// sleep for 3 seconds
		sleep(3000);

		// maximize browser window
		driver.manage().window().maximize();

		// open test page
		String url = "https://the-internet.herokuapp.com/login";
		driver.get(url);
		System.out.println("page is opened");

		// enter username
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("incorrectUsername");
//		username.sendKeys("tomsmith");
		sleep(1000);

		// enter password
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("SuperSecretPassword!");
		sleep(3000);

		// click login button
		WebElement loginButton = driver.findElement(By.tagName("button"));
		loginButton.click();
		sleep(5000);
		
		//verifications
		WebElement errorMessage = driver.findElement(By.id("flash"));
		String expectedErrorMessage= "Your username is invalid!";
		String actualErrorMessage = errorMessage.getText();
		
		Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage),"Actual error message does not contain expected."
				+ "\nActual:" + actualErrorMessage + "\n Expected:" +expectedErrorMessage);
		
		//close browser
		driver.quit();

	}
	
	
	@Test(priority = 2 , groups = { "negativeTests" })
	public void incorrectPasswordTest() {
		System.out.println("starting incorrect password test");

		// create a driver
		System.setProperty("webdriver.chrome.driver", "src/main/resources/chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		// sleep for 3 seconds
		sleep(3000);

		// maximize browser window
		driver.manage().window().maximize();

		// open test page
		String url = "https://the-internet.herokuapp.com/login";
		driver.get(url);
		System.out.println("page is opened");

		// enter username
		WebElement username = driver.findElement(By.id("username"));
		username.sendKeys("tomsmith");
		sleep(1000);

		// enter password
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys("incorrectPassword");
		sleep(3000);

		// click login button
		WebElement loginButton = driver.findElement(By.tagName("button"));
		loginButton.click();
		sleep(5000);
		
		//verifications
		WebElement errorMessage = driver.findElement(By.id("flash"));
		String expectedErrorMessage= "Your password is invalid!";
		String actualErrorMessage = errorMessage.getText();
		
		Assert.assertTrue(actualErrorMessage.contains(expectedErrorMessage),"Actual error message does not contain expected."
				+ "\nActual:" + actualErrorMessage + "\n Expected:" +expectedErrorMessage);
		
		//close browser
		driver.quit();

	}


	private void sleep(long m) {
		try {
			Thread.sleep(m);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}