package com.automation.utils;

import java.io.File;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;
import org.testng.Reporter;

public class CaptureScreenshot {
	static String screenshotPath = null;

	public static void takeScreenshot(WebDriver driver, String name) {

		try {
			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File source = screenshot.getScreenshotAs(OutputType.FILE);

			String imageName = name + ".png";
		        
			screenshotPath = "./Screenshots/" + imageName;
			File destiny = new File(screenshotPath);

			FileHandler.copy(source, destiny);
			attachCaptureScreenshot(screenshotPath);

		} catch (Exception ex) {
			System.out.println("Đã xảy ra lỗi chụp màn hình");
			ex.printStackTrace();
		}
	}

	public static void attachCaptureScreenshot(String fileName) {
		try {
			System.setProperty("org.uncommons.reportng.escape-output", "false");
			File f = new File(fileName);
			String absolutePath = f.getAbsoluteFile().toURI().toString();
			Reporter.log("<br><a title='Screenshot' href='" + absolutePath + "'>" + 
                    fileName + "<br>" + "<img alt='" + f.getName() + "' src='" + absolutePath + "' height='243' width='418'></a><br>"); 
		} catch (Exception ex) {
			System.out.println("Đã xảy ra lỗi chụp màn hình");
			ex.printStackTrace();
		}
	}
}
