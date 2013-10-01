package com.yarcdata.tests.automation.libraries.common;

import java.awt.AWTException;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class SeleniumTestBase extends SuiteConstants{
	
	protected MethodLibrary MethodsLibrary = new MethodLibrary();
	protected FunctionsLibrary FunctionsLibrary = new FunctionsLibrary(); 
	SuiteConstants sc = new SuiteConstants();
	/**
	 * To launch a browser and login to the application
	 * @throws FileNotFoundException
	 * @throws IOException
	 */

	@BeforeClass (alwaysRun = true)
	public void beforeTest() throws FileNotFoundException, IOException {
		sc.loadproperties();
		MethodsLibrary.methodLaunchBrowser(sBrowserType); 
		MethodsLibrary.methodLaunchUrika(sUrikaMachine);
		MethodsLibrary.methodLoginUrika(sUsername, sPassword);

		try {
			String className = this.getClass().getName();
			className = className.substring(className.lastIndexOf('.') + 1);
			FunctionsLibrary.deleteFile(className);
		} catch (AWTException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 *To quit from the application 
	 */
	
	@AfterClass (alwaysRun = true)
	public void afterTest() {
		MethodsLibrary.methodQuitUrika();
	}

}
