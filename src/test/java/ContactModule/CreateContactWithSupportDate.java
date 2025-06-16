package ContactModule;

import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
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
import POMUtilities.ContactInfoPompage;
import POMUtilities.ContactPomPage;
import POMUtilities.CreateNewContactPom;
import POMUtilities.HomePomPage;
import POMUtilities.LoginPomPage;

public class CreateContactWithSupportDate {
	
	@Test
	
	public void CreateConttsupportdate() throws IOException, InterruptedException {
		
	
		PropertyFileUtility prop = new PropertyFileUtility();
		
		String Browser = prop.fetchdatafrompropertyfile("browser");
		String Url =prop.fetchdatafrompropertyfile("url");
		String Username =prop.fetchdatafrompropertyfile("username");
		String Password = prop.fetchdatafrompropertyfile("password");
		String Timeouts = prop.fetchdatafrompropertyfile("timeouts");
		
		// Fetch the random number
		  JavaUtility j= new JavaUtility();
		 int num = j.generateRandomNumber();
		
         // fetch the data from excel
		ExcelFileUtility ex_util = new ExcelFileUtility();
		String contctname=	ex_util.fetchThedatafromExcel("contact", 4, 3)+num;
	
		// Launnch the browser
		
		WebDriver driver= null;
		if (Browser.equals("Chrome"))
		{
			driver= new ChromeDriver();
		}
		else	if (Browser.equals("edge"))
		{
			driver= new EdgeDriver();
		}
		else
		{
			driver = new ChromeDriver();
		}
		
		CreateNewContactPom cn= new CreateNewContactPom(driver);
		WebDriverUtility w= new WebDriverUtility();
		ContactPomPage c= new ContactPomPage(driver);
		
		
		// Maximum the window
		
		w.Maxmizethewindow(driver);;
		// Implicity wait
	
		w.waittheelementFound(driver, Timeouts);
		
		// Navigate to the Application

		w.NavigateToURL(driver, Url);

	// Login to Appication through POM page
		
		LoginPomPage l =new  LoginPomPage(driver);
		l.getUsername(Username);
		l.getPassword(Password);
		l.getLogin();
		
		
    /// click on the contact tab in homepage using pom
		 HomePomPage h = new HomePomPage(driver);
		 h.getContacttab();
		 
		// click on plus symbol
		
		c.getConplusicon();
	   
		// Enter the contact name and pass the support date
	   
	     cn.getLastnametf(contctname);
	     WebElement support_start_date = cn.getStartdatetf();
	     support_start_date.clear();
	
		// fetch the  current date 	
	     JavaUtility jutil =new JavaUtility();
	    
		String _start_date = jutil.getcurrentsystemdate();
		support_start_date.sendKeys(_start_date);
		
	      WebElement end_support = cn.getEnddatetf();
	      end_support.clear();
		
		// fetch the  End date  after the 30 days 		
		String support_end_date =jutil.getdateAfterGivendays(30);
		
	   end_support.sendKeys(support_end_date);
	
		cn.getSave_btn();
	
		
	    // verify the contact name //
		ContactInfoPompage c_info= new ContactInfoPompage(driver);
		
		 String header = c_info.getHeader();
		    if (header.contains(contctname))
		    {
		    	System.out.println("Test : pass");
		    }
		    else 
		    {
		    	System.out.println("Test : fail");
		    }
		    
		    // Verify the Support  start date 
		    
		String  start_date =c_info.getVerifystartdate();
		if (start_date.contains(_start_date))
		{
			System.out.println("Successfully Start date created");
		}
		else 
		{
			System.out.println("Not created Start date");
		}
		
		// Verify the  Support End date 
		String end_date  =c_info.getVerifyrnddate();
		  if(end_date.contains(support_end_date))
		  {
			System.out.println("Successfully End date created ");  
		  }
		  else 
		  {
			  System.out.println("Not Created End date ");
		  }
		  
		  
		// click on the contact tab in homepage using pom
			
			 h.getContacttab();
			
			// delete the created contact
	       driver.findElement
	         (By.xpath("//a[text()='"+ contctname+ "']/ancestor::tr[@bgcolor=\"white\"]/descendant::a[text()='del']"))
	         .click();
	       Thread.sleep(2000);
		    
		    // handle the popup 
	       
        w.handleAlertandClickoK(driver);
	   
	       
	        // Mousing Hovering on the admin icon using pom
	       
	       	WebElement admin=h.getAdminicon();;
		    
	       	w.Mouseovering(driver, admin);  
		   
		/// Logout of the Application with pom
		    h.getSignout();
		    
			// close the browser
			w.QuittheBrowser(driver);
			
			// close workbook
		ex_util.closeExcelWorkbook();
			

}
}
