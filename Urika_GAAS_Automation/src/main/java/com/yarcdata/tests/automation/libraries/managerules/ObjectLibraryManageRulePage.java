/**
 * Copyright 2013 YarcData LLC All Rights Reserved.
 */
package com.yarcdata.tests.automation.libraries.managerules;

/**
 * <p>
 * Object library - To invoke methods from Object Library and Functions Library in order to test UI actions.
 * </p>
 * @author Venkatachalam
 */

import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;

public class ObjectLibraryManageRulePage {
	protected WebElement element;
	/**
	 * Manage Rule Web Elements for Rule Management
	 */

	//Urika > Manage Rules > Page Title
	@FindBy(xpath = ".//*[@id='content']/h1")
	public WebElement textPageTitle;
	public String manageRulePageTitle (){
		return textPageTitle.getText();
	}//method end

	//Urika > Manage Rules > Rules header
	@FindBy(xpath = ".//*[@id='content']/div/div/div[1]/h2")
	public WebElement textPageHeader;
	public String manageRulePageHeader(){
		return textPageHeader.getText();
	}//method end	

	//Urika > Manage Rules > Rule Set header
	@FindBy(xpath = ".//*[@id='ruleManageTable']/thead/tr/th[1]")
	public WebElement textRuleSetHeader;
	public String manageRuleRuleSetHeader (){
		return textRuleSetHeader.getText();
	}//method end	

	//Urika > Manage Rules > Date header
	@FindBy(xpath = ".//*[@id='ruleManageTable']/thead/tr/th[2]")
	public WebElement textDateHeader;
	public String manageRuleDateHeader (){
		return textDateHeader.getText();
	}//method end	

	//Urika > Manage Rules > User header
	@FindBy(xpath = ".//*[@id='ruleManageTable']/thead/tr/th[3]")
	public WebElement textUserHeader;
	public String manageRuleUserHeader (){
		return textUserHeader.getText();
	}//method end	

	//Urika > Manage Rules > Action header
	@FindBy(xpath = ".//*[@id='ruleManageTable']/thead/tr/th[4]")
	public WebElement textActionHeader;
	public String manageRuleActionHeader (){
		return textActionHeader.getText();
	}//method end

	//Urika > Manage Rules > Search > Textbox
	@FindBy(xpath = ".//*[@id='ruleManageTable_filter']/label/input")    
	public WebElement textFieldManageRuleSearch;
	public void manageRuleSearch (String sNameofRule){
		textFieldManageRuleSearch.sendKeys(sNameofRule);
	}//method end

	//Urika > Manage Rule > Search > Error Message
	@FindBy(xpath = ".//*[@id='ruleManageTable']/tbody/tr/td")    
	public WebElement textManageRuleNoData;
	public String manageRuleSearchErrorMsg (){
		return textFieldManageRuleSearch.getText();
	}//method end

	//Urika > Manage Rule > Pagination > Previous button
	@FindBy(xpath = ".//*[@id='ruleManageTable_wrapper']/div[2]/div[2]/div/ul")     //*** Web elements Declaration
	public WebElement buttonPagePrevious;
	public void navigateThroughPreviousButton() {
		buttonPagePrevious.findElement(By.linkText("← Previous")).click();
	}

	//Urika > Manage Rule > Pagination > Next button
	@FindBy(xpath = ".//*[@id='ruleManageTable_wrapper']/div[2]/div[2]/div/ul")     //*** Web elements Declaration
	public WebElement buttonPageNext;
	public boolean navigateThroughNextButton() {
		if(manageRuleNextButton()){
			buttonPageNext.findElement(By.linkText("Next →")).click();
			return true;
		}else{
			return false;
		}
	}

	@FindBy(xpath = ".//*[@id='ruleManageTable_wrapper']/div[2]/div[2]/div")
	public WebElement buttonManageRuleNext;
	public boolean manageRuleNextButton() 
	{
		boolean status = true;
		List<WebElement> allRows = buttonManageRuleNext.findElements(By.tagName("ul"));
		for(int i = 0;i < allRows.size(); i++){
			List<WebElement> allCols = ((WebElement) allRows.get(i)).findElements(By.tagName("li"));
			for (int k=0; k<allCols.size();k++) {
				if (allCols.get(k).getAttribute("class").equals("next disabled")) {
					status = false;
				}
				else {
					status = true;
				}
			}
		}
		return status;
	}

	//Urika > Manage Rules > Add File
	@FindBy(xpath = ".//*[@id='content']/div/div/div[2]/div[3]/a")    
	public WebElement buttonAddRule;
	public void clickAddRuleButton (){
		buttonAddRule.click();
	}//method end

	//Urika > Manage Rules > Datatable > Delete Button > ok
	@FindBy(xpath = "html/body/div[4]/div[2]/a[1]")
	public WebElement buttonDeleteOk;
	public void clickDeleteOk(){
		buttonDeleteOk.click();
	}//method end

