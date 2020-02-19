package com.learnautomation.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage {
	WebDriver driver;
	//creating constructor to help us to initialize the webdriver
	public LoginPage(WebDriver ldriver)// pass webdriver reference, it is required otherwise it will not be able to identify the driver
	{
		this.driver= ldriver;
	}
	
	@FindBy(name="username") WebElement username;
	@FindBy(name="password") WebElement pass;
	@FindBy(xpath="//input[@class='btn btn-small']") WebElement loginButton;
	
	//Method to login into the application
	
	public void loginToCRM(String emailApplication, String passwordApplication)
	{
		username.sendKeys(emailApplication);
		pass.sendKeys(passwordApplication);
		loginButton.click();
	}
}
