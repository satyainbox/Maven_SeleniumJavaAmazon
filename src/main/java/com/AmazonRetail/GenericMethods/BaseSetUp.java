package com.AmazonRetail.GenericMethods;

import java.util.concurrent.TimeUnit;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.xml.XmlTest;
import com.AmazonRetail.Helpers.ReadConfigProperty;
import com.AmazonRetail.Helpers.Report;
import com.relevantcodes.extentreports.ExtentReports;
import io.github.bonigarcia.wdm.ChromeDriverManager;

public class BaseSetUp extends Constants {
	
	public static RemoteWebDriver driver = null;
	@BeforeSuite
	public void initializeTest(XmlTest xmlTest) {
		HTMLReportPath = userDir + ReadConfigProperty.getConfigValues("ReportPath");
		GenericMethods.createDirectory(HTMLReportPath);
		report = new Report();
		report.extent = new ExtentReports(HTMLReportPath+"\\"+ReadConfigProperty.getConfigValues("ProjectName")+"-"+date1+".html", true);
		Constants.dATA_MAP1=GenericMethods.getDataFromExcel(xmlTest.getParameter("testName"));
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