	//Urika > Manage Rules > Datatable > Delete Button > cancel
	@FindBy(xpath = "html/body/div[4]/div[2]/a[2]")
	public WebElement buttonDeleteCancel;
	public void clickDeleteCancel(){
		buttonDeleteCancel.click();
	}//method end	

	//Urika > Manage Rules > Added Successfully message
	@FindBy(xpath = ".//*[@id='status']/div")
	public WebElement textRuleFileAddedMsg;
	public String ruleFileAddedMsg(){
		return textRuleFileAddedMsg.getText();
	}//method end

	//Urika > Manage Rules > Added Successfully message > Close X Button
	@FindBy(xpath = ".//*[@id='status']/div/button")
	public WebElement buttonRuleFileAddedMsg;
	public void ruleFileAddedCloseButton(){
		buttonRuleFileAddedMsg.click();
	}//method end

	//Urika > Manage Rules > info Button > Rule Name
	@FindBy(xpath = ".//*[@id='ruleNameInfo']")
	public WebElement textRuleFileName;
	public String ruleFileInfoRuleName(){
		return textRuleFileName.getText();
	}//method end

	//Urika > Manage Rules > info Button > Description
	@FindBy(xpath = ".//*[@id='ruleDescription']")
	public WebElement textRuleFileDescription;
	public String ruleFileInfoRuleDescription(){
		return textRuleFileDescription.getText();
	}//method end

	//Urika > Manage Rules > Info Button > Close X Button
	@FindBy(xpath = ".//*[@id='viewRuleInfo']/div[1]/button")
	public WebElement buttonRuleFileClose;
	public void ruleFileInfoPopupClose(){
		buttonRuleFileClose.click();
	}//method end		

	//Urika > Manage Rules > rule Table
	@FindBy(id = "ruleManageTable")
	public WebElement tableRuleManagement;
	public String[] ruleManagementTable(int param){
		String str[] = new String[10];
		List<WebElement> allRows = tableRuleManagement.findElements(By.tagName("tr"));
		for(int i = 1;i < allRows.size(); i++){
			List<WebElement> allCols = ((WebElement) allRows.get(i)).findElements(By.tagName("td"));
			if (allCols.size()>1) {
				WebElement allCell =(WebElement) allCols.get(param);
				str[i-1] = allCell.getText();
			}
		}
		int count =0;
		for (int k = 0; k<str.length;k++){
			if (str[k] == null){
			}
			else{
				count++;
			}
		}
		String str1[] = new String[count];
		for (int r = 0; r<count;r++){
			str1[r]= str[r];
		}
		return str1;
	}

	//Urika > Manage Rules > Rule Table > info button for each rule
	@FindBy(id = "ruleManageTable")     
	public WebElement objectInfoRuleManagement;
	public String[] infoRuleManagement(String sNameYourRule)
	{
		String str[] = new String[10];
		List<WebElement> tr = tableRuleManagement.findElements(By.tagName("tr"));
		for (int m=1; m<tr.size();m++) {
			if (tr.get(m).getText().contains(sNameYourRule)) {
				List<WebElement> td = tr.get(m).findElements(By.tagName("td"));
				for (int k=0; k<td.size();k++) {
					if (td.get(k).getText().contains(sNameYourRule)) {
						String s = "//tr["+m+"]/td[4]/a[1]";
						tableRuleManagement.findElement(By.xpath(s)).click();
					}
					else {
					}
				}
			}
			else {
			}
		}	
		return str;
	}//method end

	//Urika > Manage Rules > Rule Table > delete button for each rule
	@FindBy(id = "ruleManageTable")     
	public WebElement objectDeleteRuleManagement;
	public String[] deleteRuleManagement(String sNameYourRule)
	{
		String str[] = new String[10];
		List<WebElement> tr = tableRuleManagement.findElements(By.tagName("tr"));
		for (int m=1; m<tr.size();m++) {
			if (tr.get(m).getText().contains(sNameYourRule)) {
				List<WebElement> td = tr.get(m).findElements(By.tagName("td"));
				for (int k=0; k<td.size();k++) {
					if (td.get(k).getText().equals(sNameYourRule)) {
						String s = "//tr["+m+"]/td[4]/a[2]";
						tableRuleManagement.findElement(By.xpath(s)).click();
					}
					else {
					}
				}
			}
			else {
			}
		}	
		return str;
	}//method end


	//TODO : Urika > Manage Rule > Data Table > Loading file
	@FindBy(xpath = ".//*[@id='ruleTable']/tbody/tr/td")
	public WebElement tableDataRule;
	public String[] dataTable(String sNameYourArtifact){
		String str[] = new String[10];
		List<WebElement> allRows = tableDataRule.findElements(By.tagName("tr"));
		for (int m=0; m<allRows.size();m++) {
			if (allRows.get(m).getText().contains(sNameYourArtifact)){
				List<WebElement> allCols = allRows.get(m).findElements(By.tagName("td"));
			}
		}
		return str;
	}//method end

	//Urika > Manage Rule > Delete Popup
	@FindBy(xpath = "html/body/div[4]")
	public WebElement deletePopup;
	public void deletemodelPopup(){
		deletePopup.clear();
	}

}//class end