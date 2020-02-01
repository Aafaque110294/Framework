package com.learnautomation.testcases;

import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import com.learnautomation.pages.BaseClass;
import com.learnautomation.pages.LoginPage;
//import com.learnautomation.utility.ExcelDataProvider;

public class LoginTestCRM extends BaseClass {
	
	@Test
	public void loginApp()
	{	
		
		// Reporting part, already created in base class
		logger=report.createTest("Login to CRM"); // this will return object of extenttest which is the logger
		// Creating a page object, we have a class called pagefactory class, which has 1 method called initialize elements, which will ask you which page to initialize
		LoginPage loginpage= PageFactory.initElements(driver, LoginPage.class);
		
		// taking info from log
		logger.info("Starting Application");
		
		// LoginPage.class will return the object of the same class i.e Login page
		loginpage.loginToCRM(excel.getStringData("Login", 0, 0), excel.getStringData("Login", 0, 1));
		
		// when test pass we will use again logger for log purpose
		logger.pass("Login sucessfull");
		
	}
	
}
