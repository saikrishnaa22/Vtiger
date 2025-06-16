package OrganizationModule;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import org.openqa.selenium.support.ui.Select;
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

public class CreateOrgNameAndIndustryAndType {

	@Test
	
	public void CreateOrganisation_IndustryAndType() throws Exception
	{
		// fetch the data froom the property file 
		
		PropertyFileUtility  prop= new PropertyFileUtility();
		
		String Browser = prop.fetchdatafrompropertyfile("browser");
		String URL= prop.fetchdatafrompropertyfile("url");
		String Username= prop.fetchdatafrompropertyfile("username");
		String Password= prop.fetchdatafrompropertyfile("password");
		String Timeouits = prop.fetchdatafrompropertyfile("timeouts");
		
		// Fetch the random number
		  JavaUtility j= new JavaUtility();
		 int num = j.generateRandomNumber();
		
		// fetch the data from the excelfile 
		
		ExcelFileUtility ex_util = new ExcelFileUtility();
		String orgname=	ex_util.fetchThedatafromExcel("organization", 7, 3)+num;
		String industry=ex_util.fetchThedatafromExcel("organization", 7, 4)+ num;
		String type=ex_util.fetchThedatafromExcel("organization", 7, 5)+ num;
		
		WebDriverUtility  w=  new WebDriverUtility();
	
	   // launch the browser 
	   
	   
	   WebDriver driver= null;
	   
	   if (Browser.equals("chrome"))
	   {
		  driver= new ChromeDriver();
	   }
	   else if (Browser.equals("edge"))
	   {
		   driver= new EdgeDriver();
	   }
	   else
		{
			driver= new ChromeDriver();
		}
		
	   // Maximum the window 
	  w.Maxmizethewindow(driver);
	  
	  // Implicity wait
	  
     w.waittheelementFound(driver, Timeouits);
	  
	  // navigate to the url
	     w.NavigateToURL(driver, URL);
	  
	     // Login to Appication through POM page
		
		LoginPomPage l =new  LoginPomPage(driver);
		l.getUsername(Username);
		l.getPassword(Password);
		l.getLogin();

		Thread.sleep(2000);
		
         //click on organization tab in home
		HomePomPage h = new HomePomPage(driver);
		h.getOrganizationtab();
		
	 // click on plus symbol
		OrganizationPomPage org= new OrganizationPomPage(driver);
		org.getorgplusicon();
	
		// Enter the ORganization name and click on the save Button
	
	CreateNewOrganizationPomPage c= new CreateNewOrganizationPomPage(driver);
	c.getOrgname(orgname);
	WebElement ind_dd = c.getIndustryDD();
	w.SelectDropDownByValue(ind_dd, industry);
	WebElement type_dd = c.getTypeDD();
	w.SelectDropDownByValue(type_dd, type);
	c.getSave_btn();

	   
		// verify the organization name //
	OrganizationInfoPomPage org_info= new OrganizationInfoPomPage(driver);
	
		String org_header = org_info.getHeader();
		if (org_header.contains(orgname)) {
			System.out.println("Test Pass");
		} else {
			System.out.println("Test Fail");
		}			
		
		// verify the industry 
		
	String actid =org_info.getverifyIndustyDD();
	if (actid.equals(industry))
	{
		System.out.println("industry : pass");
	}
	else 
	{
		System.out.println("industry: fail");
	}
	
	// verify the type
	String actty = org_info.getverifyTypeDD();
	if (actty.equals(type))
	{
		System.out.println("type: pass");
	}
	else 
	{
		System.out.println("type : fail");
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
    
    /// Logout of the Application with pom
    h.getSignout();
    
	// close the browser

	w.QuittheBrowser(driver);
	
	// close excel
	ex_util.closeExcelWorkbook();
	
}

	   

	}

