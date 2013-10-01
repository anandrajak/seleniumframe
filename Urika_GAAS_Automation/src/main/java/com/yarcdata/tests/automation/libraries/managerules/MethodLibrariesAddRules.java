package com.yarcdata.tests.automation.libraries.managerules;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriver.Options;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import com.yarcdata.tests.automation.libraries.common.FunctionsLibrary;
import com.yarcdata.tests.automation.libraries.common.MethodLibrary;
import com.yarcdata.tests.automation.libraries.common.ObjectsLibrary;
import com.yarcdata.tests.automation.libraries.common.SeleniumUtilities;

public class MethodLibrariesAddRules extends MethodLibrary{
	FunctionsLibrary functionsLibrary = new FunctionsLibrary(); 

	public String ruleInputFile;
	/**
	 * Verifying the Web elements in Add Rules pop up
	 * @return driver
	 */
	public WebDriver verifyAddRuleComponents() {	
		ObjectsLibrary  objectsLibrary = PageFactory.initElements(driver, ObjectsLibrary.class);
		ObjectLibraryManageRulePage manageRulePageObjectLibrary =  PageFactory.initElements(driver, ObjectLibraryManageRulePage.class);
		ObjectLibraryAddRules addRulesObjectLibrary = PageFactory.initElements(driver, ObjectLibraryAddRules.class);

		objectsLibrary.pushButtonManageRuleClick();
		manageRulePageObjectLibrary.clickAddRuleButton();

		Assert.assertTrue(addRulesObjectLibrary.textFieldAddRuleFileLocation.isDisplayed());
		Reporter.log("Local File path text box is present in the Add Rule modal popup");

		Assert.assertTrue(addRulesObjectLibrary.textErrorMsgNullRuleName.getText().contains("* Name is required."));
		Reporter.log("Default messages are present in the Add Rule modal popup");

		Assert.assertTrue(addRulesObjectLibrary.textFieldNameOfRule.isDisplayed());
		Reporter.log("Name your rules set text box is present in the Add Rule modal popup");

		Assert.assertTrue(addRulesObjectLibrary.textFieldAddruleComments.isDisplayed());
		Reporter.log("Comments Text area box is present in the Add Rule modal popup");

		Assert.assertTrue(addRulesObjectLibrary.pushButtonAddRuleSubmit.isDisplayed());
		Assert.assertTrue(addRulesObjectLibrary.pushButtonAddRuleSubmit.isEnabled());
		Reporter.log("Submit button is present in the Add Rule modal popup and is enabled");

		Assert.assertTrue(addRulesObjectLibrary.pushButtonCancelRule.isDisplayed());
		Assert.assertTrue(addRulesObjectLibrary.pushButtonCancelRule.isEnabled());
		Reporter.log("Cancel button is present in the Add Rule modal popup and is enabled");

		addRulesObjectLibrary.pushButtonCancelClick();

		return driver;
	}
	/**
	 * Add rule file in Manage Rules
	 * @param sNameYourRule
	 * @param sComment
	 * @param startCount
	 * @return driver
	 */
	public WebDriver addMultipleRuleFiles (String sNameYourRule, String sComment, int startCount, int dpCount) {
		ObjectsLibrary objectsLibrary = PageFactory.initElements(driver, ObjectsLibrary.class);
		ObjectLibraryManageRulePage manageRulePageObjectLibrary =  PageFactory.initElements(driver, ObjectLibraryManageRulePage.class);
		ObjectLibraryAddRules addRulesObjectLibrary = PageFactory.initElements(driver, ObjectLibraryAddRules.class);
		ruleInputFile = System.getProperty("ruleset.file.location");

		if (startCount == 1) {
			objectsLibrary.pushButtonManageRuleClick();
			functionsLibrary.functionWaitForAnObject(1);
		}
		//driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);		
		manageRulePageObjectLibrary.clickAddRuleButton();
		addRulesObjectLibrary.textFieldLocalFileAddruleFilelocation(ruleInputFile);
		addRulesObjectLibrary.textFieldNameOfRule(sNameYourRule);
		addRulesObjectLibrary.textFieldAddRuleCommentsEnterData(sComment);
		addRulesObjectLibrary.pushButtonAddruleSubmitClick();
		SeleniumUtilities.waitForTextObject(20, By.xpath(".//*[@id='status']/div"), "The rule "+sNameYourRule +" was added.");

		String Message = manageRulePageObjectLibrary.ruleFileAddedMsg();
		//functionsLibrary.functionWaitForAnObject(2);
		String ExtactMessage = Message.replaceAll("\\r|x\\n", "");
		Assert.assertEquals(ExtactMessage, "The rule "+sNameYourRule +" was added.");
		//Capturing screenshot and saving it in a folder
		if (dpCount == startCount) {
			for (int i = 1; i <= dpCount; dpCount--)
			{
				functionsLibrary.functionWaitForAnObject(1);
				//SeleniumUtilities.waitForAnObject(20, manageRulePageObjectLibrary.buttonRuleFileAddedMsg);
				Assert.assertEquals(manageRulePageObjectLibrary.buttonRuleFileAddedMsg.isDisplayed(), true);
				Reporter.log("The 'Rule file added' message box is closed");
				manageRulePageObjectLibrary.ruleFileAddedCloseButton();	
			}
			
			do {
				try {
					String className = Thread.currentThread().getStackTrace()[2].getClassName();
					className = className.substring(className.lastIndexOf('.') + 1);
					String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
					SeleniumUtilities.createFile(className,methodName);
				} catch (Exception e) {
					e.printStackTrace();
				} 
			}while(manageRulePageObjectLibrary.navigateThroughNextButton());
			//manageRulePageObjectLibrary.navigateThroughPreviousButton();
		}
		return driver;
	}

