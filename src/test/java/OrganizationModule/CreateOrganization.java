package OrganizationModule;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import GenericUtilities.ExcelFileUtility;
import GenericUtilities.JavaUtility;
import GenericUtilities.PropertyFileUtility;
import GenericUtilities.WebDriverUtility;
import POMUtilities.CreateNewOrganizationPomPage;
import POMUtilities.HomePomPage;
import POMUtilities.LoginPomPage;
import POMUtilities.OrganizationInfoPomPage;
import POMUtilities.OrganizationPomPage;

public class CreateOrganization {

	@Test

	public void Createneworg() throws IOException, InterruptedException {
		// fetch the data from property file
		
		PropertyFileUtility prop = new PropertyFileUtility();
		
		String BROWSER = prop.fetchdatafrompropertyfile("browser");
		String URL =prop.fetchdatafrompropertyfile("url");
		String USERNAME = prop.fetchdatafrompropertyfile("username");
		String PASSWORD =prop.fetchdatafrompropertyfile("password");
		String TIMEOUTS =prop.fetchdatafrompropertyfile("timeouts");
		
		// Fetch the random number
		  JavaUtility j= new JavaUtility();
		 int num = j.generateRandomNumber();

          	// Fetch the data from Excel

		ExcelFileUtility ex_util = new ExcelFileUtility();
		
		String orgname=	ex_util.fetchThedatafromExcel("organization", 1, 3)+ num;
		
		WebDriverUtility w= new WebDriverUtility();
	
		
	     // launch the Browser
	      WebDriver driver= null;
	      if (BROWSER.equals("chrome"))
	      {
	    	driver= new ChromeDriver();
	      }
	      else if (BROWSER.equals("edge"))
	      {
	    	driver = new EdgeDriver();	  
	      }
	      else 
	      {
	    	  driver= new ChromeDriver();
	      }
	      
		// Maximum the window
		w.Maxmizethewindow(driver);
		
		// Implicity wait
	w.waittheelementFound(driver, TIMEOUTS);
	
		// Navigate to the Application
	w.NavigateToURL(driver, URL);

	
	  // Login to Appication through POM page
		
		LoginPomPage l =new  LoginPomPage(driver);
		l.getUsername(USERNAME);
		l.getPassword(PASSWORD);
		l.getLogin();

	//click on organization tab with home
		HomePomPage h = new HomePomPage(driver);
		h.getOrganizationtab();
		
		// click on plus symbol
		OrganizationPomPage org= new OrganizationPomPage(driver);
		org.getorgplusicon();
		
		// Enter the ORganization name and click on the save Button
		CreateNewOrganizationPomPage c_new =new CreateNewOrganizationPomPage(driver);
		c_new.getOrgname(orgname);
		c_new.getSave_btn();
	
		// verify the organization name  //
	 OrganizationInfoPomPage org_info= new OrganizationInfoPomPage(driver);
		String org_header = org_info.getHeader();
		if (org_header.contains(orgname)) {
			System.out.println("Test Pass");
		} else {
			System.out.println("Test Fail");
		}
		
		//click on organization tab with pom
		h.getOrganizationtab();
		
		
		// Delete the Created organization
		driver.findElement(
				By.xpath("//a[text()='" + orgname + "']/ancestor::tr[@bgcolor='white']/descendant::a[text()='del']"))
				.click();
		Thread.sleep(3000);
		// Handle the Alert pop
		w.handleAlertandClickoK(driver);
		
		// Mousing Hovering on the admin icon using pom
		
		WebElement admin=h.getAdminicon();

		w.Mouseovering(driver, admin);  
	    
	    // Logout of the Application with pom
	    h.getSignout();
	    
		// close the browser
	
		w.QuittheBrowser(driver);
		
		// close the workbook
		ex_util.closeExcelWorkbook();

	}

}
