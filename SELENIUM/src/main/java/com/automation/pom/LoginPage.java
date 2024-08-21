package com.automation.pom;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.automation.utils.PropertiesFileUtils;

public class LoginPage {
	
	static WebDriver driver;
//	private WebDriverWait wait;
	
	public LoginPage(WebDriver driver) {
		
		LoginPage.driver = driver;
	}

	public void enterEmail(String email) throws IOException, InterruptedException {
		
		driver.findElement(By.xpath(PropertiesFileUtils.getProperty("email_login"))).sendKeys(email);
//		Thread.sleep(2000);
	}	
	public void enterPassword(String password) throws IOException, InterruptedException {
		
		driver.findElement(By.name(PropertiesFileUtils.getProperty("password"))).sendKeys(password);
//		Thread.sleep(2000);
	}
		

	
	public void clickSignIn() throws IOException, InterruptedException {
	
		driver.findElement(By.xpath(PropertiesFileUtils.getProperty("login_xpath"))).click();
//		Thread.sleep(2000);
	}
}
