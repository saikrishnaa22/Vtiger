package BaseClassUtility;

import java.io.IOException;
import java.sql.SQLException;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Reporter;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import GenericUtilities.DataBaseUtility;
import GenericUtilities.PropertyFileUtility;
import GenericUtilities.WebDriverUtility;
import ListenersUtillity.UtilityClassObject;
import POMUtilities.HomePomPage;
import POMUtilities.LoginPomPage;

public class BaseClass {
	
	public WebDriver driver = null;
	public  static  WebDriver sdriver= null;

	DataBaseUtility db_util = new DataBaseUtility();
	PropertyFileUtility prop = new PropertyFileUtility();
	WebDriverUtility w = new WebDriverUtility();

	@BeforeSuite
	public void ConnectionWithDB() throws SQLException {
		db_util.getdatabaseconnection();
		Reporter.log("Database Connected", true);
	}

	@AfterSuite
	public void CloseConnectionWithDB() throws SQLException {
		db_util.closeDataBaseconnection();
		Reporter.log("Database Connection failed", true);
	}

	@BeforeTest
	public void ConfigureParallelExe() {
		Reporter.log("Configure the Parallel Exe", true);
	}

	@AfterTest
	public void CloseParallelExe() {
		Reporter.log("Configure the Parallel Exe", true);
	}
    
	//@Parameters("browser")
	@BeforeClass
	public void LaunchTheBrowser() throws IOException {

		//String Browser = prop.fetchdatafrompropertyfile("browser");
		String Browser = System.getProperty("browser", prop.fetchdatafrompropertyfile("browser"));

		Reporter.log("Launch the browser", true);
		if (Browser.equals("chrome")) {
			driver = new ChromeDriver();
		} else if (Browser.equals("edge")) {
			driver = new EdgeDriver();
		} else {
			driver = new ChromeDriver();
		}
         sdriver = driver;
         UtilityClassObject.setDriver(driver);
	}

	@AfterClass
	public void CloseTheBrowser() {

		Reporter.log("Close the browser", true);
		driver.quit();

	}

	@BeforeMethod
	public void Login() throws IOException {
		/*String Url = prop.fetchdatafrompropertyfile("url");
		String Username = prop.fetchdatafrompropertyfile("username");
		String Password = prop.fetchdatafrompropertyfile("password");
		String Timeouts = prop.fetchdatafrompropertyfile("timeouts");
		*/
		String Url = System.getProperty("url", prop.fetchdatafrompropertyfile("url"));
		String Username =System.getProperty("username", prop.fetchdatafrompropertyfile("username"));
		String Password =System.getProperty("password", prop.fetchdatafrompropertyfile("password"));
		String Timeouts =System.getProperty("timeouts", prop.fetchdatafrompropertyfile("timeouts")); 
				
		LoginPomPage l = new LoginPomPage(driver);
		Reporter.log("Navigated and logged in", true);
		w.Maxmizethewindow(driver);
		l.login(Url,Username, Password);
		w.waittheelementFound(driver, Timeouts);

	}

	@AfterMethod
	public void Logout() {
		Reporter.log("Logout", true);
		HomePomPage h = new HomePomPage(driver);
		WebElement admin = h.getAdminicon();
		w.Mouseovering(driver, admin);
		h.getSignout();

	}

}
