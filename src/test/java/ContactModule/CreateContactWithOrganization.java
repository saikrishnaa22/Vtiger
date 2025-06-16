package ContactModule;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;
import java.util.Set;

import org.apache.poi.EncryptedDocumentException;
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
import POMUtilities.CreateNewOrganizationPomPage;
import POMUtilities.HomePomPage;
import POMUtilities.LoginPomPage;
import POMUtilities.OrganizationInfoPomPage;
import POMUtilities.OrganizationPomPage;

public class CreateContactWithOrganization {

	@Test
	public void CreateContactWithOrg() throws EncryptedDocumentException, IOException, InterruptedException {
		

		PropertyFileUtility prop = new PropertyFileUtility();

		String Browser = prop.fetchdatafrompropertyfile("browser");
		String Url = prop.fetchdatafrompropertyfile("url");
		String Username = prop.fetchdatafrompropertyfile("username");
		String Password = prop.fetchdatafrompropertyfile("password");
		String Timeouts = prop.fetchdatafrompropertyfile("timeouts");

		// Fetch the random number
		JavaUtility j = new JavaUtility();
		int num = j.generateRandomNumber();
         
		// Ftch the data from excel
		ExcelFileUtility ex_util = new ExcelFileUtility();
		String contctname = ex_util.fetchThedatafromExcel("contact", 7, 3) +num;
		String orgname = ex_util.fetchThedatafromExcel("contact", 7, 4) +num;

		WebDriverUtility w = new WebDriverUtility();
		
		// Launnch the browser

		WebDriver driver = null;
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

		OrganizationPomPage o = new OrganizationPomPage(driver);
		HomePomPage h = new HomePomPage(driver);
		CreateNewOrganizationPomPage org_new = new CreateNewOrganizationPomPage(driver);

		// Maximum the window
		w.Maxmizethewindow(driver);

		// Implicity wait
		w.waittheelementFound(driver, Timeouts);
		
		// Navigate to the Application
		w.NavigateToURL(driver,Url);

		// login into application
		LoginPomPage l = new LoginPomPage(driver);
		l.getUsername(Username);
		l.getPassword(Password);
		l.getLogin();

		// click on organization tab in the homepage
		h.getOrganizationtab();

		// click on plus symbol
		o.getorgplusicon();

		// Enter the ORganization name and click on the save Button
		org_new.getOrgname(orgname);
		org_new.getSave_btn();

		// verify the organization //
     OrganizationInfoPomPage org_info = new OrganizationInfoPomPage(driver);
		String header = org_info.getHeader();
		if (header.contains(orgname)) {
			System.out.println("Test Pass");
		} else {
			System.out.println("Test Fail");
		}

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
		String con_header = c_info.getHeader();
		if (con_header.contains(contctname)) {
			System.out.println("Test : pass");
		} else {
			System.out.println("Test : fail");
		}

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
	

		// Mousing Hovering on the admin icon using pom

		WebElement admin = h.getAdminicon();
		w.Mouseovering(driver, admin);

		// Logout of the Application with pom
		h.getSignout();

		// close the browser

		w.QuittheBrowser(driver);

		// close excel sheet
		ex_util.closeExcelWorkbook();

	}

}
