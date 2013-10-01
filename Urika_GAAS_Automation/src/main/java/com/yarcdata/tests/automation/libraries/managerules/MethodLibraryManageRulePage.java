package com.yarcdata.tests.automation.libraries.managerules;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import com.yarcdata.tests.automation.libraries.common.FunctionsLibrary;
import com.yarcdata.tests.automation.libraries.common.MethodLibrary;
import com.yarcdata.tests.automation.libraries.common.ObjectsLibrary;
import com.yarcdata.tests.automation.libraries.common.SeleniumUtilities;
import com.yarcdata.tests.automation.libraries.managerules.ObjectLibraryManageRulePage;

public class MethodLibraryManageRulePage extends MethodLibrary{

	/**
	 * Verifying the Web elements in manage rules page
	 * @return driver
	 */
	public WebDriver verifyManageRuleComponents() {
		ObjectsLibrary objectsLibrary = PageFactory.initElements(driver, ObjectsLibrary.class);
		ObjectLibraryManageRulePage manageRulePageObjectLibrary =  PageFactory.initElements(driver, ObjectLibraryManageRulePage.class);

		Assert.assertTrue(objectsLibrary.objectPushButtonManageRule.isDisplayed());
		Reporter.log("The Manage Rules link is enabled and functional");

		objectsLibrary.pushButtonManageRuleClick();
		Assert.assertTrue(driver.getCurrentUrl().contains("rule-manage.jsp"));
		Reporter.log("The Manage Rules page has loaded successfully");

		Assert.assertEquals(driver.findElement(By.xpath(".//*[@id='content']/h1")).getText(), "Rule Management");
		Reporter.log("'Rule Management' text is present in the Manage Rules page");

		Assert.assertEquals(driver.findElement(By.xpath(".//*[@id='content']/div/div/div[1]/h2")).getText(), "Rules");
		Reporter.log("'Rules' text is present in the Manage Rules page");

		Assert.assertTrue(manageRulePageObjectLibrary.textRuleSetHeader.isDisplayed());
		String headerNameArtifact = manageRulePageObjectLibrary.manageRuleRuleSetHeader();
		Assert.assertEquals(headerNameArtifact, "Rule Set");
		Reporter.log("'Rule Set' column name text is visible");		

		Assert.assertTrue(manageRulePageObjectLibrary.textManageRuleNoData.isDisplayed());
		Reporter.log("No data available in table is visible");

		Assert.assertTrue(manageRulePageObjectLibrary.buttonAddRule.isDisplayed());
		Reporter.log("'Add Rule' button is present in the Manage Rules page");

		return driver;
	}

	/**
	 * Verifying the Web Elements for Rule Management Table 
	 * @return
	 */
	public WebDriver verifyRuleManagementTableComponents() {
		ObjectLibraryManageRulePage manageRulePageObjectLibrary =  PageFactory.initElements(driver, ObjectLibraryManageRulePage.class);

		Assert.assertTrue(manageRulePageObjectLibrary.textDateHeader.isDisplayed());
		String headerNameDate = manageRulePageObjectLibrary.manageRuleDateHeader();
		Assert.assertEquals(headerNameDate, "Date");
		Reporter.log("'Date' column name text is visible");

		Assert.assertTrue(manageRulePageObjectLibrary.textUserHeader.isDisplayed());
		String headerNameUser = manageRulePageObjectLibrary.manageRuleUserHeader();
		Assert.assertEquals(headerNameUser, "User");
		Reporter.log("'User' column name text is visible");

		Assert.assertTrue(manageRulePageObjectLibrary.textActionHeader.isDisplayed());
		String headerNameSize = manageRulePageObjectLibrary.manageRuleActionHeader();
		Assert.assertEquals(headerNameSize, "Action");
		Reporter.log("'Action' column name text is visible");

		Assert.assertTrue(manageRulePageObjectLibrary.textFieldManageRuleSearch.isDisplayed());
		Reporter.log("Search text box is visible");

		Assert.assertTrue(manageRulePageObjectLibrary.buttonPagePrevious.isDisplayed());
		Reporter.log("Previous button is visible");

		Assert.assertTrue(manageRulePageObjectLibrary.buttonManageRuleNext.isDisplayed());
		Reporter.log("Next button is visible");

		return driver;
	}

