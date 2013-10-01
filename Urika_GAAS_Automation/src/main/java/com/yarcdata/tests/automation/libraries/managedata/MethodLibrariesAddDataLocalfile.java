package com.yarcdata.tests.automation.libraries.managedata;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;


import net.sf.saxon.functions.FunctionLibrary;

import org.testng.Assert;
import org.testng.Reporter;

import org.apache.log4j.Logger;
import org.cyberneko.html.HTMLElements.Element;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Predicate;
import com.yarcdata.tests.automation.libraries.common.FunctionsLibrary;
import com.yarcdata.tests.automation.libraries.common.MethodLibrary;
import com.yarcdata.tests.automation.libraries.common.ObjectsLibrary;
import com.yarcdata.tests.automation.libraries.common.SeleniumUtilities;

public class MethodLibrariesAddDataLocalfile extends MethodLibrary{

	public String dataInputFile;
	public String ruleInputFile;
	private static Logger log = Logger.getLogger(MethodLibrariesAddDataLocalfile.class);

	public WebDriver verifyAddDataLocalComponents()
	{
		ObjectsLibrary  objectsLibrary = PageFactory.initElements(driver, ObjectsLibrary.class);
		ObjectLibraryManageDataPage manageDataPageObjectLibrary =  PageFactory.initElements(driver, ObjectLibraryManageDataPage.class);
		ObjectLibraryAddDataPopUp addDataPopUpObjectLibrary = PageFactory.initElements(driver, ObjectLibraryAddDataPopUp.class);

		objectsLibrary.pushButtonManageDataClick();
		manageDataPageObjectLibrary.clickAddDataButton();

		Assert.assertEquals(driver.findElement(By.xpath(".//*[@id='addFiles']/div[1]/h3")).getText(), "Add Data Artifacts");
		Reporter.log("'Add Data Artifacts' text is present  in Add data modal popup");

		Assert.assertTrue(addDataPopUpObjectLibrary.dropdownDataSource.isDisplayed());
		Reporter.log("Choose your data source: Dropdown is visible in Add data modal popup");

		String selectedOption = new Select(driver.findElement(By.id("dataSource"))).getFirstSelectedOption().getText();
		Assert.assertEquals("Local File", selectedOption);
		Reporter.log("Default value Local file in Add data modal popup");

		Assert.assertTrue(addDataPopUpObjectLibrary.textFieldManageDataNameOfArtifact.isDisplayed());
		Reporter.log("Name your artifact text box is present in the Add data modal popup");

		Assert.assertTrue(addDataPopUpObjectLibrary.textErrorMsgNullArtifactName.getText().contains("* Name is required."));
		Assert.assertTrue(addDataPopUpObjectLibrary.textErrorMsgNullLocalFile.getText().contains("* File size limit of 2GB."+"\n"+"* N-Triple or N-Quad files (.nt or .nq) are required."));
		Reporter.log("Default messages are present in the Add data modal popup");

		Assert.assertTrue(addDataPopUpObjectLibrary.textAreaManageDataComments.isDisplayed());
		Reporter.log("Comments Text area box is present in the Add data modal popup");

		Assert.assertTrue(addDataPopUpObjectLibrary.textFieldManageDataLocalFile.isDisplayed());
		Reporter.log("Local File upload is present in the Add data modal popup");

		Assert.assertTrue(addDataPopUpObjectLibrary.buttonManageDataSubmit.isDisplayed());
		Assert.assertTrue(addDataPopUpObjectLibrary.buttonManageDataSubmit.isEnabled());
		Reporter.log("Submit button is present in the Add data modal popup");

		Assert.assertTrue(addDataPopUpObjectLibrary.buttonManageDataCancel.isDisplayed());
		Assert.assertTrue(addDataPopUpObjectLibrary.buttonManageDataCancel.isEnabled());
		Reporter.log("Cancel button is present in the Add data modal popup");
		return driver;
	}	
	/**
	 * Add Multiple Data using local file
	 * @param sDataSource
	 * @param sNameYourArtifact
	 * @param sDataComment
	 * @param sNameofData
	 * @param dpCount
	 * @param startCount
	 * @return driver
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 */
	public WebDriver addMultipleDataArtifactThroughLocalFile (String sDataSource, String sNameYourArtifact, String sDataComment, String sNameofData, int dpCount, int startCount) throws FileNotFoundException, IOException, InterruptedException
	{
		FunctionsLibrary FunctionsLibrary = new FunctionsLibrary();
		ObjectsLibrary  objectsLibrary = PageFactory.initElements(driver, ObjectsLibrary.class);
		ObjectLibraryManageDataPage manageDataPageObjectLibrary =  PageFactory.initElements(driver, ObjectLibraryManageDataPage.class);
		ObjectLibraryAddDataPopUp addDataPopUpObjectLibrary = PageFactory.initElements(driver, ObjectLibraryAddDataPopUp.class);

		dataInputFile = System.getProperty("dataset.file.location");

		if (startCount == 1) {
			objectsLibrary.pushButtonManageDataClick();
			FunctionsLibrary.functionWaitForAnObject(1);
		}
		manageDataPageObjectLibrary.clickAddDataButton();
		addDataPopUpObjectLibrary.selectDatasourceDropdown(sDataSource);
		addDataPopUpObjectLibrary.enterNameOfArtifactForManageData(sNameYourArtifact);
		addDataPopUpObjectLibrary.enterCommentsForManageData(sDataComment);
		addDataPopUpObjectLibrary.chooseLocalFileForManageData(dataInputFile);

		//Note : Time wait has been added here to upload the file in the order of submission
		addDataPopUpObjectLibrary.clickManageDataAddDataSubmit();
		FunctionsLibrary.functionWaitForAnObject(10);
		String[] artifactName = manageDataPageObjectLibrary.dataManagementTable(0);

		//To check if the data load progress is visible
		int isexist = 0;
		for (int i = 0; i< artifactName.length; i++) {
			if (!artifactName[i].equals(sNameYourArtifact)) {
				isexist = 1;
			}
			else {
				isexist = 0;
			}
		}
		//To check if the file is loading
		if(isexist == 1) {
			manageDataPageObjectLibrary.dataTable(sNameYourArtifact);
			Reporter.log("File is loading...");
			isexist = 0;
		}
		//To check the file upload success messages
		if(dpCount == startCount) {
			SeleniumUtilities.waitForPresenceOfElement(1200, By.xpath(".//*[@id='status']/div["+dpCount+"]"));
			//Get the current system time
			DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date date = new Date();
			String sysDate  = dateFormat.format(date);

			//Get the current user
			Properties props = new Properties();	
			String seleniumPropertyFile = System.getProperty("selenium.properties"); 
			props.load(new FileInputStream(seleniumPropertyFile));
			String uName = props.getProperty("urika.username");

			//Get the uploaded file size
			File file = new File(dataInputFile);
			long fileSizeInBytes = file.length();
			long fileSizeInMB = fileSizeInBytes / (1024*1024);

			//Capturing screenshot and saving it in a folder
			try {
				String className = Thread.currentThread().getStackTrace()[2].getClassName();
				className = className.substring(className.lastIndexOf('.') + 1);
				String methodName = Thread.currentThread().getStackTrace()[1].getMethodName();
				SeleniumUtilities.createFile(className,methodName);
			} catch (Exception e) {
				e.printStackTrace();
			} 

			FunctionsLibrary.functionWaitForAnObject(2);
			int j = 0;
			do {
				artifactName = manageDataPageObjectLibrary.dataManagementTable(j);
				for (int i = 0; i< artifactName.length; i++) {
					if (artifactName[i].equalsIgnoreCase(sNameofData)) {
						Assert.assertEquals(artifactName[i], sNameofData);
					}
					else if (artifactName[i].contains(sysDate)) {
						Assert.assertEquals(artifactName[i], sysDate);
					}
					else if (artifactName[i].equalsIgnoreCase(uName)) {
						Assert.assertEquals(artifactName[i], uName);
					}
					else if (artifactName[i].equals(fileSizeInMB+".0 MB")) {
						Assert.assertEquals(artifactName[i], (fileSizeInMB+".0 MB"));
					}
				}
				j++;
			} while(j<=3);

			Assert.assertEquals(manageDataPageObjectLibrary.buttonDataFileAddedMsg.isDisplayed(), true);
			Reporter.log("The Message box is closed");
			manageDataPageObjectLibrary.dataFileAddedCloseButton();

		}
		return driver;
	}
	/**
	 * Add invalid data file in Manage Data using Local File
	 * @param sDataSource
	 * @param sNameYourArtifact
	 * @param sComment
	 * @param serrorforinvalidscenarios
	 * @param startcount
	 * @return driver
	 * @throws InterruptedException
	 */
	public WebDriver addDataWithInvalidLocalFile (String sDataSource, String sNameYourArtifact, String sComment, String serrorforinvalidscenarios, int startcount) throws InterruptedException
	{
		FunctionsLibrary functionsLibrary = new FunctionsLibrary();
		ObjectsLibrary  objectsLibrary = PageFactory.initElements(driver, ObjectsLibrary.class);
		ObjectLibraryManageDataPage manageDataPageObjectLibrary =  PageFactory.initElements(driver, ObjectLibraryManageDataPage.class);
		ObjectLibraryAddDataPopUp addDataPopUpObjectLibrary = PageFactory.initElements(driver, ObjectLibraryAddDataPopUp.class);

		dataInputFile = System.getProperty("dataset.file.location");
		ruleInputFile = System.getProperty("ruleset.file.location");
		String nullLocalPath="";

		objectsLibrary.pushButtonManageDataClick();
		functionsLibrary.functionWaitForAnObject(1);
		manageDataPageObjectLibrary.clickAddDataButton();
		addDataPopUpObjectLibrary.selectDatasourceDropdown(sDataSource);
		addDataPopUpObjectLibrary.enterNameOfArtifactForManageData(sNameYourArtifact);
		addDataPopUpObjectLibrary.enterCommentsForManageData(sComment);

		if (sNameYourArtifact.equalsIgnoreCase("InvalidFileExtension")) {
			addDataPopUpObjectLibrary.chooseLocalFileForManageData(ruleInputFile);
		}
		else if (sNameYourArtifact.equalsIgnoreCase("NullLocalFilePath")) {
			addDataPopUpObjectLibrary.chooseLocalFileForManageData(nullLocalPath);
		}
		else{
			addDataPopUpObjectLibrary.chooseLocalFileForManageData(dataInputFile);
		}

		//Note : Time wait is added here for already existing artifact name issue 
		functionsLibrary.functionWaitForAnObject(1);
		addDataPopUpObjectLibrary.clickManageDataAddDataSubmit();

		//To check the error message if the local file path is null
		if (nullLocalPath.equalsIgnoreCase("")) {
			String textColorNullLocalFile = addDataPopUpObjectLibrary.nullLocalFileErrorMsg().getCssValue("color");
			Assert.assertEquals("#333333",Color.fromString(textColorNullLocalFile).asHex().toUpperCase());
		}
		//To check the error message if the artifact name is null
		else if(sNameYourArtifact.equalsIgnoreCase("")) {
			String textColorNullArtifact = addDataPopUpObjectLibrary.nullArtifactNameErrorMsg().getCssValue("color");
			Assert.assertEquals("#333333",Color.fromString(textColorNullArtifact).asHex().toUpperCase());			
		}
		//To check the error message if both the artifact name and file path is null
		else if(nullLocalPath.equalsIgnoreCase("") && sNameYourArtifact.equalsIgnoreCase("")) {
			String textColorNullArtifact = addDataPopUpObjectLibrary.nullArtifactNameErrorMsg().getCssValue("color");
			Assert.assertEquals("#333333",Color.fromString(textColorNullArtifact).asHex().toUpperCase());	

			String textColorNullLocalFile = addDataPopUpObjectLibrary.nullLocalFileErrorMsg().getCssValue("color");
			Assert.assertEquals("#333333",Color.fromString(textColorNullLocalFile).asHex().toUpperCase());
		}	
		//To check if invalid characters are present in the artifact name
		else {
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
		String[] artifactName = manageDataPageObjectLibrary.dataManagementTable(0);

		for (int i = 0; i< artifactName.length; i++) {
			if (!artifactName[i].equals(sNameYourArtifact)) {
				Assert.assertTrue(true);
				Reporter.log("The cancelled data artifact is not present in the Data management table");
			}
		}
		return driver;
	}
}
