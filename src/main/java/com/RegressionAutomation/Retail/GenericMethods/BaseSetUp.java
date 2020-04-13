package com.RegressionAutomation.Retail.GenericMethods;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.RegressionAutomation.Retail.Helpers.ReadConfigProperty;
import com.RegressionAutomation.Retail.Helpers.Report;
import com.relevantcodes.extentreports.ExtentReports;

import io.github.bonigarcia.wdm.ChromeDriverManager;
public class BaseSetUp extends Constants {
	public static RemoteWebDriver driver = null;
	@BeforeSuite
	public void initializeTest() {
		HTMLReportPath = userDir + ReadConfigProperty.getConfigValues("ReportPath");
		GenericMethods.createDirectory(HTMLReportPath);
		report = new Report();
		report.extent = new ExtentReports(HTMLReportPath+"\\"+ReadConfigProperty.getConfigValues("ProjectName")+"-"+date1+".html", true);
	}
	
	public RemoteWebDriver launchBrowser(String Browser, String Machine) {
		
		if (Machine.equalsIgnoreCase("GRID")) {
			
		}
		else
		{
			Browser = Browser.toUpperCase();
			switch (Browser){
			case CHROME:
				chrome();
				break;
			case IE:
			case FIREFOX:
			default:
			}
		
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
		return driver;
		
	}
	
	private void chrome()
	{
		ChromeDriverManager.chromedriver().setup();
		driver=new ChromeDriver();
		
	}
	
	@AfterSuite
	public void closeReport() {
		if (!report.equals(null)) {
			report.endReport();
		}
	}

}
