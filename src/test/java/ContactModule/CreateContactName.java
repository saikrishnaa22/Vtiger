package ContactModule;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import javax.swing.plaf.basic.BasicArrowButton;

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
import POMUtilities.OrganizationPomPage;

public class CreateContactName {
	
	@Test
	
	public void CreatenewConttName( ) throws IOException, InterruptedException {
		

		
		PropertyFileUtility prop = new PropertyFileUtility();
		
		String Browser = prop.fetchdatafrompropertyfile("browser");
		String Url =prop.fetchdatafrompropertyfile("url");
		String Username =prop.fetchdatafrompropertyfile("username");
		String Password = prop.fetchdatafrompropertyfile("password");
		String Timeouts = prop.fetchdatafrompropertyfile("timeouts");
		
		
		// Fetch the random number
		  JavaUtility j= new JavaUtility();
		 int num = j.generateRandomNumber();
		
		ExcelFileUtility ex_util = new ExcelFileUtility();
		String contctname=	ex_util.fetchThedatafromExcel("contact", 1, 3)+num;
		
	
		// Launnch the browser
		
	
		WebDriver driver= null;
		if (Browser.equals("Chrome"))
		{
			driver= new ChromeDriver();
		}
		else if (Browser.equals("edge"))
		{
			driver= new EdgeDriver();
		}
		else
		{
			driver = new ChromeDriver();
		}
	
		
	    WebDriverUtility w= new WebDriverUtility();
		
		ExcelFileUtility e= new ExcelFileUtility();
		

		// Maximum the window
		w.Maxmizethewindow(driver);
		
		// Implicity wait
		
		w.waittheelementFound(driver, Timeouts);
		
		// Navigate to the Application
		
	       w.NavigateToURL(driver, Url);

       /// Login to Appication through POM page
		
		LoginPomPage l =new  LoginPomPage(driver);
		l.getUsername(Username);
		l.getPassword(Password);
		l.getLogin();

	  // click on the contact tab in homepage using pom
		 HomePomPage h = new HomePomPage(driver);
		 h.getContacttab();
		
        // click on plus symbol
       ContactPomPage c= new ContactPomPage(driver);
       c.getConplusicon();
		 
		// Enter the contact name and click on the save Button
		 CreateNewContactPom c_new = new CreateNewContactPom(driver);
		 c_new.getLastnametf(contctname);
		 c_new.getSave_btn();

	    
	        // verify the contact  name//
	    ContactInfoPompage con_info= new ContactInfoPompage(driver);
	 String header = con_info.getHeader();
	    if (header.contains(contctname))
	    {
	    	System.out.println("Test : pass");
	    }
	    else 
	    {
	    	System.out.println("Test : fail");
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
       	
       	WebElement admin=h.getAdminicon();
      	w.Mouseovering(driver, admin);  
	    
        // Logout of the Application with pom
	    h.getSignout();
	    
		// close the browser
		
		w.QuittheBrowser(driver);
		
		// close excel 
          ex_util.closeExcelWorkbook();
       
		
		
	}
	

}
