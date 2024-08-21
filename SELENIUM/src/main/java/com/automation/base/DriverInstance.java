package com.automation.base;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class DriverInstance {
	
	protected WebDriver driver;
	
	@BeforeClass
	public void initDriverInstance() throws IOException {
		driver = new ChromeDriver();
		driver.manage().window().maximize();		
	}
	
	@AfterClass
	public void closeDriverInstance() {
		System.out.println("Finished");
		driver.quit();
	}
}
