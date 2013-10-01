/**
 * Copyright 2013 YarcData LLC All Rights Reserved.
 */
package com.yarcdata.tests.automation.managedata;
/**
 * <p>
 * To check Manage Data - URL Scenarios
 * </p>
 * @author Anitha
 * @author Venkatachalam
 * @reviewer Promoth
 */

import java.awt.AWTException;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

import com.yarcdata.tests.automation.libraries.common.SeleniumTestBase;
import com.yarcdata.tests.automation.libraries.common.SuiteConstants;
import com.yarcdata.tests.automation.libraries.managedata.*;

public class AddDataWithURL extends SeleniumTestBase{

	MethodLibraryManageDataPage ManageDataPageMethodLibrary = new MethodLibraryManageDataPage();
	MethodLibrariesAddDataURL AddDataURLMethodLibraries = new MethodLibrariesAddDataURL();
	private static Logger log = Logger.getLogger(AddDataWithRawRdf.class);

	int startcount = 1;
	
	/**
	 * To retrieve Raw RDF data from a Spreadsheet- Positive Flow
	 * @return retObjArr
	 * @throws Exception
	 */

	@DataProvider(name = "DP1")
	public Object[][] postiveTestdataWithURL() throws Exception{
		Object[][] retObjArr= FunctionsLibrary.getTableArray(seleniumInputFile, managedatasheetname, adddatatableurl);
		return(retObjArr);
	}

	@Test(dataProvider = "DP1")
	public void addDataThroughURLTest (String sDataSource, String sUrl, String sNameYourArtifact, String sDataComment, String sNameofData) throws Exception 
	{
		if (startcount == 1){
			ManageDataPageMethodLibrary.verifyManageDataComponents();
			AddDataURLMethodLibraries.verifyAddDataURLComponents();
		}
		AddDataURLMethodLibraries.addDataThroughURL(sDataSource, sUrl, sNameYourArtifact, sDataComment, sNameofData, startcount);
		startcount++;	
	}

	/**
	 * To retrieve Raw RDF data from a Spreadsheet- Negative Flow
	 * @return retObjArr
	 * @throws Exception
	 */
	@DataProvider(name = "DP2")
	public Object[][] negativeTestdataWithURL() throws Exception{
		Object[][] retObjArr= FunctionsLibrary.getTableArray(seleniumInputFile, managedatasheetname, urlNegativescenarios);
		return(retObjArr);
	}

	@Test(dataProvider = "DP2")
	public void addDataWithInvalidURLTest (String sDataSource, String sUrl, String sNameYourArtifact, String sDataComment, String serrorforinvalidscenarios) throws InterruptedException, IOException, AWTException 
	{
		AddDataURLMethodLibraries.addDataWithInvalidURL(sDataSource, sUrl, sNameYourArtifact, sDataComment, serrorforinvalidscenarios,startcount);
		startcount++;
	}
}
