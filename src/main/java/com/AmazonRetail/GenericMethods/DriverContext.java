package com.AmazonRetail.GenericMethods;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

public class DriverContext {
	
	static WebDriver driver;
	
	private DriverContext()
	{
		this.getDriver();
	}
	
	public static WebDriver getDriver()
	{
		driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);

		return driver;
	}
	
	
	public static void setDriver(WebDriver driver)
	{
		DriverContext.driver=driver;
	}
	
	public static WebDriver getInstance()
	{
		if(driver == null)
		{
			driver = (WebDriver) new DriverContext();
			return driver;
		}
		else
		{
			return driver;
		}
	}

}
