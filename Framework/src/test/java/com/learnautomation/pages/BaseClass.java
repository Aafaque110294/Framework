package com.learnautomation.pages;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.MediaEntityModelProvider;
import com.aventstack.extentreports.reporter.ExtentAventReporter;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.learnautomation.utility.BrowserFactory;
import com.learnautomation.utility.ConfigDataProvider;
import com.learnautomation.utility.ExcelDataProvider;
import com.learnautomation.utility.Helper;

public class BaseClass {
	
	public WebDriver driver;
	public ExcelDataProvider excel;
	public ConfigDataProvider config;
	public ExtentReports report;
	public ExtentTest logger;
	
	@BeforeSuite
	public void setupSuit()
	{
		Reporter.log("Setting up reports and Test is getting ready", true);
		excel  = new ExcelDataProvider();
		config= new ConfigDataProvider();
		
		//creating the object of ExtentHtmlReporter and provide the path of folder(we will use system.getproperty in that we have predefined property called user.dir- it will give you current working directory, in our case it will return path of the framework followed by our FOlder name and report name)
		ExtentHtmlReporter extent=new ExtentHtmlReporter(new File(System.getProperty("user.dir")+"/Report/FreeCRM"+Helper.getCurrentDateTime()+".html"));
		//now we need to create object of extent report
		report= new ExtentReports();
		// attaching the html report
		report.attachReporter(extent); 
		// flushing the report in @AfterMethod, so that it should generate report
		
		Reporter.log("Setting done Test can be started", true);
	}

	@BeforeClass
	public void setup()
	{
		Reporter.log("Browser is starting and application is getting ready", true);
		
		driver= BrowserFactory.startApplication(driver, config.getBrowser(), config.getStagingURL());
		
		Reporter.log("Browser is up and application runnning", true);
	}
	

	@AfterClass
	public void tearDown()
	{
		BrowserFactory.quitBrowser(driver);
	}
	
	@AfterMethod
	public void tearDownMethod(ITestResult result) throws IOException //Itestresult is seperate interface
	{
		Reporter.log("Test is about to end", true);
		
		if(result.getStatus()==result.FAILURE)
		{		
			logger.fail("Test Failed", MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreeshot(driver)).build());
		}
		else if(result.getStatus()==result.SUCCESS)
		{
			logger.pass("Test passed", MediaEntityBuilder.createScreenCaptureFromPath(Helper.captureScreeshot(driver)).build());
		}
		report.flush();
		
		Reporter.log("Test completed >>> Reports generated", true);
	}

}
