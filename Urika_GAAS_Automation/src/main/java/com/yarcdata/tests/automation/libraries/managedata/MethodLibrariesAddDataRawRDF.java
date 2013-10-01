package com.yarcdata.tests.automation.libraries.managedata;

import java.awt.AWTException;
import java.io.IOException;

import net.sf.saxon.functions.FunctionLibrary;

import org.testng.Assert;
import org.testng.Reporter;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.yarcdata.tests.automation.libraries.common.FunctionsLibrary;
import com.yarcdata.tests.automation.libraries.common.MethodLibrary;
import com.yarcdata.tests.automation.libraries.common.ObjectsLibrary;
import com.yarcdata.tests.automation.libraries.common.SeleniumUtilities;

public class MethodLibrariesAddDataRawRDF extends MethodLibrary {

	FunctionsLibrary functionsLibrary = new FunctionsLibrary(); 
	
	public WebDriver verifyAddDataRawRdfComponents()
	{
		ObjectsLibrary  objectsLibrary = PageFactory.initElements(driver, ObjectsLibrary.class);
		ObjectLibraryManageDataPage manageDataPageObjectLibrary =  PageFactory.initElements(driver, ObjectLibraryManageDataPage.class);
		ObjectLibraryAddDataPopUp addDataPopUpObjectLibrary = PageFactory.initElements(driver, ObjectLibraryAddDataPopUp.class);
	
		objectsLibrary.pushButtonManageDataClick();
		SeleniumUtilities.waitForAnObject(60, manageDataPageObjectLibrary.buttonManageDataPrevious);
		
		manageDataPageObjectLibrary.clickAddDataButton();
		addDataPopUpObjectLibrary.selectDatasourceDropdown("Raw RDF");

		Assert.assertTrue(addDataPopUpObjectLibrary.dropdownDataSource.isDisplayed());
		Reporter.log("Choose your data source: Dropdown is visible in Add data modal popup");		

		Assert.assertTrue(addDataPopUpObjectLibrary.textFieldManageDataRawRDF.isDisplayed());
		Reporter.log("RDF text area box is present in the Add data modal popup");

		Assert.assertTrue(addDataPopUpObjectLibrary.textErrorMsgRawRDF.getText().contains("* N-Triple or N-Quad format is required."+"\n"+"* Data limit of 4MB"));
		Assert.assertTrue(addDataPopUpObjectLibrary.textErrorMsgNullArtifactName.getText().contains("* Name is required."));
		Reporter.log("Default messages are present in the Add data modal popup");

		Assert.assertTrue(addDataPopUpObjectLibrary.textFieldManageDataNameOfArtifact.isDisplayed());
		Reporter.log("Name your artifact text box is present in the Add data modal popup");

		Assert.assertTrue(addDataPopUpObjectLibrary.textAreaManageDataComments.isDisplayed());
		Reporter.log("Comments Text area box is present in the Add data modal popup");

		Assert.assertTrue(addDataPopUpObjectLibrary.buttonManageDataSubmit.isDisplayed());
		Assert.assertTrue(addDataPopUpObjectLibrary.buttonManageDataSubmit.isEnabled());
		Reporter.log("Submit button is present in the Add data modal popup");

		Assert.assertTrue(addDataPopUpObjectLibrary.buttonManageDataCancel.isDisplayed());
		Assert.assertTrue(addDataPopUpObjectLibrary.buttonManageDataCancel.isEnabled());
		Reporter.log("Cancel button is present in the Add data modal popup");
		
		addDataPopUpObjectLibrary.clickManageDataAddDataCancel();		

		return driver;
	}
	/**
	 * Add single or Multiple RAW RDF data in Manage data
	 * @param sDataSource
	 * @param sRawRdf
	 * @param sNameYourArtifact
	 * @param sComment
	 * @param sNameofData
	 * @param dpCount
	 * @param startCount
	 * @return driver
	 */
	public WebDriver addMultipleDataArtifactThroughRawRdf (String sDataSource, String sRawRdf, String sNameYourArtifact, String sComment,  String sNameofData, int dpCount, int startCount)
	{
		FunctionsLibrary functionsLibrary = new FunctionsLibrary(); 
		ObjectsLibrary  objectsLibrary = PageFactory.initElements(driver, ObjectsLibrary.class);
		ObjectLibraryManageDataPage manageDataPageObjectLibrary =  PageFactory.initElements(driver, ObjectLibraryManageDataPage.class);
		ObjectLibraryAddDataPopUp addDataPopUpObjectLibrary = PageFactory.initElements(driver, ObjectLibraryAddDataPopUp.class);
		if(startCount == 1) {
			objectsLibrary.pushButtonManageDataClick();
			functionsLibrary.functionWaitForAnObject(1);
		}
		manageDataPageObjectLibrary.clickAddDataButton();
		addDataPopUpObjectLibrary.selectDatasourceDropdown(sDataSource);
		addDataPopUpObjectLibrary.enterRawRDFForManageData(sRawRdf);
		addDataPopUpObjectLibrary.enterNameOfArtifactForManageData(sNameYourArtifact);
		addDataPopUpObjectLibrary.enterCommentsForManageData(sComment);
		addDataPopUpObjectLibrary.clickManageDataAddDataSubmit();

		//Note : To make sure the message box gets displayed 
		functionsLibrary.functionWaitForAnObject(4);	
		
		//To check if the message format is displayed correctly 
		String Message = manageDataPageObjectLibrary.dataFileAddedMsg();
		String ExtactMessage = Message.replaceAll("\\r|x\\n", "");
		Assert.assertEquals(ExtactMessage, "The artifact "+sNameofData+" was added.");

		String[] artifactName = manageDataPageObjectLibrary.dataManagementTable(0);
		//To check if the data load progress is visible
		int isExist = 0;
		for (int i = 0; i< artifactName.length; i++) {
			if (!artifactName[i].equals(sNameYourArtifact)) {
				isExist = 1;
			}
			else {
				isExist = 0;
			}
		}
		//To check if the file is loading
		if(isExist == 1) {
			manageDataPageObjectLibrary.dataTable(sNameYourArtifact);
			Reporter.log("File is loading...");
			isExist = 0;
		}
		//To check the file upload success messages
		if(dpCount == startCount) {
			SeleniumUtilities.waitForTextObject(600,By.xpath(".//*[@id='status']/div"), "The artifact "+sNameofData +" was added.");
			artifactName = manageDataPageObjectLibrary.dataManagementTable(0);
			for (int i = 0; i< artifactName.length; i++) {
				if (artifactName[i].equals(sNameYourArtifact)) {
					Assert.assertEquals(artifactName[i], sNameofData);
					Reporter.log("The artifact "+sNameYourArtifact+" has been loaded successfully and is visible");
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
			
			Assert.assertEquals(manageDataPageObjectLibrary.buttonDataFileAddedMsg.isDisplayed(), true);
			Reporter.log("The Message box is closed");
			manageDataPageObjectLibrary.dataFileAddedCloseButton();
		}
		return driver;
	}//End Method
	/**
	 * Add invalid RAW RDF data in Manage data
	 * @param sDataSource
	 * @param sRawRdf
	 * @param sNameYourArtifact
	 * @param sComment
	 * @param serrorforinvalidscenarios
	 * @param startcount
	 * @return driver
	 * @throws InterruptedException
	 */
	public WebDriver addDataWithInvalidRawRDF (String sDataSource, String sRawRdf, String sNameYourArtifact, String sComment, String serrorforinvalidscenarios, int startcount) throws InterruptedException
	{
		ObjectsLibrary  objectsLibrary = PageFactory.initElements(driver, ObjectsLibrary.class);
		ObjectLibraryManageDataPage manageDataPageObjectLibrary =  PageFactory.initElements(driver, ObjectLibraryManageDataPage.class);
		ObjectLibraryAddDataPopUp addDataPopUpObjectLibrary = PageFactory.initElements(driver, ObjectLibraryAddDataPopUp.class);
	
		objectsLibrary.pushButtonManageDataClick();
		functionsLibrary.functionWaitForAnObject(1);
		manageDataPageObjectLibrary.clickAddDataButton();
		addDataPopUpObjectLibrary.selectDatasourceDropdown(sDataSource);
		addDataPopUpObjectLibrary.enterRawRDFForManageData(sRawRdf);
		addDataPopUpObjectLibrary.enterNameOfArtifactForManageData(sNameYourArtifact);
		addDataPopUpObjectLibrary.enterCommentsForManageData(sComment); 
		addDataPopUpObjectLibrary.clickManageDataAddDataSubmit();

		if (sRawRdf.equalsIgnoreCase("")) {
			String textColorNullLocalFile = addDataPopUpObjectLibrary.nullLocalFileErrorMsg().getCssValue("color");
			Assert.assertEquals("#333333",Color.fromString(textColorNullLocalFile).asHex().toUpperCase());
		}
		else if(sNameYourArtifact.equalsIgnoreCase("")) {
			String textColorNullArtifact = addDataPopUpObjectLibrary.nullArtifactNameErrorMsg().getCssValue("color");
			Assert.assertEquals("#333333",Color.fromString(textColorNullArtifact).asHex().toUpperCase());			
		}
		else if(sRawRdf.equalsIgnoreCase("") && sNameYourArtifact.equalsIgnoreCase("")) {
			String textColorNullArtifact = addDataPopUpObjectLibrary.nullArtifactNameErrorMsg().getCssValue("color");
			Assert.assertEquals("#333333",Color.fromString(textColorNullArtifact).asHex().toUpperCase());	
			String textColorNullLocalFile = addDataPopUpObjectLibrary.nullLocalFileErrorMsg().getCssValue("color");
			Assert.assertEquals("#333333",Color.fromString(textColorNullLocalFile).asHex().toUpperCase());
		}	
		else {
			String tempErrorMessage = addDataPopUpObjectLibrary.invalidFileExtn();
			String  errorMessage = tempErrorMessage.replaceAll("\\r|x\\n", "");
			Assert.assertEquals(serrorforinvalidscenarios, errorMessage);
		}
		try {
			String className = Thread.currentThread().getStackTrace()[2].getClassName();
			className = className.substring(className.lastIndexOf('.') + 1);
			String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
			SeleniumUtilities.createFile(className,methodName);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		addDataPopUpObjectLibrary.clickManageDataAddDataCancel();
		String[] artifactName = manageDataPageObjectLibrary.dataManagementTable(0);
		for (int i = 0; i< artifactName.length; i++) {
			if (!artifactName[i].equals(sNameYourArtifact)) {
				Assert.assertTrue(true);
			}
		}
		return driver;
	}	
}