	/**
	 * Add data using invalid URL or file extension in Manage Data
	 * @param sNameYourRule
	 * @param sComment
	 * @param sErrorMessages
	 * @param startCount
	 * @return driver
	 * @throws InterruptedException
	 */
	public WebDriver addInvalidRuleFiles (String sNameYourRule, String sComment, String sRuleFilePath, String sErrorMessages, int startCount) {

		ObjectsLibrary objectsLibrary = PageFactory.initElements(driver, ObjectsLibrary.class);
		ObjectLibraryManageRulePage manageRulePageObjectLibrary =  PageFactory.initElements(driver, ObjectLibraryManageRulePage.class);
		ObjectLibraryAddRules addRulesObjectLibrary = PageFactory.initElements(driver, ObjectLibraryAddRules.class);
		FunctionsLibrary functionsLibrary = new FunctionsLibrary();
		ruleInputFile = System.getProperty("ruleset.file.location");

		if (startCount == 1) {
			objectsLibrary.pushButtonManageRuleClick();
			functionsLibrary.functionWaitForAnObject(1);
		}
	//	driver.manage().timeouts().setScriptTimeout(30, TimeUnit.SECONDS);
		manageRulePageObjectLibrary.clickAddRuleButton();
		if (sRuleFilePath.equalsIgnoreCase("1")) {
			addRulesObjectLibrary.textFieldLocalFileAddruleFilelocation("");
		}
		else{
			addRulesObjectLibrary.textFieldLocalFileAddruleFilelocation(ruleInputFile);
		}
		addRulesObjectLibrary.textFieldNameOfRule(sNameYourRule);
		addRulesObjectLibrary.textFieldAddRuleCommentsEnterData(sComment);
		//functionsLibrary.functionWaitForAnObject(1);
		addRulesObjectLibrary.pushButtonAddruleSubmitClick();

		//To check the error message if the rule file path is null
		if (sRuleFilePath.equalsIgnoreCase("1")) {
			//To check the error message if both the rule name and rule file path is null
			if(sNameYourRule.equalsIgnoreCase("")) {
				String textColorNullRuleName = addRulesObjectLibrary.nullRuleNameErrorMsg().getCssValue("color");
				Assert.assertEquals("#333333",Color.fromString(textColorNullRuleName).asHex().toUpperCase());

				String textColorNullRuleFilePath = addRulesObjectLibrary.nullRuleFilePathErrorMsg().getCssValue("color");
				Assert.assertEquals("#333333",Color.fromString(textColorNullRuleFilePath).asHex().toUpperCase());
			}
			else{
				String textColorNullRuleFilePath = addRulesObjectLibrary.nullRuleFilePathErrorMsg().getCssValue("color");
				Assert.assertEquals("#333333",Color.fromString(textColorNullRuleFilePath).asHex().toUpperCase());
			}
		}
		//To check the error message if the rule name is null
		else if(sNameYourRule.equalsIgnoreCase("")) {
			String textColorNullRuleName = addRulesObjectLibrary.nullRuleNameErrorMsg().getCssValue("color");
			Assert.assertEquals("#333333",Color.fromString(textColorNullRuleName).asHex().toUpperCase());	
		}
		//To check the predefined error messages
		else {
			String errorMessage = addRulesObjectLibrary.textRuleErrorMessage();
			errorMessage = errorMessage.replaceAll("\\r|x\\n", "");
			Assert.assertEquals(sErrorMessages, errorMessage);
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

		addRulesObjectLibrary.pushButtonCancelClick();
		//TODO: add validations
		return driver;
	}
}
