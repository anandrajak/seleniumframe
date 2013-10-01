package com.yarcdata.tests.automation.libraries.managedata;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.Reporter;

import com.yarcdata.tests.automation.libraries.common.FunctionsLibrary;
import com.yarcdata.tests.automation.libraries.common.MethodLibrary;
import com.yarcdata.tests.automation.libraries.common.ObjectsLibrary;
import com.yarcdata.tests.automation.libraries.common.SeleniumUtilities;
import com.yarcdata.tests.automation.libraries.managedata.ObjectLibraryAddDataPopUp;

public class MethodLibrariesAddDataURL extends MethodLibrary{
	FunctionsLibrary FunctionsLibrary = new FunctionsLibrary();
	
	public WebDriver verifyAddDataURLComponents()
	{	
		ObjectsLibrary  objectsLibrary = PageFactory.initElements(driver, ObjectsLibrary.class);
		ObjectLibraryManageDataPage manageDataPageObjectLibrary =  PageFactory.initElements(driver, ObjectLibraryManageDataPage.class);
		ObjectLibraryAddDataPopUp addDataPopUpObjectLibrary = PageFactory.initElements(driver, ObjectLibraryAddDataPopUp.class);
		
		objectsLibrary.pushButtonManageDataClick();
		manageDataPageObjectLibrary.clickAddDataButton();
		addDataPopUpObjectLibrary.selectDatasourceDropdown("URL");

		Assert.assertTrue(addDataPopUpObjectLibrary.dropdownDataSource.isDisplayed());
		Reporter.log("Choose your data source: Dropdown is visible in Add data modal popup");

		Assert.assertTrue(addDataPopUpObjectLibrary.textFieldManageDataURL.isDisplayed());
		Reporter.log("URL text box is present in the Add data modal popup");

		Assert.assertTrue(addDataPopUpObjectLibrary.textErrorMsgNullURL.getText().contains("* N-Triple or N-Quad files (.nt or .nq) are required."));
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
	 * Add data file using URL in Manage Data
	 * @param sDataSource
	 * @param sUrl
	 * @param sNameYourArtifact
	 * @param sComment
	 * @param sNameofData
	 * @param startCount
	 * @return driver
	 */
	public WebDriver addDataThroughURL (String sDataSource, String sUrl, String sNameYourArtifact, String sComment,String sNameofData, int startCount)
	{
		ObjectsLibrary  objectsLibrary = PageFactory.initElements(driver, ObjectsLibrary.class);
		ObjectLibraryManageDataPage manageDataPageObjectLibrary =  PageFactory.initElements(driver, ObjectLibraryManageDataPage.class);
		ObjectLibraryAddDataPopUp addDataPopUpObjectLibrary = PageFactory.initElements(driver, ObjectLibraryAddDataPopUp.class);
	
		if (startCount == 1) {
			objectsLibrary.pushButtonManageDataClick();
			FunctionsLibrary.functionWaitForAnObject(1);
		}
		manageDataPageObjectLibrary.clickAddDataButton();
		SeleniumUtilities.waitForAnObject(60, addDataPopUpObjectLibrary.dropdownDataSource);
		addDataPopUpObjectLibrary.selectDatasourceDropdown(sDataSource);
		addDataPopUpObjectLibrary.enterURLForManageData(sUrl);
		addDataPopUpObjectLibrary.enterNameOfArtifactForManageData(sNameYourArtifact);
		addDataPopUpObjectLibrary.enterCommentsForManageData(sComment);
		addDataPopUpObjectLibrary.clickManageDataAddDataSubmit();
		SeleniumUtilities.waitForAnObject(60, manageDataPageObjectLibrary.textDataFileAddedMsg);
		
		//To check if the message format is displayed correctly 
		String Message = manageDataPageObjectLibrary.dataFileAddedMsg();
		String ExtactMessage = Message.replaceAll("\\r|x\\n", "");
		Assert.assertEquals(ExtactMessage, "The artifact "+sNameofData+" was added.");

		manageDataPageObjectLibrary.dataManagementTable(0);
		//The thread.sleep function is used to avoid
		FunctionsLibrary.functionWaitForAnObject(2);
		
		//To verify if the data artifact added is present in the data management table
		String[] artifactName = manageDataPageObjectLibrary.dataManagementTable(0);
		for (int i = 0; i< artifactName.length; i++) {
			if (artifactName[i].equals(sNameofData)) {
				Assert.assertEquals(artifactName[i], sNameofData);
				Reporter.log("The artifact added is present in the data management table");
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

		return driver;
	}		
	/**
	 * Add data using invalid URL or file extension in Manage Data
	 * @param sDataSource
	 * @param sUrl
	 * @param sNameYourArtifact
	 * @param sComment
	 * @param serrorforinvalidscenarios
	 * @param startcount
	 * @return driver
	 * @throws InterruptedException
	 */
	public WebDriver addDataWithInvalidURL (String sDataSource, String sUrl, String sNameYourArtifact, String sComment, String serrorforinvalidscenarios, int startcount) throws InterruptedException
	{
		ObjectsLibrary  objectsLibrary = PageFactory.initElements(driver, ObjectsLibrary.class);
		ObjectLibraryManageDataPage manageDataPageObjectLibrary =  PageFactory.initElements(driver, ObjectLibraryManageDataPage.class);
		ObjectLibraryAddDataPopUp addDataPopUpObjectLibrary = PageFactory.initElements(driver, ObjectLibraryAddDataPopUp.class);
		if (startcount == 1) {
			objectsLibrary.pushButtonManageDataClick();
			FunctionsLibrary.functionWaitForAnObject(1);
		}
		manageDataPageObjectLibrary.clickAddDataButton();
		addDataPopUpObjectLibrary.selectDatasourceDropdown(sDataSource);
		addDataPopUpObjectLibrary.enterURLForManageData(sUrl);
		addDataPopUpObjectLibrary.enterNameOfArtifactForManageData(sNameYourArtifact);
		addDataPopUpObjectLibrary.enterCommentsForManageData(sComment);
		addDataPopUpObjectLibrary.clickManageDataAddDataSubmit();
		
		//To check the error message if the URL is null
		if (sUrl.equalsIgnoreCase("")) {
			String textColor = addDataPopUpObjectLibrary.nullURLErrorMsg().getCssValue("color");
			Assert.assertEquals("#333333",Color.fromString(textColor).asHex().toUpperCase());
		}
		//To check the error message if the artifact name is null
		else if(sNameYourArtifact.equalsIgnoreCase("")) {
			String textColor = addDataPopUpObjectLibrary.nullArtifactNameErrorMsg().getCssValue("color");
			Assert.assertEquals("#333333",Color.fromString(textColor).asHex().toUpperCase());			
		}
		//To check the error message if both the URL and artifact name is null 
		else if(sUrl.equalsIgnoreCase("") && sNameYourArtifact.equalsIgnoreCase("")) {
			String textColorURL = addDataPopUpObjectLibrary.nullURLErrorMsg().getCssValue("color");
			Assert.assertEquals("#333333",Color.fromString(textColorURL).asHex().toUpperCase());
			String textColorArtifact = addDataPopUpObjectLibrary.nullArtifactNameErrorMsg().getCssValue("color");
			Assert.assertEquals("#333333",Color.fromString(textColorArtifact).asHex().toUpperCase());			
		}	
		//To check if the artifact name contains invalid characters
		else  {
			String tempErrorMessage = addDataPopUpObjectLibrary.invalidFileExtn();
			String  errorMessage = tempErrorMessage.replaceAll("\\r|x\\n", "");
			Assert.assertEquals(serrorforinvalidscenarios, errorMessage);
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
		addDataPopUpObjectLibrary.clickManageDataAddDataCancel();
		return driver;
	}
}
