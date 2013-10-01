/**
 * Copyright 2013 YarcData LLC All Rights Reserved.
 */
package com.yarcdata.tests.automation.libraries.common;

/**
 * <p>
 * Object library - To invoke methods from Object Library and Functions Library in order to test UI actions.
 * </p>
 * @author Venkatachalam
 */

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ObjectsLibrary {

	protected WebElement element;

	/**
	 * URIKA LOGIN
	 */

	//Urika > Login > Username
	@FindBy(id = "username")    //*** Web elements Declaration
	public WebElement objectTextFieldUsername;
	public void textFieldUsernameEnterData (String sUserName){
		objectTextFieldUsername.sendKeys(sUserName);
	}//method end

	//Urika > Login > Password
	@FindBy(id = "password")
	public WebElement objectTextFieldPassword;
	public void textFieldPasswordEnterData (String sPassword){
		objectTextFieldPassword.sendKeys(sPassword);
	}//method end

	//Urika > Login > SignIn
	@FindBy(id = "login")
	public WebElement objectPushButtonSignIn;
	public void pushButtonSignInClick (){
		objectPushButtonSignIn.click();
	}//method end

	//Urika > Username Button 
	@FindBy(id = "logoutDropdownButton")
	public WebElement objectDropdownSignout;
	public void dropdownButtonSignoutClick (){
		objectDropdownSignout.click();
	}//method end

	//Urika > Username Button > Signout
	@FindBy(xpath = ".//*[@id='logoutButton']")
	public WebElement objectPushButtonSignout;
	public void pushButtonSignoutClick (){
		objectPushButtonSignout.click();
	}//method end


	/**
	 * Left hand side tabs Page navigation Links
	 */

	//Urika > Dashboard
	@FindBy(xpath = ".//*[@id='leftnav-dashboard']")    //*** Web elements Declaration
	public WebElement objectPushButtonDashboard;
	public void pushButtonDashboardClick (){
		objectPushButtonDashboard.click();
	}//method end
	
	//Urika > DB Wizard
	@FindBy(xpath = ".//*[@id='leftnav-wizard']")    //*** Web elements Declaration
	public WebElement objectPushButtonDBWizard;
	public void pushButtonDbwizardClick (){
		objectPushButtonDBWizard.click();
	}//method end

	//Urika > Manage Databases
	@FindBy(xpath = ".//*[@id='leftlav-manage-db']")    //*** Web elements Declaration
	public WebElement objectPushButton_ManageDB;
	public void pushButtonManageDBClick (){
		objectPushButton_ManageDB.click();
	}//method end

	//Urika > Manage Data
	@FindBy(xpath = ".//*[@id='leftnav-manage-data']") 	//*** Web elements Declaration
	public WebElement objectPushButtonManageData;
	public void pushButtonManageDataClick (){
		objectPushButtonManageData.click();
	}//method end

	//Urika > Manage Rules
	@FindBy(xpath = ".//*[@id='leftnav-manage-rules']")    //*** Web elements Declaration
	public WebElement objectPushButtonManageRule;
	public void pushButtonManageRuleClick (){
		objectPushButtonManageRule.click();
	}//method end

	//Urika > Manage Queue
	@FindBy(xpath = ".//*[@id='leftnav-manage-queue']")    //*** Web elements Declaration
	public WebElement objectPushButtonManageQueue;
	public void pushButtonManageQueueClick (){
		objectPushButtonManageQueue.click();
	}//method end

	//Urika > Manage Users
	@FindBy(xpath = ".//*[@id='leftnav-manage-users']")    //*** Web elements Declaration
	public WebElement objectPushButtonManageUsers;
	public void pushButtonManageUsersClick (){
		objectPushButtonManageUsers.click();
	}//method end

	//Urika > View Logs
	@FindBy(xpath = ".//*[@id='leftnav-view-logs']")    //*** Web elements Declaration
	public WebElement objectPushButtonViewLogs;
	public void pushButtonViewLogsClick (){
		objectPushButtonViewLogs.click();
	}//method end

	//Urika > Search Logs
	@FindBy(xpath = ".//*[@id='leftnav-search-logs']")    //*** Web elements Declaration
	public WebElement objectPushButtonSearchLogs;
	public void pushButtonSearchLogsClick (){
		objectPushButtonSearchLogs.click();
	}//method end

	//Urika > SPARQL Query
	@FindBy(xpath = ".//*[@id='leftnav-sparql-query']")    //*** Web elements Declaration
	public WebElement objectPushButtonSparqlQuery;
	public void pushButtonSparqlQueryClick (){
		objectPushButtonSparqlQuery.click();
	}//method end

	//Urika > SPARQL Update
	@FindBy(xpath = ".//*[@id='leftnav-sparql-update']")    //*** Web elements Declaration
	public WebElement objectPushButtonSparqlUpdate;
	public void pushButtonSparqlUpdateClick (){
		objectPushButtonSparqlUpdate.click();
	}//method end

	//Urika > Query History
	@FindBy(xpath = ".//*[@id='leftnav-query-history']")    //*** Web elements Declaration
	public WebElement objectPushButtonQueryHistory;
	public void pushButtonQueryHistoryClick (){
		objectPushButtonQueryHistory.click();
	}//method end

}//class end