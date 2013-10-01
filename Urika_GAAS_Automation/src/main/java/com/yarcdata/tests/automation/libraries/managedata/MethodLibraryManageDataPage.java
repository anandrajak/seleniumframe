package com.yarcdata.tests.automation.libraries.managedata;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import com.yarcdata.tests.automation.libraries.common.FunctionsLibrary;
import com.yarcdata.tests.automation.libraries.common.MethodLibrary;
import com.yarcdata.tests.automation.libraries.common.ObjectsLibrary;
import com.yarcdata.tests.automation.libraries.common.SeleniumUtilities;
import com.yarcdata.tests.automation.libraries.managedata.ObjectLibraryManageDataPage;

public class MethodLibraryManageDataPage extends MethodLibrary{

	/**
	 * Verifying the Web elements in manage data page
	 * @return driver
	 */

	public WebDriver verifyManageDataComponents()
	{
		ObjectsLibrary objectsLibrary = PageFactory.initElements(driver, ObjectsLibrary.class);
		ObjectLibraryManageDataPage manageDataPageObjectLibrary =  PageFactory.initElements(driver, ObjectLibraryManageDataPage.class);

		Assert.assertTrue(objectsLibrary.objectPushButtonManageData.isDisplayed());
		Reporter.log("The Manage Data link is enabled and functional");

		objectsLibrary.pushButtonManageDataClick();
		Assert.assertTrue(driver.getCurrentUrl().contains("data-manage.jsp"));
		Reporter.log("The Manage Data page has loaded successfully");

		
		String manageDataTitle = manageDataPageObjectLibrary.manageDataPageTitle();
		Assert.assertEquals(manageDataTitle, "Data Management");
		Reporter.log("'Data Management' text is present in the Manage Data page");

		
		String manageDataHeader = manageDataPageObjectLibrary.manageDataPageHeader();
		Assert.assertEquals(manageDataHeader, "Data");
		Reporter.log("'Data' text is present in the Manage Data page");

		Assert.assertTrue(manageDataPageObjectLibrary.textArtifactHeader.isDisplayed());
		String headerNameArtifact = manageDataPageObjectLibrary.manageDataArtifactHeader();
		Assert.assertEquals(headerNameArtifact, "Artifact");
		Reporter.log("'Artifact' column name text is visible");		

		Assert.assertTrue(manageDataPageObjectLibrary.textDateHeader.isDisplayed());
		String headerNameDate = manageDataPageObjectLibrary.manageDataDateHeader();
		Assert.assertEquals(headerNameDate, "Date");
		Reporter.log("'Date' column name text is visible");

		Assert.assertTrue(manageDataPageObjectLibrary.textUserHeader.isDisplayed());
		String headerNameUser = manageDataPageObjectLibrary.manageDataUserHeader();
		Assert.assertEquals(headerNameUser, "User");
		Reporter.log("'User' column name text is visible");

		Assert.assertTrue(manageDataPageObjectLibrary.textSizeHeader.isDisplayed());
		String headerNameSize = manageDataPageObjectLibrary.manageDataSizeHeader();
		Assert.assertEquals(headerNameSize, "Size");
		Reporter.log("'Size' column name text is visible");

		Assert.assertTrue(manageDataPageObjectLibrary.textActionHeader.isDisplayed());
		String headerNameAction = manageDataPageObjectLibrary.manageDataActionHeader();
		Assert.assertEquals(headerNameAction, "Action");
		Reporter.log("'Action' column name text is visible");

		Assert.assertTrue(manageDataPageObjectLibrary.textFieldManageDataSearch.isDisplayed());
		Reporter.log("Search text box is visible");

		Assert.assertTrue(manageDataPageObjectLibrary.buttonManageDataPrevious.isDisplayed());
		Reporter.log("Previous button is visible");

		Assert.assertTrue(manageDataPageObjectLibrary.buttonAddData.isDisplayed());
		Reporter.log("'Add Data' button is present in the Manage Data page");

		return driver;
	}
	/**
	 * Verify Data file present in the Manage data
	 * @param sNameYourArtifact
	 * @param startCount
	 * @return driver
	 */
	public WebDriver verifyDataFile (String sNameYourArtifact, int startCount)
	{
		FunctionsLibrary functionsLibrary = new FunctionsLibrary();  
		ObjectsLibrary  objectsLibrary = PageFactory.initElements(driver, ObjectsLibrary.class);
		ObjectLibraryManageDataPage manageDataPageObjectLibrary =  PageFactory.initElements(driver, ObjectLibraryManageDataPage.class);
		if (startCount == 1) {
			objectsLibrary.pushButtonManageDataClick();
		}
		functionsLibrary.functionWaitForAnObject(2);
		String[] artifactName = manageDataPageObjectLibrary.dataManagementTable(0);
		for (int i = 0; i< artifactName.length; i++) {
			if (artifactName[i].equalsIgnoreCase(sNameYourArtifact)) {
				Assert.assertEquals(artifactName[i], sNameYourArtifact);
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
	 * 
	 * @param sNameYourArtifact
	 * @param startCount
	 * @return driver
	 */
	public WebDriver deleteDataFile (String sNameYourArtifact, int startCount)
	{	
		FunctionsLibrary functionsLibrary = new FunctionsLibrary();
		ObjectsLibrary  objectsLibrary = PageFactory.initElements(driver, ObjectsLibrary.class);
		ObjectLibraryManageDataPage manageDataPageObjectLibrary =  PageFactory.initElements(driver, ObjectLibraryManageDataPage.class);
		if (startCount == 1) {
			objectsLibrary.pushButtonManageDataClick();
		}
		functionsLibrary.functionWaitForAnObject(5);
		manageDataPageObjectLibrary.deleteDataManagementTable(sNameYourArtifact);
		functionsLibrary.functionWaitForAnObject(5);
		manageDataPageObjectLibrary.clickDeleteOk();
		SeleniumUtilities.waitForTextObject(20, By.xpath(".//*[@id='status']/div"), "The artifact "+sNameYourArtifact +" was deleted.");
		
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
}