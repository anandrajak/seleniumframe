package com.yarcdata.tests.automation.libraries.common;

import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;

import java.awt.*;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;

public class FunctionsLibrary {

	/**
	 * To wait for an object to get loaded in a page
	 * @param iTimeOutInSeconds
	 */

	public void functionWaitForAnObject (int iTimeOutInSeconds){
		int iSeconds = (iTimeOutInSeconds * 1000);
		try {
			Thread.sleep(iSeconds);
		} catch (InterruptedException e) {
			e.printStackTrace();  
		}
	}

	/**
	 * To read the values from pom.xml
	 * @param sReport
	 * @param sNodeString
	 * @return sReturn
	 */

	public static String functionReadXMLData (String sReport, String sNodeString){
		String sReturn = "Invalid";
		try{
			FileInputStream fstream = new FileInputStream(sReport);
			// Get the object of DataInputStream
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;

			//Read File Line By Line
			while ((strLine = br.readLine()) != null)   {
				if (strLine.contains(sNodeString)){
					int position = strLine.indexOf (">");
					String temp = strLine.substring(position+1, strLine.length());
					position = temp.indexOf ("</");
					temp = temp.substring(0, position);
					return temp;
				}
			}
			in.close();
		}
		catch (Exception e){
			System.err.println("Error: " + e.getMessage());
		}
		return sReturn;

	}//End Method
	/**
	 * To read the test input data from spreadsheet
	 * @param xlFilePath
	 * @param sheetName
	 * @param tableName
	 * @return tabArray
	 * @throws Exception
	 */

	public String[][] getTableArray(String xlFilePath, String sheetName, String tableName) throws Exception{
		String[][] tabArray=null;

		Workbook workbook = Workbook.getWorkbook(new File(xlFilePath));
		Sheet sheet = workbook.getSheet(sheetName); 
		int startRow,startCol, endRow, endCol,ci,cj;
		Cell tableStart=sheet.findCell(tableName);
		startRow=tableStart.getRow();
		startCol=tableStart.getColumn();

		Cell tableEnd= sheet.findCell(tableName, startCol+1,startRow+1, 100, 64000,  false);                

		endRow=tableEnd.getRow();
		endCol=tableEnd.getColumn();
		tabArray=new String[endRow-startRow-1][endCol-startCol-1];
		ci=0;

		for (int i=startRow+1;i<endRow;i++,ci++){ 
			cj=0;
			for (int j=startCol+1;j<endCol;j++,cj++){
				tabArray[ci][cj]=sheet.getCell(j,i).getContents();
			}
		}

		return(tabArray);
	}

	/**
	 * To delete an existing folder and create a new folder for storing screenshots
	 * @param cName
	 * @throws AWTException
	 * @throws IOException
	 */

	public void deleteFile(String cName) throws AWTException, IOException {
		//To make a new directory called "Screenshots"
		String basePath = System.getProperty ("user.dir")+"/"+"target"+"/"+"Screenshots"+"/";
		File dir = new File (basePath);
		dir.mkdir();
		Date date = new Date();
		Format formatter;
		formatter = new SimpleDateFormat("yyyyMMddHHmmssSS");
		String s = formatter.format(date);

		File file = new File(basePath+cName);
		String filename = new Throwable().fillInStackTrace().getStackTrace()[1].getMethodName() + "_" + s;

		//To delete any existing folders and create a new folder
		if (file.exists()) 
		{
			FileUtils.forceDelete(file);
			file.mkdir();
		}
		else {
			file.mkdir();
		}
	}

}
