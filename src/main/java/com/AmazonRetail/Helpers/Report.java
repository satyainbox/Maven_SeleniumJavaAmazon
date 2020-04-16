package com.AmazonRetail.Helpers;

import java.io.File;
import static org.testng.Assert.*;

import org.codehaus.plexus.util.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import com.AmazonRetail.GenericMethods.Constants;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class Report {
	
	public ExtentReports extent = null;
	public ExtentTest logger = null;
	int Count = 0;

	public void passTest(String Message, WebDriver driver) {
		try {
			TakeScreenSHOTAndReportStatus(LogStatus.PASS, Message, driver);
			Constants.Status = "PASS";
			Constants.methodname = "PASS";
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void failTest(String Message, WebDriver driver) {
		try {
			TakeScreenSHOTAndReportStatus(LogStatus.FAIL, Message, driver);
			Constants.Status = "FAIL";
			Constants.methodname = "Reason of failure: " + Message;
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void infoTest(String Message, WebDriver driver) {
		try {
			TakeScreenSHOTAndReportStatus(LogStatus.INFO, Message, driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void failAndTerminateTest(String Message, WebDriver driver) {
		try {
			TakeScreenSHOTAndReportStatus(LogStatus.FATAL, Message, driver);
			Constants.Status = "FAIL";
			/*
			 * String name = new Object(){}.getClass().getClass().getName();
			 * System.out.println(name);
			 */
			Constants.methodname = "Reason of failure: " + Message;
			/*
			 * System.out.println(Constants.methodname);
			 * System.out.println(Constants.Status);
			 */
			assertFalse(true);
		} catch (Exception e) {
			Constants.Status = "PASS";
			e.printStackTrace();
		}
	}

	public void infoAndTerminateTest(String Message, WebDriver driver) {
		try {
			TakeScreenSHOTAndReportStatus(LogStatus.WARNING, Message);
			assertFalse(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void warningTest(String Message, WebDriver driver) {
		try {
			TakeScreenSHOTAndReportStatus(LogStatus.WARNING, Message, driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void endReport() {
		// extent.flush();
		extent.close();
	}

	public void TakeScreenSHOTAndReportStatus(LogStatus log, String Message, WebDriver...driver)
	{
		Constants cons = new Constants();
		try {
			if (driver.length > 0) {
				TakesScreenshot ts = (TakesScreenshot) driver[0];
				File source = ts.getScreenshotAs(OutputType.FILE);
				String destination = cons.ScreenShotFolder + "\\Screen" + Count + ".png";
				// System.out.println(destination);
				File Destination = new File(destination);
				FileUtils.copyFile(source, Destination);
				String destination1 = Constants.Reportname + "\\Screen" + Count + ".png";
				/*
				 * Added one more string destination1 because the screenshots
				 * are not reading as expected so the destination will be given
				 * as ./reportname...
				 */
				logger.log(log, Message + logger.addScreenCapture(destination1));
				Count++;
			} else {
				logger.log(log, Message);
			}
		} catch (Exception e) {
			e.getMessage();
		}
	}

	public void passAndTerminateTest(String Message, WebDriver driver) {
		try {
			TakeScreenSHOTAndReportStatus(LogStatus.PASS, Message, driver);
			Constants.Status = "PASS";
			/*
			 * String name = new Object(){}.getClass().getClass().getName();
			 * System.out.println(name);
			 */
			/*
			 * System.out.println(Constants.methodname);
			 * System.out.println(Constants.Status);
			 */
			assertFalse(true);
		} catch (Exception e) {
			Constants.Status = "PASS";
			e.printStackTrace();
		}
	}

}
