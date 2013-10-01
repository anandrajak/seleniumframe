package com.yarcdata.tests.automation.libraries.common;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;


public class MethodLibrary {

	public static WebDriver driver;
	public static WebDriverWait wait;
	
	FunctionsLibrary FunctionsLibrary = new FunctionsLibrary ();
	ObjectsLibrary  ObjectsLibrary = PageFactory.initElements(driver, ObjectsLibrary.class);

	public WebDriver methodLaunchBrowser (String sBrowserType){
		driver = new FirefoxDriver(); 
//		wait = new WebDriverWait(driver, 10);
		driver.manage().window().maximize();
		return driver;
	}//End Method
	
	/**
	 * URL invoking
	 * @param sURL
	 * @return driver
	 */
	public WebDriver methodLaunchUrika (String sURL){
		driver.get(sURL);
		return driver;
	}//End Method

	/**
	 * Login Credentials for the Application
	 * @param sUsername
	 * @param sPassword
	 * @return driver
	 */
	public WebDriver methodLoginUrika (String sUsername, String sPassword)
	{
		ObjectsLibrary  ObjectsLibrary = PageFactory.initElements(driver, ObjectsLibrary.class);
		ObjectsLibrary.textFieldUsernameEnterData(sUsername);
		ObjectsLibrary.textFieldPasswordEnterData(sPassword);
		ObjectsLibrary.pushButtonSignInClick();
		SeleniumUtilities.waitForAnObject(10, ObjectsLibrary.objectPushButtonQueryHistory);
		return driver;

	}//End Method

	/**
	 * Application Sign out
	 * @return driver
	 */
	public WebDriver methodSignout (){
		ObjectsLibrary  ObjectsLibrary = PageFactory.initElements(driver, ObjectsLibrary.class);
		ObjectsLibrary.dropdownButtonSignoutClick();
		ObjectsLibrary.pushButtonSignoutClick();
		SeleniumUtilities.waitForAnObject(20, ObjectsLibrary.objectTextFieldUsername);
		return driver;
	}//End Method

	/**
	 * Closing the Driver
	 * @return driver
	 */
	public WebDriver methodQuitUrika (){
		driver.quit();
		return driver;
	}//End Method
}
