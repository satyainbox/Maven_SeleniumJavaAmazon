package com.AmazonRetail.GenericMethods;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import org.apache.poi.ss.usermodel.Workbook;

import com.AmazonRetail.Helpers.Report;

import cucumber.runtime.model.CucumberFeature;


public class Constants {
	
	
	public static DateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
	public static DateFormat dateFormate2= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
	public static Date date = new Date();
	public static String date1=dateFormat.format(date);
	public static String date2=dateFormate2.format(date);
	public static final String FIREFOX = "firefox";
	public static final String IE = "IE";
	public static final String CHROME = "chrome";	
	public static final String userDir = System.getProperty("user.dir");
	public static final String configpath  = userDir + "\\src\\test\\resources\\config\\config.properties";
	public static String Status;
	public static Object methodname;
	public static String ScreenShotFolder;
	public static String Reportname;
	public static String HTMLReportPath;
	public static Report report;
	public static Workbook workbook;
	public static Map<String, String> dATA_MAP1;
	public static CucumberFeature runFeature;
	public static String testUnderExecution = null;
	public static String executionFlag=null;
	
}
