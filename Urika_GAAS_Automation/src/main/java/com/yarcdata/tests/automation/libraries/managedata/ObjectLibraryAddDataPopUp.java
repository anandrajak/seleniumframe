/**
 * Copyright 2013 YarcData LLC All Rights Reserved.
 */
package com.yarcdata.tests.automation.libraries.managedata;

/**
 * Object library - To invoke methods from Object Library and Functions Library in order to test UI actions.
 * @author Venkatachalam
 */

import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;

public class ObjectLibraryAddDataPopUp {
	protected WebElement element;
	/**
	 * Manage Data Web Elements for Add Data Popup
	 */
	@FindBy(xpath = ".//*[@id='addFiles']/div[1]/h3")
	public WebElement textPopupTitle;
	public String popupTitle()	{
		return textPopupTitle.getText();
	}
	
	//Urika > Add Data > Choose your data source
	@FindBy(id = "dataSource")    //*** Web elements Declaration
	public WebElement dropdownDataSource;
	public void selectDatasourceDropdown (String sDataSource){
		new Select(dropdownDataSource.findElement(By.id("dataSource"))).selectByVisibleText(sDataSource);
	}//method end	

	//Urika > Manage Data > Add File > Name your artifact
	@FindBy(id = "filenameInput")
	public WebElement textFieldManageDataNameOfArtifact;
	public void enterNameOfArtifactForManageData (String sNameYourArtifact){
		textFieldManageDataNameOfArtifact.sendKeys(sNameYourArtifact);
	}//method end

	//Urika > Manage Data > Add File > Comments
	@FindBy(id = "commentsInput")
	public WebElement textAreaManageDataComments;
	public void enterCommentsForManageData (String sDataComment){
		textAreaManageDataComments.sendKeys(sDataComment);
	}//method end

	//Urika > Manage Data > Add File > URL > URL Textbox
	@FindBy(id = "urlInput")    //*** Web elements Declaration
	public WebElement textFieldManageDataURL;
	public void enterURLForManageData (String sUrl){
		textFieldManageDataURL.sendKeys(sUrl);
	}//method end
	
	//Urika > Manage Data > Add File > URL > null URL Error Message
	@FindBy(id = "urlGroup")
	public WebElement textErrorMsgNullURL;
	public WebElement nullURLErrorMsg (){
		return textErrorMsgNullURL;
	}//method end	
	
	//Urika > Manage Data > Add File > Local File > Local File
	@FindBy(id = "localInput")    //*** Web elements Declaration
	public WebElement textFieldManageDataLocalFile;
	public void chooseLocalFileForManageData (String sLocalFilePath){
		textFieldManageDataLocalFile.sendKeys(sLocalFilePath);
	}//method end
	
	//Urika > Manage Data > Add File > Local File > null local file path Error Message
	@FindBy(id = "fileGroup")
	public WebElement textErrorMsgNullLocalFile;
	public WebElement nullLocalFileErrorMsg (){
		return textErrorMsgNullURL;
	}//method end	

	//Urika > Manage Data > Add File > Raw RDF> RDF Textbox
	@FindBy(id = "rdfInput")    //*** Web elements Declaration
	public WebElement textFieldManageDataRawRDF;
	public void enterRawRDFForManageData (String sRawRdf){
		textFieldManageDataRawRDF.sendKeys(sRawRdf);
	}//method end
	
	//Urika > Manage Data > Add File > Raw RDF> RDF Error Message
	@FindBy(id = "rdfGroup")    //*** Web elements Declaration
	public WebElement textErrorMsgRawRDF;
	public WebElement rawRDFErrorMsg(){
		return textErrorMsgRawRDF;
	}//method end

	//Urika > Manage Data > Add File > Submit
	@FindBy(id = "importDataAction")
	public WebElement buttonManageDataSubmit;
	public void clickManageDataAddDataSubmit (){
		buttonManageDataSubmit.click();
	}//method end

	//Urika > Manage Data > Add File > Cancel
	@FindBy(id = "importDataCancel")
	public WebElement buttonManageDataCancel;
	public void clickManageDataAddDataCancel (){
		buttonManageDataCancel.click();
	}//method end	

	//Urika > Manage Data > Add File > null artifact Error Message	
	@FindBy(id = "filenameGroup")
	public WebElement textErrorMsgNullArtifactName;
	public WebElement nullArtifactNameErrorMsg (){
		return textErrorMsgNullArtifactName;
	}//method end		

	//Urika > Manage Data > Add File > Error Messages
	@FindBy(id = "dataStatus")
	public WebElement textErrorMsgInvalidFileExtn;
	public String invalidFileExtn(){
		return textErrorMsgInvalidFileExtn.getText();
	}//method end
}//class end