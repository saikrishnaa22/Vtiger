package ContactModule;

import static org.testng.Assert.fail;

import java.io.IOException;

import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import BaseClassUtility.BaseClass;
import GenericUtilities.ExcelFileUtility;
import GenericUtilities.JavaUtility;
import GenericUtilities.PropertyFileUtility;
import GenericUtilities.WebDriverUtility;
import POMUtilities.ContactInfoPompage;
import POMUtilities.ContactPomPage;
import POMUtilities.CreateNewContactPom;
import POMUtilities.CreateNewOrganizationPomPage;
import POMUtilities.HomePomPage;
import POMUtilities.LoginPomPage;
import POMUtilities.OrganizationInfoPomPage;
import POMUtilities.OrganizationPomPage;


//@Listeners(ListenersUtillity.Listeners.class)

public class ContactAllTestSenarios extends BaseClass{
	//@Parameters("browser")
	
@Test(groups ="smoke",retryAnalyzer =ListenersUtillity.IRetryAnalyser.class)

	public void CreateConttName() throws IOException, InterruptedException {
		
		
		// Fetch the random number
		  JavaUtility j= new JavaUtility();
		 int num = j.generateRandomNumber();
		
		ExcelFileUtility ex_util = new ExcelFileUtility();
		String contctname=	ex_util.fetchThedatafromExcel("contact", 1, 3)+num;
	
		
	    WebDriverUtility w= new WebDriverUtility();
	
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
	 String lastname = con_info.getVerifylastnametf();
	 Assert.assertEquals(lastname, contctname);
	 
	    // click on the contact tab in homepage using pom
		 h.getContacttab();
		
		// delete the created contact
       driver.findElement
         (By.xpath("//a[text()='"+ contctname+ "']/ancestor::tr[@bgcolor=\"white\"]/descendant::a[text()='del']"))
         .click();
      
       Thread.sleep(2000);
	    
	    // handle the popup 
              w.handleAlertandClickoK(driver);
  
		// close excel 
          ex_util.closeExcelWorkbook();
       
}
	
	//@Parameters("browser")

@Test(retryAnalyzer =ListenersUtillity.IRetryAnalyser.class)

public void CreateContactWithOrg() throws EncryptedDocumentException, IOException, InterruptedException {
	
	// Fetch the random number
	JavaUtility j = new JavaUtility();
	int num = j.generateRandomNumber();
     
	// Ftch the data from excel
	ExcelFileUtility ex_util = new ExcelFileUtility();
	String contctname = ex_util.fetchThedatafromExcel("contact", 7, 3) +num;
	String orgname = ex_util.fetchThedatafromExcel("contact", 7, 4) +num;

	WebDriverUtility w = new WebDriverUtility();
	
	OrganizationPomPage o = new OrganizationPomPage(driver);
	HomePomPage h = new HomePomPage(driver);
	CreateNewOrganizationPomPage org_new = new CreateNewOrganizationPomPage(driver);

	

	// click on organization tab in the homepage
	h.getOrganizationtab();

	// click on plus symbol
	o.getorgplusicon();

	// Enter the ORganization name and click on the save Button
	org_new.getOrgname(orgname);
	org_new.getSave_btn();

	// verify the organization //
 OrganizationInfoPomPage org_info = new OrganizationInfoPomPage(driver);
	String verifyorg_name = org_info.getverifyOrgname();

 
 // Hard Assert checking org name
 Assert.assertEquals(verifyorg_name, verifyorg_name);

	// click on the contact tab in homepage using pom
	h.getContacttab();

	// click on the +image to create Contact
	ContactPomPage c=new ContactPomPage(driver);
	c.getConplusicon();

	// Enter the ORganization name and click on the save Button
CreateNewContactPom c_new = new CreateNewContactPom(driver);
ContactInfoPompage c_info= new ContactInfoPompage(driver);
c_new.getLastnametf(contctname);


	w.parentwindow(driver);

	String pwind = driver.getWindowHandle();

	driver.findElement(By.xpath("//img[@title='Select']")).click();

	Set<String> wids = driver.getWindowHandles();
	for (String id : wids) {
		driver.switchTo().window(id);
		if (driver.getCurrentUrl().contains("module=Accounts&action")) {
			
			CreateNewContactPom cc=new CreateNewContactPom(driver);
			cc.getOrgSearchTf(orgname);
			cc.getOrgSearchbtn();
		
			driver.findElement(By.xpath("//a[text()='" + orgname + "']")).click();
		}

	}

	driver.switchTo().window(pwind);
	driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();

	// verify the contact name //
	String lastname = c_info.getVerifylastnametf();
	// Hard Assert to validate contact name
	Assert.assertEquals(lastname, contctname);

	// verify the organization in the contact info page

	String verifyorg = driver
			.findElement(By.xpath("//td[@id='mouseArea_Organization Name']/a[text()='" + orgname + "']")).getText();
	if (verifyorg.contains(orgname)) {
		System.out.println("success with org");
	} else {
		System.out.println("Not Created ");
	}

	// click on the contact tab in homepage using pom

	h.getContacttab();

	// delete the created contact
	driver.findElement(By
			.xpath("//a[text()='" + contctname + "']/ancestor::tr[@bgcolor=\"white\"]/descendant::a[text()='del']"))
			.click();
	Thread.sleep(2000);

	// handle the  alert popup
	w.handleAlertandClickoK(driver);
	
	// click on organization tab with pom
			h.getOrganizationtab();

			// Delete the Created organization
			driver.findElement(
					By.xpath("//a[text()='" + orgname + "']/ancestor::tr[@bgcolor='white']/descendant::a[text()='del']"))
					.click();
			Thread.sleep(3000);
			// Handle the Alert pop
			w.handleAlertandClickoK(driver);

	// close excel sheet
	ex_util.closeExcelWorkbook();

}
	
	//@Parameters("browser")

@Test(retryAnalyzer =ListenersUtillity.IRetryAnalyser.class)

public void CreateConttsupportdate() throws IOException, InterruptedException {
	

	// Fetch the random number
	  JavaUtility j= new JavaUtility();
	 int num = j.generateRandomNumber();
	
     // fetch the data from excel
	ExcelFileUtility ex_util = new ExcelFileUtility();
	String contctname=	ex_util.fetchThedatafromExcel("contact", 4, 3)+num;

	
	
	CreateNewContactPom cn= new CreateNewContactPom(driver);
	WebDriverUtility w= new WebDriverUtility();
	ContactPomPage c= new ContactPomPage(driver);
	
	
	
    // click on the contact tab in homepage using pom
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
	 String lastname = c_info.getVerifylastnametf();
	 
	 // Hard Assert tp verify contact name
	 Assert.assertEquals(lastname, contctname);
	 

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
       Thread.sleep(3000);
	    
	    // handle the popup 
       
    w.handleAlertandClickoK(driver);
   
		// close workbook
	ex_util.closeExcelWorkbook();
		

}
}
