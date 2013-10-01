package com.yarcdata.tests.automation.libraries.common;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.AWTException;
import java.io.File;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class SeleniumUtilities extends MethodLibrary{
	public int timeoutInSeconds;

	public static void waitForAnObject(int waitTime, WebElement element) {
		wait = new WebDriverWait(driver, waitTime);
		//wait.until(ExpectedConditions.presenceOfElementLocated(element));	
		wait.until(ExpectedConditions.visibilityOf(element));
	}

	public static void waitForTextObject(int waitTime, By by, String message) {
		wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.textToBePresentInElement(by, message));	
	}

	public static void waitForPresenceOfElement(int waitTime, By by) {
		wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.presenceOfElementLocated(by));	
	}

	public static void waitForElementToClick(int waitTime, By by) {
		wait = new WebDriverWait(driver, waitTime);
		wait.until(ExpectedConditions.elementToBeClickable(by));	
	}
	
	/**
	 * To take screenshots and store them in corresponding folders
	 * @param cName
	 * @param mName
	 * @throws AWTException
	 * @throws IOException
	 */

	public static void createFile(String cName, String mName) throws AWTException, IOException {
		//String basePath = "/home/saranya/Desktop/june_workspace/June_Urika_GAAS_Automation/target/Screenshots/";	
		String basePath = System.getProperty ("user.dir")+"/"+"target"+"/"+"Screenshots"+"/";
		String classNameMethodName = Thread.currentThread().getStackTrace()[2].getClassName();
		Date date = new Date();
		Format formatter;
		formatter = new SimpleDateFormat("yyyyMMddHHmmssSS");
		String s = formatter.format(date);

		File file = new File(basePath + cName);
		String filename = mName;
		if (file.exists()) 
		{
			File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(file + "/" + new Throwable().fillInStackTrace().getStackTrace()[1].getMethodName() + "_" + s + ".png" ));
		}
	}	
}
