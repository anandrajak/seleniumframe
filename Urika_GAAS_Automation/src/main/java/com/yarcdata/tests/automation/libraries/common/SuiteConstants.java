/**
 * Copyright 2013 YarcData LLC All Rights Reserved.
 */

package com.yarcdata.tests.automation.libraries.common;

/**
 * <p>
 * SuiteConstants - To perform functionalities before and after each test class execution
 * </p>
 * @author Venkatachalam
 */

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class SuiteConstants {
	protected FunctionsLibrary FunctionsLibrary = new FunctionsLibrary ();

	String sPOMXML = System.getProperty ("user.dir") + "/pom.xml";
//	String sDataFile = FunctionsLibrary.functionReadXMLData(sPOMXML, "project.datalibrary");

	protected static String seleniumPropertyFile;
	protected static String seleniumInputFile;
	protected static String dataInputFile;
	protected static String ruleInputFile;
	protected static String rawrdfInputfile;
	protected static String CopyRawRdf;
	
	protected static String sBrowserType;
	protected static String sUrikaMachine;
	protected static String sUsername;
	protected static String sPassword;

	public static String managedatasheetname;
	public static String adddatatableurl;
	public static String urlNegativescenarios;
	public static String adddatatablelocalfile;
	public static String localFilenegativescenarios;
	public static String adddeletedatatablelocalfile;
	public static String adddatatableRawRDF;
	public static String rawRDFnegativescenarios;
	public static String cancelAdddatatablelocalfile;
	public static String twouseradddatatable;
	public static String hugefiledata;
	public static String cancelAdddatatableRawRdf;
	public static String twouseraddtablerawrdf;
	
	public static String manageRulesSheetName;
	public static String deleteall;
	public static String addRuleFileTable;
	public static String ruleFileNegativeCases;
	public static String deleteRuleFile;
	public static String nonDeleteRule;
	public static String ruleNameSearch;
	

	public static void loadproperties() throws FileNotFoundException, IOException{

		Properties props = new Properties();
		seleniumPropertyFile = System.getProperty("selenium.properties"); 
		seleniumInputFile = System.getProperty("selenium.input.file.location");
		dataInputFile = System.getProperty("dataset.file.location");
		ruleInputFile = System.getProperty("ruleset.file.location");
		rawrdfInputfile = System.getProperty("rawrdfinputfile.location");

		props.load(new FileInputStream(seleniumPropertyFile));
		sBrowserType = props.getProperty("urika.browser");
		sUrikaMachine = props.getProperty("urika.url");
		sUsername = props.getProperty("urika.username");
		sPassword = props.getProperty("urika.password");

		//Manage data Sheet input 	
		managedatasheetname = props.getProperty("excel.Managedata");

		//Add Url Table Names
		adddatatableurl = props.getProperty("excel.AdddatatableURL");
		urlNegativescenarios = props.getProperty("excel.URLNegativescenarios");

		//Add Local File Table Names
		adddatatablelocalfile = props.getProperty("excel.Adddatatable_localfile");
		localFilenegativescenarios = props.getProperty("excel.LocalFile_Negativescenarios");
		twouseradddatatable = props.getProperty("excel.TwouserAdddatatable");

		//Add RawRdf Table Names
		adddatatableRawRDF = props.getProperty("excel.Adddatatable_RawRDF");
		rawRDFnegativescenarios = props.getProperty("excel.RawRDF_Negativescenarios");
		twouseraddtablerawrdf=props.getProperty("excel.TwouserAdddatatableRawRDF");

		//Manage Rules Sheet Input
		manageRulesSheetName = props.getProperty("excel.ManageRules");
		
		//Add Rules Table Names
		deleteall = props.getProperty("excel.deleteall");
		
		addRuleFileTable = props.getProperty("excel.AddRuleFileTable");
		ruleFileNegativeCases = props.getProperty("excel.RuleFile_NegativeCases");
		deleteRuleFile = props.getProperty("excel.deleteRule");
		nonDeleteRule =  props.getProperty("excel.nonDeleteRule");
		ruleNameSearch = props.getProperty("excel.ruleNameSearch");
	}
}