	/**
	 * Verifying the Rule in the Rule Management table
	 * @param sNameYourRule
	 * @param startCount
	 * @return
	 */
	public WebDriver verifyRuleFile (String sNameYourRule, int startCount)
	{
		FunctionsLibrary functionsLibrary = new FunctionsLibrary();  
		ObjectsLibrary  objectsLibrary = PageFactory.initElements(driver, ObjectsLibrary.class);
		ObjectLibraryManageRulePage manageRulePageObjectLibrary =  PageFactory.initElements(driver, ObjectLibraryManageRulePage.class);
		if (startCount == 1) {
			objectsLibrary.pushButtonManageRuleClick();
		}
		functionsLibrary.functionWaitForAnObject(2);
		String[] ruleName = manageRulePageObjectLibrary.ruleManagementTable(0);
		for (int i = 0; i< ruleName.length; i++) {
			if (ruleName[i].equalsIgnoreCase(sNameYourRule)) {
				Assert.assertEquals(ruleName[i], sNameYourRule);
			}
		}
		//Capturing screenshot and saving it in a folder		
		try {
			String className = Thread.currentThread().getStackTrace()[2].getClassName();
			className = className.substring(className.lastIndexOf('.') + 1);
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			SeleniumUtilities.createFile(className,methodName);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return driver;
	}//End Method

	/**
	 * Verifying the Rule info button and Deleting the rule 
	 * @param sNameYourRule
	 * @param sComment
	 * @param startCount
	 * @return
	 */
	public WebDriver deleteRuleFile (String sNameYourRule, String sComment, int startCount)
	{	
		FunctionsLibrary functionsLibrary = new FunctionsLibrary();
		ObjectsLibrary  objectsLibrary = PageFactory.initElements(driver, ObjectsLibrary.class);
		ObjectLibraryManageRulePage manageRulePageObjectLibrary =  PageFactory.initElements(driver, ObjectLibraryManageRulePage.class);
		if (startCount == 1) {
			objectsLibrary.pushButtonManageRuleClick();
			functionsLibrary.functionWaitForAnObject(2);
		}
	//	driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
		do 
		{
			String[] ruleName = manageRulePageObjectLibrary.ruleManagementTable(0);
			for (int i = 0; i< ruleName.length; i++) {
				if (ruleName[i].equalsIgnoreCase(sNameYourRule)) {
					//functionsLibrary.functionWaitForAnObject(1);
					manageRulePageObjectLibrary.infoRuleManagement(sNameYourRule);
					Assert.assertEquals(manageRulePageObjectLibrary.ruleFileInfoRuleName(), sNameYourRule);
					Assert.assertEquals(manageRulePageObjectLibrary.ruleFileInfoRuleDescription(),sComment);
					functionsLibrary.functionWaitForAnObject(1);
					Assert.assertTrue(manageRulePageObjectLibrary.buttonRuleFileClose.isDisplayed());
					manageRulePageObjectLibrary.ruleFileInfoPopupClose();
					functionsLibrary.functionWaitForAnObject(1);
					manageRulePageObjectLibrary.deleteRuleManagement(sNameYourRule);
					functionsLibrary.functionWaitForAnObject(2);					
					Assert.assertTrue(manageRulePageObjectLibrary.buttonDeleteOk.isDisplayed());
					manageRulePageObjectLibrary.clickDeleteOk();
					//functionsLibrary.functionWaitForAnObject(2);
					SeleniumUtilities.waitForTextObject(60, By.xpath(".//*[@id='status']/div"), "The rule "+sNameYourRule +" was deleted.");
				}
			}
			//functionsLibrary.functionWaitForAnObject(2);
		}while(manageRulePageObjectLibrary.navigateThroughNextButton());

		String[] ruleName = manageRulePageObjectLibrary.ruleManagementTable(0);
		for (int i = 0; i< ruleName.length; i++) {
			if (ruleName[i].equalsIgnoreCase(sNameYourRule)) {
				Assert.assertNotEquals(ruleName[i], sNameYourRule);
			}
		}
		manageRulePageObjectLibrary.navigateThroughPreviousButton();
		verifyRuleManagementTableComponents();

		//Capturing screenshot and saving it in a folder
		try {
			String className = Thread.currentThread().getStackTrace()[2].getClassName();
			className = className.substring(className.lastIndexOf('.') + 1);
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			SeleniumUtilities.createFile(className,methodName);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		Assert.assertEquals(manageRulePageObjectLibrary.buttonRuleFileAddedMsg.isDisplayed(), true);
		Reporter.log("The 'Rule file deleted' message box is closed");
		manageRulePageObjectLibrary.ruleFileAddedCloseButton();
		return driver;
	}

	/**
	 * Cancel button is clicked when the rule is getting deleted
	 * @param sNameYourRule
	 * @param startCount
	 * @return
	 */
	public WebDriver cancelDeleteRule (String sNameYourRule, int startCount)
	{	
		FunctionsLibrary functionsLibrary = new FunctionsLibrary();
		ObjectsLibrary  objectsLibrary = PageFactory.initElements(driver, ObjectsLibrary.class);
		ObjectLibraryManageRulePage manageRulePageObjectLibrary =  PageFactory.initElements(driver, ObjectLibraryManageRulePage.class);
		if (startCount == 1) {
			objectsLibrary.pushButtonManageRuleClick();
			functionsLibrary.functionWaitForAnObject(2);
		}
	//	driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
		do 
		{
			String[] ruleName = manageRulePageObjectLibrary.ruleManagementTable(0);
			for (int i = 0; i< ruleName.length; i++) {
				if (ruleName[i].equalsIgnoreCase(sNameYourRule)) {
					verifyRuleManagementTableComponents();
					functionsLibrary.functionWaitForAnObject(1);
					manageRulePageObjectLibrary.deleteRuleManagement(sNameYourRule);
					functionsLibrary.functionWaitForAnObject(2);
					Assert.assertTrue(manageRulePageObjectLibrary.buttonDeleteCancel.isDisplayed());
					manageRulePageObjectLibrary.clickDeleteCancel();		
				}
			}
		}while(manageRulePageObjectLibrary.navigateThroughNextButton());

		String[] ruleName = manageRulePageObjectLibrary.ruleManagementTable(0);
		for (int i = 0; i< ruleName.length; i++) {
			if (ruleName[i].equalsIgnoreCase(sNameYourRule)) {
				Assert.assertEquals(ruleName[i], sNameYourRule);
			}
		}
		return driver;
	}

	/**
	 * Searching for rule in the Rule Management table
	 * @param sNameYourRule
	 * @param startCount
	 * @return
	 */
	public WebDriver ruleSearch (String sNameYourRule, int startCount)
	{	
		FunctionsLibrary functionsLibrary = new FunctionsLibrary();
		ObjectsLibrary  objectsLibrary = PageFactory.initElements(driver, ObjectsLibrary.class);
		ObjectLibraryManageRulePage manageRulePageObjectLibrary =  PageFactory.initElements(driver, ObjectLibraryManageRulePage.class);
		if (startCount == 1) {
			objectsLibrary.pushButtonManageRuleClick();
			functionsLibrary.functionWaitForAnObject(2);
		}
		manageRulePageObjectLibrary.textFieldManageRuleSearch.clear();
		manageRulePageObjectLibrary.manageRuleSearch(sNameYourRule);
		functionsLibrary.functionWaitForAnObject(1);

		String[] ruleName = manageRulePageObjectLibrary.ruleManagementTable(0);
		for (int i = 0; i< ruleName.length; i++) {
			if (ruleName[i].equalsIgnoreCase(sNameYourRule)) {
				Assert.assertEquals(ruleName[i].toLowerCase(), sNameYourRule);
			}
			else if (ruleName[i].contains(sNameYourRule)) {
				Assert.assertTrue(ruleName[i].contains(sNameYourRule));
			}
			else {
				Assert.assertEquals(manageRulePageObjectLibrary.manageRuleSearchErrorMsg(), "No data available in table");
				manageRulePageObjectLibrary.textFieldManageRuleSearch.clear();
				Assert.assertTrue(manageRulePageObjectLibrary.tableRuleManagement.findElements(By.tagName("tr")).size()>1);
			}
		}
		//Capturing screenshot and saving it in a folder
		try {
			String className = Thread.currentThread().getStackTrace()[2].getClassName();
			className = className.substring(className.lastIndexOf('.') + 1);
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			SeleniumUtilities.createFile(className,methodName);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return driver;
	}
}