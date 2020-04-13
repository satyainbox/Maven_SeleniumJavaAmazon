package com.RegressionAutomation.Retail.testRunner;

import com.github.mkolisnyk.cucumber.runner.ExtendedCucumberOptions;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(features = "./src/test/resources/FeatureFiles",glue={"com.RegressionAutomation.Retail.StepDefinitions"},strict=true,plugin = {"html:target/cucumber-html-report/","json:target/cucumber.json","pretty:target/cucumber-pretty.txt","usage:target/cucumber-usage.json","junit:target/cucumber-results.xml","html:target/site/cucumber-pretty","com.cucumber.listner.ExtentCucumberFormatter:target/cucumber-reports/report.html"},monochrome=true,tags="@TCFT01")
@ExtendedCucumberOptions(jsonReport = "target/cucumber.json", retryCount = 1,detailedReport = true, detailedAggregatedReport = true, overviewReport = true, coverageReport = true, jsonUsageReport = "target/cucumber-usage.json",usageReport = true, toPDF = true, includeCoverageTags = {"@passed","@flaky"}, outputFolder = "target")
public class testRunner {

}
