/**
 * Copyright 2013 YarcData LLC All Rights Reserved.
 */
package com.yarcdata.tests.automation.libraries.managerules;

/**
 * Object library - To invoke methods from Object Library and Functions Library in order to test UI actions.
 * @author Venkatachalam
 */

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ObjectLibraryAddRules {
	protected WebElement element;
	/**
	 * Manage Rules Web Elements for Add Rule Popup
	 */

	//Urika > Manage Rule > Add Rule > Rule pop up Header
	@FindBy(xpath = ".//*[@id='addRule']/div[1]/h3")
	public WebElement textPopUpTitle;
	public String popupTitle()	{
		return textPopUpTitle.getText();
	}

	//Urika > Manage Rule > Add Rule > Local Input
	@FindBy(id = "ruleFile")    //*** Web elements Declaration
	public WebElement textFieldAddRuleFileLocation;
	public void textFieldLocalFileAddruleFilelocation (String sRuleLocalFilePath){
		textFieldAddRuleFileLocation.sendKeys(sRuleLocalFilePath);
	}//method end

	//Urika > Manage Rule > Add Rule > Name of the rule
	@FindBy(id = "ruleNameInput")
	public WebElement textFieldNameOfRule;
	public void textFieldNameOfRule (String sNameofrule){
		textFieldNameOfRule.sendKeys(sNameofrule);
	}//method end

	//Urika > Manage Rule > Add Rule > Comments
	@FindBy(name = "comment")
	public WebElement textFieldAddruleComments;
	public void textFieldAddRuleCommentsEnterData (String sRuleComment){
		textFieldAddruleComments.sendKeys(sRuleComment);
	}//method end

	//Urika > Manage Rule > Add Rule > Submit
	@FindBy(id = "importRuleAction")
	public WebElement pushButtonAddRuleSubmit;
	public void pushButtonAddruleSubmitClick (){
		pushButtonAddRuleSubmit.click();
	}//method end

	//Urika > Manage Rule > Add Rule > Cancel
	@FindBy(id = "importRuleCancel")
	public WebElement pushButtonCancelRule;
	public void pushButtonCancelClick (){
		pushButtonCancelRule.click();
	}//method end	

	//Urika > Manage Rule > Add Rule > Warning
	@FindBy(xpath = ".//*[@id='ruleStatus']/div")    //*** Web elements Declaration
	public WebElement textRuleErrorMessage;
	public String textRuleErrorMessage (){
		return textRuleErrorMessage.getText();
	}//method end

	//Urika > Manage Rule > Add Rule > null artifact Error Message	
	@FindBy(id = "rulenameGroup")
	public WebElement textErrorMsgNullRuleName;
	public WebElement nullRuleNameErrorMsg (){
		return textErrorMsgNullRuleName;
	}//method end

	//Urika > Manage Rule > Add Rule > null Rule file path Error Message	
	@FindBy(id = "ruleGroup")
	public WebElement textErrorMsgNullRuleFilePath;
	public WebElement nullRuleFilePathErrorMsg (){
		return textErrorMsgNullRuleFilePath;
	}//method end

}//class end