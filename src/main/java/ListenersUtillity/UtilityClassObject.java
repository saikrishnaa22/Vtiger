package ListenersUtillity;

import org.openqa.selenium.WebDriver;

import com.aventstack.extentreports.ExtentTest;

public class UtilityClassObject {
	
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
	public static ThreadLocal<WebDriver> driver = new ThreadLocal<WebDriver>();
	
	
	public static ExtentTest getTest() {
		return test.get();
	}
	public static void setTestLocal(ExtentTest actual_test) {
		 test.set(actual_test);
	}
	public static WebDriver getDriver() {
		return driver.get();
	}
	public static void setDriver(WebDriver util_Driver) {
	     driver.set(util_Driver);
	}
	
}
