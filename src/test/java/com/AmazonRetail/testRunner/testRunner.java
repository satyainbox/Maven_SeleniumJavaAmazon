package com.AmazonRetail.testRunner;

import com.AmazonRetail.GenericMethods.BaseSetUp;
import com.AmazonRetail.GenericMethods.Constants;
import com.github.mkolisnyk.cucumber.runner.ExtendedCucumberOptions;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import java.util.List;
import org.junit.runner.RunWith;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.TestNGCucumberRunner;
import cucumber.runtime.model.CucumberFeature;

@RunWith(Cucumber.class)
@CucumberOptions(features = "./src/test/resources/FeatureFiles/TC_01_Validate_Account_Details.feature",glue={"com.AmazonRetail.StepDefinitions"},strict=true,plugin = {"html:target/cucumber-html-report/","json:target/cucumber.json","pretty:target/cucumber-pretty.txt","usage:target/cucumber-usage.json","junit:target/cucumber-results.xml","html:target/site/cucumber-pretty","com.cucumber.listner.ExtentCucumberFormatter:target/cucumber-reports/report.html"},monochrome=true,tags="@TCFT01")
@ExtendedCucumberOptions(jsonReport = "target/cucumber.json", retryCount = 1,detailedReport = true, detailedAggregatedReport = true, overviewReport = true, coverageReport = true, jsonUsageReport = "target/cucumber-usage.json",usageReport = true, toPDF = true, includeCoverageTags = {"@passed","@flaky"}, outputFolder = "target")
public class testRunner extends BaseSetUp {
	
	private TestNGCucumberRunner testNGCucumberRunner;	
	@Parameters({"featureName","testName"})
	@BeforeTest(alwaysRun=true)
	public void setupTest(String featurename,String testName)
	{
		testNGCucumberRunner=new TestNGCucumberRunner(this.getClass());
		List<CucumberFeature> featureList=testNGCucumberRunner.getFeatures();
		testUnderExecution=testName;
		for(CucumberFeature features : featureList)
		{
			if(featurename.equalsIgnoreCase(features.getPath()))
			{
				runFeature=features;
				break;
			}
		}
	}
	
	@BeforeClass(alwaysRun = true)
	public void setUpClass() throws Exception
	{
		testNGCucumberRunner= new TestNGCucumberRunner(this.getClass());
	}

	
	@Test(groups = "cucumber" , description = "Runs cucumber feature" , invocationCount = 1,threadPoolSize= 1)
	public void testNGCucumberRunner_() throws Exception {
		testNGCucumberRunner.runCucumber(runFeature);
	}
	
	@AfterMethod
	public void getResult(ITestResult result)
	{
		try
		{
			if(!report.equals(null)){
				ExtentTest logger = report.logger;
				ExtentReports extent = report.extent;
				if(result.getStatus() == ITestResult.FAILURE)
				{
					logger.log(LogStatus.FAIL, "Test Case Failed is "+result.getName());
				}
				else if(result.getStatus() == ITestResult.SKIP)
				{
					logger.log(LogStatus.SKIP, "Test Case skipped is "+result.getName());
				}
				extent.endTest(logger);
				extent.flush();
				if (!driver.equals(null)) {
					// driver.close();
					// driver.quit();
				}
				
			}
		}catch (Exception E)
		{
			
		}
	}
	
	@AfterClass(alwaysRun = true)
	public void tearDownClass() throws Exception
	{
		testNGCucumberRunner.finish();
	}
	
	@AfterTest
	public void tearDown() throws Exception{
//		if(!driver.equals(null))
//		{
//			
//		}
	}

}
