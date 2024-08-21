package com.automation.testcase;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeoutException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.automation.base.DriverInstance;
import com.automation.pom.LoginPage;
import com.automation.utils.CaptureScreenshot;
import com.automation.utils.PropertiesFileUtils;

public class TC_LoginTest extends DriverInstance {

	@Test(dataProvider = "Excel")
	public void TC01_LoginFirstAccount(String email, String password) throws IOException, InterruptedException {

		driver.get(PropertiesFileUtils.getProperty("appURL"));

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		WebElement iconSignIn = wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@href='/login']")));
		iconSignIn.click();

		LoginPage loginPage = new LoginPage(driver);
		PageFactory.initElements(driver, loginPage);
		loginPage.enterEmail(email);
		loginPage.enterPassword(password);
		loginPage.clickSignIn();
	
			WebElement iconSignOut = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@href='/logout']")));

			Assert.assertTrue(iconSignOut.isDisplayed(), "Sign Out button is not displayed");
	        iconSignOut.click();
			Thread.sleep(1000);
	}

	@DataProvider(name = "Excel")
	public Object[][] testDataGenerator() throws IOException {

		FileInputStream file = new FileInputStream("./data/assignment2_data_test.xlsx");
		XSSFWorkbook wk = new XSSFWorkbook(file);
		XSSFSheet ls = wk.getSheet("Login");
		int r = ls.getPhysicalNumberOfRows();

		Object[][] data = new Object[r][2];

		for (int i = 0; i < r; i++) {
			XSSFRow row = ls.getRow(i);
			XSSFCell email = row.getCell(0);
			XSSFCell password = row.getCell(1);
			data[i][0] = email.getStringCellValue();
			data[i][1] = password.getStringCellValue();
		}
		return data;
	}

	@AfterMethod
	public void takeScreenshot(ITestResult result) {
		if (ITestResult.FAILURE == result.getStatus()) {
			try {
				String email = (String) result.getParameters()[0];
				int index = email.indexOf("@");
				String accountName = email.substring(0, index);

				CaptureScreenshot.takeScreenshot(driver, accountName);
				System.out.println("Đã chụp màn hình" + result.getName() + "_" + accountName);

			} catch (Exception e) {
				System.out.println("Lỗi xảy ra Screenshot" + e.getMessage());
			}
		}
	}
}
