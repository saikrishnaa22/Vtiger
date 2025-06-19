package ListenersUtillity;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.logging.FileHandler;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.devtools.v131.target.model.FilterEntry;
import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import BaseClassUtility.BaseClass;

public class Listenersss implements ITestListener,ISuiteListener{

	
	public static ExtentTest test ;
	public static ExtentReports  report;
	public  ExtentSparkReporter spark;
	@Override
	public void onStart(ISuite suite) {
		Reporter.log("Test start execution", true);
		Reporter.log("Configure the Report", true);
		String timestamp = new Date().toString().replace("","_").replace(":", "_");
		
		//Configure report
	    spark =new ExtentSparkReporter("./AdvanceReport/Report.html");
		spark.config().setDocumentTitle("Vtiger CRM Application ");
		spark.config().setReportName("Vtiger");
		spark.config().setTheme(Theme.DARK);
		
		// here created obj is declared globelly
	    report = new ExtentReports();
		report.attachReporter(spark);
		report.setSystemInfo("OS", "Windows 11");
		report.setSystemInfo("Browser", "version: 137.0.7151.104");

		
	}

	@Override
	public void onFinish(ISuite suite) {
		  Reporter.log("Test finish execution", true);
		  Reporter.log("Report Backup", true);
		  report.flush();
	}

	@Override
	public void onTestStart(ITestResult result) {
		Reporter.log("ON Test Start execution", true);
		String testname = result.getMethod().getMethodName();
		test = report.createTest(testname);
		UtilityClassObject.setTestLocal(test);
	//	Reporter.log("Test method execution started<=="+testname+"==>", true);
		test.log(Status.INFO,"Test method execution started<=="+testname+"==>");
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		Reporter.log(" On Test success execution", true);
		String testname = result.getMethod().getMethodName();
	//	Reporter.log("Test method execution successfull<=="+testname+"==>", true);
	test.log(Status.INFO,"Test method execution successfull<=="+testname+"==>");
		
	}

	@Override
	public void onTestFailure(ITestResult result) {
		Reporter.log(" OnTest Failure execution", true);
		String testname = result.getMethod().getMethodName();
		//Reporter.log("Test method execution failed<=="+testname+"==>", true);
		
		//Take ScreenShot
		
	String timestamp = new Date().toString().replace("","_").replace(":", "_");
		
		  TakesScreenshot tks = (TakesScreenshot)BaseClass.sdriver;
		  String src = tks.getScreenshotAs(OutputType.BASE64);
		  test.addScreenCaptureFromBase64String(src+""+testname+"--"+timestamp+"");
			test.log(Status.INFO,"Test method execution failed<=="+testname+"==>");
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		Reporter.log("Test finish execution", true);
		String testname = result.getMethod().getMethodName();
	//Reporter.log("Test method execution skipped<=="+testname+"==>", true);
	test.log(Status.INFO,"Test method execution failed<=="+testname+"==>");
	}
	
	
	

}
