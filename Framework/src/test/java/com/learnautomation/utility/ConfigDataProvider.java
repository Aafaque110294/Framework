package com.learnautomation.utility;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigDataProvider {
	
	Properties pro;// we have a properties class we will use that
	
	public ConfigDataProvider() {
		File src= new File("./Config/Config.properties");
		try {
			FileInputStream fis= new FileInputStream(src);
			pro= new Properties();
			pro.load(fis);
		} catch (Exception e) {
			System.out.println("Not able to Load config FIle"+ e.getMessage());
		}
	}
	
	//Creating a method which will take key and return a value like, getStagingURL(Test URL), Get Browser, Get data from confug
	
	public String getDataFromConfig(String KeyToSearch)
	{
		return pro.getProperty(KeyToSearch);
	}
	
	public String getBrowser()
	{
		return pro.getProperty("Browser");
	}
	
	public String getStagingURL()
	{
		return pro.getProperty("qaUrl");
	}

}
