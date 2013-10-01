/**
 * Copyright 2013 YarcData LLC All Rights Reserved.
 */
package com.yarcdata.tests.automation.managerules;
/**
 * <p>
 * To check Manage Rules - Deleting and Searching Rule files 
 * </p>
 * @author Venkatachalam
 * @author Saranya
 * @reviewer Promoth
 */

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.yarcdata.tests.automation.libraries.common.SeleniumTestBase;
import com.yarcdata.tests.automation.libraries.managerules.MethodLibrariesAddRules;
import com.yarcdata.tests.automation.libraries.managerules.MethodLibraryManageRulePage;

public class ManageRulesPage extends SeleniumTestBase{

	MethodLibraryManageRulePage manageRulePageMethodLibrary = new MethodLibraryManageRulePage();
	MethodLibrariesAddRules addRulesMethodLibrary = new MethodLibrariesAddRules();
	int startCount = 1;

	/**
	 * To retrieve data from Spreadsheet for deleting Rule files 
	 * @return retObjArr
	 * @throws Exception
	 */
	@DataProvider(name = "DP1")
	public Object[][] deleteTest() throws Exception{
		Object[][] retObjArr= FunctionsLibrary.getTableArray(seleniumInputFile, manageRulesSheetName, deleteRuleFile);
		return(retObjArr);
	}

	/**
	 * To delete Rule files from the Rules Table
	 * @param sNameYourRule
	 * @param sComment
	 * @throws Exception
	 */
	@Test(dataProvider = "DP1")
	public void deleteRuleTest (String sNameYourRule, String sComment) throws Exception 
	{
		int dpcount = deleteTest().length;		
		manageRulePageMethodLibrary.deleteRuleFile(sNameYourRule, sComment, startCount);
		if(dpcount == startCount)
			startCount =1;
		else
			startCount++;	
	}

	/**
	 * To retrieve data from Spreadsheet for canceling the Rule files deletion 
	 * @return retObjArr
	 * @throws Exception
	 */
	@DataProvider(name = "DP2")
	public Object[][] cancelDeleteTest() throws Exception{
		Object[][] retObjArr= FunctionsLibrary.getTableArray(seleniumInputFile, manageRulesSheetName, nonDeleteRule);
		return(retObjArr);
	}

	/**
	 * To cancel the Rule files deletion 
	 * @param sNameYourRule
	 * @throws Exception
	 */
	@Test(dataProvider = "DP2", dependsOnMethods = "deleteRuleTest")
	public void cancelDeleteRuleTest (String sNameYourRule) throws Exception 
	{
		int dpcount = cancelDeleteTest().length;
		manageRulePageMethodLibrary.cancelDeleteRule(sNameYourRule, startCount);
		if(dpcount == startCount)
			startCount =1;
		else
			startCount++;
	}

	/**
	 * To retrieve data from Spreadsheet for performing search functionality 
	 * @return retObjArr
	 * @throws Exception
	 */
	@DataProvider(name = "DP3")
	public Object[][] ruleSearchTestdata() throws Exception{
		Object[][] retObjArr= FunctionsLibrary.getTableArray(seleniumInputFile, manageRulesSheetName, ruleNameSearch);
		return(retObjArr);
	}

	/**
	 * To perform search functionality in the Rules Table
	 * @param sNameYourRule
	 * @throws Exception
	 */
	@Test(dataProvider = "DP3", dependsOnMethods = "cancelDeleteRuleTest")
	public void ruleSearchTest (String sNameYourRule) throws Exception 
	{
		int dpcount = ruleSearchTestdata().length;
		manageRulePageMethodLibrary.ruleSearch(sNameYourRule, startCount);
		if(dpcount == startCount)
			startCount =1;
		else
			startCount++;
	}
}