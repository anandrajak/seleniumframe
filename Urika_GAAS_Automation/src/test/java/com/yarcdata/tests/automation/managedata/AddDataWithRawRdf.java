/**
 * Copyright 2013 YarcData LLC All Rights Reserved.
 */
package com.yarcdata.tests.automation.managedata;
/**
 * <p>
 * To check Manage Data - Raw RDF Scenarios
 * </p>
 * @author Venkatachalam
 * @reviewer Promoth
 */

import java.awt.AWTException;
import java.io.IOException;

import org.apache.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.yarcdata.tests.automation.libraries.common.SeleniumTestBase;
import com.yarcdata.tests.automation.libraries.managedata.*;

public class AddDataWithRawRdf extends SeleniumTestBase{

	MethodLibrariesAddDataRawRDF AddDataRawRDFMethodLibraries = new MethodLibrariesAddDataRawRDF();
	MethodLibraryManageDataPage ManageDataPageMethodLibrary = new MethodLibraryManageDataPage();
	private static Logger log = Logger.getLogger(AddDataWithRawRdf.class);

	int startcount = 1;

	/**
	 * To retrieve Raw RDF data from a Spreadsheet- Positive Flow
	 * @return retObjArr
	 * @throws Exception
	 */
	@DataProvider(name = "DP1")
	public Object[][] postiveTestDataWithRawRdf() throws Exception{
		Object[][] retObjArr= FunctionsLibrary.getTableArray(seleniumInputFile, managedatasheetname, adddatatableRawRDF);
		return(retObjArr);
	}

	@Test(dataProvider = "DP1")
	public void addDataThroughRawRDFTest (String sDataSource, String sRawRdf, String sNameYourArtifact, String sComment,  String sNameofData) throws Exception 
	{
		if(startcount==1){
			AddDataRawRDFMethodLibraries.verifyAddDataRawRdfComponents();
		}
		int dpcount = postiveTestDataWithRawRdf().length;
		AddDataRawRDFMethodLibraries.addMultipleDataArtifactThroughRawRdf(sDataSource, sRawRdf, sNameYourArtifact, sComment,  sNameofData, dpcount, startcount);
		if(dpcount == startcount)
			startcount =1;
		else
			startcount++;	
	}

	/**
	 * To retrieve Raw RDF data from a Spreadsheet- Negative Flow
	 * @return retObjArr
	 * @throws Exception
	 */

	@DataProvider(name = "DP2")
	public Object[][] negativeTestdataWithRawRdfNegative() throws Exception{
		Object[][] retObjArr= FunctionsLibrary.getTableArray(seleniumInputFile, managedatasheetname, rawRDFnegativescenarios);
		return(retObjArr);
	}

	@Test(dataProvider = "DP2", dependsOnMethods="addDataThroughRawRDFTest")
	public void addDataWithInvalidRawRDFTest (String sDataSource, String sRawRdf, String sNameYourArtifact, String sComment, String serrorforinvalidscenarios) throws InterruptedException, IOException, AWTException 
	{
		AddDataRawRDFMethodLibraries.addDataWithInvalidRawRDF(sDataSource, sRawRdf, sNameYourArtifact, sComment,  serrorforinvalidscenarios, startcount);
	}

	/**
	 * To retrieve Raw RDF data from a Spreadsheet- Multiple files
	 * @return retObjArr
	 * @throws Exception
	 */

	@DataProvider(name = "DP3")
	public Object[][] multipleUsersTestdataWithRawRdf() throws Exception{
		Object[][] retObjArr= FunctionsLibrary.getTableArray(seleniumInputFile, managedatasheetname, twouseraddtablerawrdf);
		return(retObjArr);
	}

	@Test(dataProvider = "DP3", dependsOnMethods="addDataWithInvalidRawRDFTest")
	public void addAndDeleteDataAsDifferentUsersTest (String sUsername, String sPassword, String sNameYourArtifact) throws Exception 
	{
		MethodsLibrary.methodSignout();
		MethodsLibrary.methodLoginUrika(sUsername, sPassword);
		ManageDataPageMethodLibrary.verifyDataFile(sNameYourArtifact, startcount);
		MethodsLibrary.methodSignout();
		MethodsLibrary.methodQuitUrika();
		startcount++;
	}	
}
