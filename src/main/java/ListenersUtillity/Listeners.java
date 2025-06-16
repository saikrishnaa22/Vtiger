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

import BaseClassUtility.BaseClass;

public class Listeners implements ITestListener,ISuiteListener{

	@Override
	public void onStart(ISuite suite) {
		Reporter.log("Test start execution", true);
		Reporter.log("Configure the Report", true);
	}

	@Override
	public void onFinish(ISuite suite) {
		  Reporter.log("Test finish execution", true);
		  Reporter.log("Report Backup", true);
	}

	@Override
	public void onTestStart(ITestResult result) {
		Reporter.log("ON Test Start execution", true);
		String testname = result.getMethod().getMethodName();
		Reporter.log("Test method execution started<=="+testname+"==>", true);
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		Reporter.log(" On Test success execution", true);
		String testname = result.getMethod().getMethodName();
		Reporter.log("Test method execution successfull<=="+testname+"==>", true);
	}

	@Override
	public void onTestFailure(ITestResult result) {
		Reporter.log(" OnTest Failure execution", true);
		String testname = result.getMethod().getMethodName();
		Reporter.log("Test method execution failed<=="+testname+"==>", true);
		
		//Take ScreenShot
		
	String timestamp = new Date().toString().replace("","_").replace(":", "_");
		
		  TakesScreenshot tks = (TakesScreenshot)BaseClass.sdriver;
		   File src = tks.getScreenshotAs(OutputType.FILE);
		   File dest = new File("./Screenshot/"+testname +"+timestamp"+".png");
				   try {
			FileUtils.copyFile(src, dest);
		} catch (IOException e) {
		
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		Reporter.log("Test finish execution", true);
		String testname = result.getMethod().getMethodName();
		Reporter.log("Test method execution skipped<=="+testname+"==>", true);
	}
	
	
	

}
