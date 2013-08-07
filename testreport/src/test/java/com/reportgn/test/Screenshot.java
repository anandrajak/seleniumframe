package com.reportgn.test;
//<span class="IL_AD" id="IL_AD7">package</span> test.Lib;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import jxl.format.Format;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners (Screenshot.class)

public class Screenshot extends TestListenerAdapter {
    
	static WebDriver driver = new FirefoxDriver();

	@BeforeClass
	public void aseClass()
	{
		//driver.get("http://www.google.com");
		driver.get("http://en.wikipedia.org/wiki/Main_Page");
	}
	
	@Test
	public void testFindElements()throws Exception{
	 
	//find the About link
	WebElement about= driver.findElement
	(By.linkText("Toolbox"));
	 
	// click to the link
	
	about.click();
	 
	// wait for 5 seconds
	Thread.sleep(5000);
	 
	// write out the title of the page in console
	System.out.println(driver.getTitle());
	}
	
	@AfterClass
	public void tearDown()
	{
		driver.close();
		driver.quit();
		
	}
	
	  @Override
      public void onTestFailure(ITestResult result) {
                      String workingDirectory = System.getProperty("user.dir");
      String fileName = workingDirectory + File.separator + "screenshots" + File.separator +  result.getMethod().getMethodName() + ".png";//filename
      File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
      try {
		FileUtils.copyFile(scrFile, new File(fileName ));
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
    Reporter.log("<a href=\"" + fileName  + "\">  Clickhere  </a>"  );
   Reporter.setCurrentTestResult(null);
      
      }
	  
	  
	/*@Override
	public void onTestFailure(ITestResult result){
		Reporter.log("---Fail---");
		String methodName = Thread.currentThread().getStackTrace()[0].getMethodName();
		
   
	      Date date=new Date();
	 	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");   
	      File scrnsht = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
	       String NewFileNamePath=("/home/anandraja/screenshot/"+ formatter.format(date)+".png"); 
	      
	      try {
	          FileUtils.copyFile(scrnsht,new File(NewFileNamePath));
	          System.out.println(NewFileNamePath);
	    } catch (IOException e) {
	        // TODO Auto-generated catch block
	          e.printStackTrace();
	    }
	     Reporter.log("<a href=\"" + NewFileNamePath  + "\">  Clickhere  </a>"  );
	      Reporter.setCurrentTestResult(null);
	      
	      
	    File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scrFile, new File(file + "/" + new Throwable().fillInStackTrace().getStackTrace()[1].getMethodName() + "_" + s + ".png" ));
	      
	      
	  	     
	}*/

	      @Override
	      public void onTestSkipped(ITestResult result) {
	      // will be called after test will be skipped
	    	  Reporter.log("---Skip---");
	    	  String methodName = Thread.currentThread().getStackTrace()[0].getMethodName();
	  		 Date date=new Date();
		 	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd-hh-mm-ss");   
		      File scrnsht = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		       String NewFileNamePath=("/home/anandraja/screenshot/"+ formatter.format(date)+".png"); 
		      
		      try {
		          FileUtils.copyFile(scrnsht,new File(NewFileNamePath));
		          System.out.println(NewFileNamePath);
		    } catch (IOException e) {
		        // TODO Auto-generated catch block
		          e.printStackTrace();
		    }
		     Reporter.log("<a href=\"" + NewFileNamePath  + "\">  Clickhere  </a>"  );
		      Reporter.setCurrentTestResult(null);
		      
	        
	      }
	      @Override
	      public void onTestSuccess(ITestResult result) {
	      // will be called after test will pass
	        Reporter.log("Pass");
	      }
}