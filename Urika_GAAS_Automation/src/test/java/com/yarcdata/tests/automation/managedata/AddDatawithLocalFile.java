/**
 * Copyright 2013 YarcData LLC All Rights Reserved.
 */
package com.yarcdata.tests.automation.managedata;
/**
 * <p>
 * To check Manage Data - Local File Scenarios
 * </p>
 * @author Venkatachalam
 * @author Anitha
 * @reviewer Promoth
 */

import java.awt.AWTException;
import java.io.IOException;

import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

import com.yarcdata.tests.automation.libraries.common.SeleniumTestBase;
import com.yarcdata.tests.automation.libraries.managedata.*;


public class AddDatawithLocalFile extends SeleniumTestBase{

	MethodLibrariesAddDataLocalfile AddDataLocalfileMethodLibraries = new MethodLibrariesAddDataLocalfile();
	MethodLibraryManageDataPage ManageDataPageMethodLibrary = new MethodLibraryManageDataPage();
	
	int startcount = 1;

	/**
	 * To retrieve Local File data from a Spreadsheet- Positive Flow
	 * @return retObjArr
	 * @throws Exception
	 */
	@DataProvider(name = "DP1")
	public Object[][] postiveTestdataWithLocalfile() throws Exception{
		Object[][] retObjArr= FunctionsLibrary.getTableArray(seleniumInputFile, managedatasheetname, adddatatablelocalfile);
		return(retObjArr);
	}

	/**
	 * To upload Single/Multiple Local data file - Positive Flow 
	 * @param sDataSource
	 * @param sNameYourArtifact
	 * @param sDataComment
	 * @param sNameofData
	 * @throws Exception
	 */
	@Test(dataProvider = "DP1")
	public void addDataThroughLocalFileTest (String sDataSource, String sNameYourArtifact, String sDataComment, String sNameofData) throws Exception 
	{
		int dpcount = postiveTestdataWithLocalfile().length;
		if (startcount == 1){
			AddDataLocalfileMethodLibraries.verifyAddDataLocalComponents();
		}
		AddDataLocalfileMethodLibraries.addMultipleDataArtifactThroughLocalFile(sDataSource, sNameYourArtifact, sDataComment, sNameofData, dpcount, startcount);
		if(dpcount == startcount)
			startcount =1;
		else
			startcount++;		
	}

	/**
	 * To retrieve Local File data from a Spreadsheet - Negative Flow 
	 * @return retObjArr
	 * @throws Exception
	 */
	@DataProvider(name = "DP2")
	public Object[][] negativeTestdataWithLocalfile() throws Exception{
		Object[][] retObjArr= FunctionsLibrary.getTableArray(seleniumInputFile, managedatasheetname, localFilenegativescenarios);
		return(retObjArr);
	}

	/**
	 * To upload Single/Multiple Local data file - Negative Flow
	 * @param sDataSource
	 * @param sNameYourArtifact
	 * @param sComment
	 * @param serrorforinvalidscenarios
	 * @throws InterruptedException
	 * @throws IOException
	 * @throws AWTException
	 */
	@Test(dataProvider = "DP2", dependsOnMethods="addDataThroughLocalFileTest")
	public void addDataWithInvalidLocalFileTest (String sDataSource, String sNameYourArtifact, String sComment, String serrorforinvalidscenarios) throws InterruptedException, IOException, AWTException 
	{
		AddDataLocalfileMethodLibraries.addDataWithInvalidLocalFile(sDataSource, sNameYourArtifact, sComment, serrorforinvalidscenarios, startcount);
	}

	/**
	 * To retrieve Local File data from a Spreadsheet - Multiple Users 
	 * @return retObjArr
	 * @throws Exception
	 */
	@DataProvider(name = "DP3")
	public Object[][] multipleUsersTestdatawithLocalfile() throws Exception{
		Object[][] retObjArr= FunctionsLibrary.getTableArray(seleniumInputFile, managedatasheetname, twouseradddatatable);
		return(retObjArr);
	}

	/**
	 * To upload Single/Multiple Local data file and delete the same with Multiple Users
	 * @param sUsername
	 * @param sPassword
	 * @param sNameYourArtifact
	 * @throws Exception
	 */
	@Test(dataProvider = "DP3", dependsOnMethods="addDataWithInvalidLocalFileTest")
	//@Test(dataProvider = "DP3")
	public void addAndDeleteDataAsDifferentUsersTest (String sUsername, String sPassword, String sNameYourArtifact) throws Exception 
	{
		MethodsLibrary.methodSignout();
		MethodsLibrary.methodLoginUrika(sUsername, sPassword);		
		ManageDataPageMethodLibrary.deleteDataFile(sNameYourArtifact, startcount);
		MethodsLibrary.methodSignout();
		MethodsLibrary.methodQuitUrika();
		startcount++;
	}	

}