/**
 * Copyright 2013 YarcData LLC All Rights Reserved.
 */
package com.yarcdata.tests.automation.managerules;
/**
 * <p>
 * To check Manage Rules - Adding Rule files positive and negative scenarios
 * </p>
 * @author Saranya
 * @author Venkatachalam
 * @reviewer Promoth
 */

import java.awt.AWTException;
import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.yarcdata.tests.automation.libraries.common.SeleniumTestBase;
import com.yarcdata.tests.automation.libraries.managerules.MethodLibrariesAddRules;
import com.yarcdata.tests.automation.libraries.managerules.MethodLibraryManageRulePage;

public class AddInferencingRules extends SeleniumTestBase{
	
	MethodLibraryManageRulePage manageRulePageMethodLibrary = new MethodLibraryManageRulePage();
	MethodLibrariesAddRules addRulesMethodLibrary = new MethodLibrariesAddRules();
	int startcount = 1;
	
	/**
	 * To retrieve Rule file data from a Spreadsheet- Positive Flow
	 * @return retObjArr
	 * @throws Exception
	 */
	@DataProvider(name = "DP1")
	public Object[][] postiveTestdataWithLocalfile() throws Exception{
		Object[][] retObjArr= FunctionsLibrary.getTableArray(seleniumInputFile, manageRulesSheetName, addRuleFileTable);
		return(retObjArr);
	}
	
	/**
	 * To upload Single/Multiple Rule file - Positive Flow 
	 * @param sNameYourRule
	 * @param sDataComment
	 * @throws Exception
	 */
	@Test(dataProvider = "DP1")
	public void addInferencingRulesTest (String sNameYourRule, String sComment) throws Exception 
	{
		int dpcount = postiveTestdataWithLocalfile().length;
		if (startcount == 1) {
			manageRulePageMethodLibrary.verifyManageRuleComponents();
			manageRulePageMethodLibrary.verifyRuleManagementTableComponents();
			addRulesMethodLibrary.verifyAddRuleComponents();
		}
		addRulesMethodLibrary.addMultipleRuleFiles(sNameYourRule, sComment, startcount, dpcount);
		if(dpcount == startcount)
			startcount =1;
		else
			startcount++;
	}
	
	/**
	 * To retrieve Rule File data from a Spreadsheet - Negative Flow 
	 * @return retObjArr
	 * @throws Exception
	 */
	@DataProvider(name = "DP2")
	public Object[][] negativeTestdataWithLocalfile() throws Exception{
		Object[][] retObjArr= FunctionsLibrary.getTableArray(seleniumInputFile, manageRulesSheetName, ruleFileNegativeCases);
		return(retObjArr);
	}
	
	/**
	 * To upload Single/Multiple Rule file - Negative Flow
	 * @param sNameYourRule
	 * @param sDataComment
	 * @throws Exception
	 */
	@Test(dataProvider = "DP2", dependsOnMethods = "addInferencingRulesTest")
	public void addInvalidInferencingRulesTest (String sNameYourRule, String sComment, String sRuleFilePath, String sErrorMessages) throws Exception 
	{
		if (startcount == 1) {
			addRulesMethodLibrary.addInvalidRuleFiles(sNameYourRule, sComment, sRuleFilePath, sErrorMessages, startcount);
		}
	}
}
