/**
 * Copyright 2013 YarcData LLC All Rights Reserved.
 */
package com.yarcdata.tests.automation.libraries.managedata;

/**
 * <p>
 * Object library - To invoke methods from Object Library and Functions Library in order to test UI actions.
 * </p>
 * @author Venkatachalam
 */

import java.util.List;

import org.testng.Assert;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class ObjectLibraryManageDataPage {
	protected WebElement element;
	/**
	 * Manage Data Web Elements for DataManagement page
	 */
	
	@FindBy(xpath = ".//*[@id='content']/h1")
	public WebElement textPageTitle;
	public String manageDataPageTitle (){
		return textPageTitle.getText();
	}//method end
	
	@FindBy(xpath = ".//*[@id='content']/div/div/div[1]/h2")
	public WebElement textPageHeader;
	public String manageDataPageHeader(){
		return textPageHeader.getText();
	}//method end	
	
	@FindBy(xpath = ".//*[@id='dataManageTable']/thead/tr/th[1]")
	public WebElement textArtifactHeader;
	public String manageDataArtifactHeader (){
		return textArtifactHeader.getText();
	}//method end	

	@FindBy(xpath = ".//*[@id='dataManageTable']/thead/tr/th[2]")
	public WebElement textDateHeader;
	public String manageDataDateHeader (){
		return textDateHeader.getText();
	}//method end	

	@FindBy(xpath = ".//*[@id='dataManageTable']/thead/tr/th[3]")
	public WebElement textUserHeader;
	public String manageDataUserHeader (){
		return textUserHeader.getText();
	}//method end	

	@FindBy(xpath = ".//*[@id='dataManageTable']/thead/tr/th[4]")
	public WebElement textSizeHeader;
	public String manageDataSizeHeader (){
		return textSizeHeader.getText();
	}//method end	

	@FindBy(xpath = ".//*[@id='dataManageTable']/thead/tr/th[5]")
	public WebElement textActionHeader;
	public String manageDataActionHeader (){
		return textActionHeader.getText();
	}//method end	

	@FindBy(id = "dataManageTable_filter")    
	public WebElement textFieldManageDataSearch;
	public void manageDataSearch (String sNameofArtifact){
		textFieldManageDataSearch.sendKeys(sNameofArtifact);
	}//method end

	@FindBy(xpath = ".//*[@id='dataManageTable_wrapper']/div[2]/div[2]/div/ul/li[1]/a")
	public WebElement buttonManageDataPrevious;
	public void manageDataPreviousButton (){
		buttonManageDataPrevious.click();
	}//method end	

	@FindBy(xpath = ".//*[@id='dataManageTable_wrapper']/div[2]/div[2]/div/ul/li[3]/a")
	public WebElement buttonManageDataNext;
	public void manageDataNextButton (){
		buttonManageDataNext.click();
	}//method end	
	
	//Urika > Manage Data > Add File
	@FindBy(id = "addDataButton")    //*** Web elements Declaration
	public static WebElement buttonAddData;
	public void clickAddDataButton (){
		buttonAddData.click();
	}//method end

	//Urika > Manage Data > Data Table
	@FindBy(id = "dataManageTable")
	public WebElement tableDataManagement;
	public String[] dataManagementTable(int param){
		String str[] = new String[10];
		List<WebElement> allRows = tableDataManagement.findElements(By.tagName("tr"));
		for(int i = 1;i < allRows.size(); i++){
			List<WebElement> allCols = ((WebElement) allRows.get(i)).findElements(By.tagName("td"));
			if (allCols.size()>1) {
				WebElement allCell =(WebElement) allCols.get(param);
				if (param==0) {
					WebElement b = allCell.findElement(By.tagName("span"));
					str[i-1] = b.getAttribute("title").trim(); 
				}
				else if (param>=1 && param<=5) {
					str[i-1] = allCell.getText();
				}
				else {
				}
			}
			else {
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

	//Urika > Manage Data > Data Table
	@FindBy(id = "dataManageTable")     
	public WebElement tableDeleteDataManagement;
	public String[] deleteDataManagementTable(String sNameofData)
	{
		String str[] = new String[10];
		List<WebElement> allRows = tableDeleteDataManagement.findElements(By.tagName("tr"));
		for (int m=1; m<allRows.size();m++) {
			if (allRows.get(m).getText().contains(sNameofData)) {
				List<WebElement> allCols = allRows.get(m).findElements(By.tagName("td"));
				for (int k=0; k<allCols.size();k++) {
					if (allCols.get(k).getText().equals(sNameofData)) {
						tableDeleteDataManagement.findElement(By.xpath(".//*/tr["+m+"]/td[5]/a[2]")).click();
					}
				}
			}
		} 
		return str;
	}

	//Urika > Manage Data > Data Table > Loading file
	@FindBy(id = "dataTable")     //*** Web elements Declaration
	public WebElement tableDataArtifacts;
	public String[] dataTable(String sNameYourArtifact){
		String str[] = new String[10];
		List<WebElement> allRows = tableDataArtifacts.findElements(By.tagName("tr"));
		for (int m=0; m<allRows.size();m++) {
			if (allRows.get(m).getText().contains(sNameYourArtifact)){
				List<WebElement> allCols = allRows.get(m).findElements(By.tagName("td"));
			}
		}
		return str;
	}

	//Urika > Manage Data > Datatable > Delete Button
	@FindBy(xpath = "html/body/div[4]/div[2]/a[1]")
	public WebElement buttonDeleteOk;
	public void clickDeleteOk(){
		buttonDeleteOk.click();
	}//method end	

	//Urika > Manage Data > Added Successfully message
	@FindBy(xpath = ".//*[@id='status']/div")
	public WebElement textDataFileAddedMsg;
	public String dataFileAddedMsg(){
		return textDataFileAddedMsg.getText();
	}//method end

	//Urika > Manage Data > Added Successfully message
	@FindBy(xpath = ".//*[@id='status']/div/button")
	public WebElement buttonDataFileAddedMsg;
	public void dataFileAddedCloseButton(){
		buttonDataFileAddedMsg.click();
	}//method end
}//class end