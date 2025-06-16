package LeadsModule;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.time.Duration;
import java.util.Properties;

import org.apache.logging.log4j.util.PropertiesUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

public class CreateLeadName {
	
	@Test
	
	public void Createname() throws Exception {
		
		
		// fetch the data from the property file
		FileInputStream pfis = new FileInputStream("./src/test/resources/CommonData.properties");
		Properties p =new Properties();
		p.load(pfis);
		
		String Browser = p.getProperty("browser");
		String Url = p.getProperty("url");
		String Username = p.getProperty("username");
		String Password = p.getProperty("password");
		String Timeouts = p.getProperty("timeouts");
		
		// fetch the data from the  excel file 
		FileInputStream efis = new FileInputStream("./src/test/resources/VtigerTestData.xlsx");
		Workbook wb = WorkbookFactory.create(efis);
		String leadsname = wb.getSheet("leads").getRow(1).getCell(2).toString();
		String companysname = wb.getSheet("leads").getRow(1).getCell(3).toString();
		
		// Launnch the browser
		
		WebDriver driver= null;
		if (Browser.equals("chrome"))
		{
			driver=new ChromeDriver();
		}
		else if (Browser.equals("edge"))
		{
			driver= new EdgeDriver();
		}
		
		// Maximum the window
		driver.manage().window().maximize();
		// Implicity wait
		long time = Long.parseLong(Timeouts);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time));
		// Navigate to the Application
		driver.get(Url);

		// Login to Appication
		driver.findElement(By.name("user_name")).sendKeys(Username);
		driver.findElement(By.name("user_password")).sendKeys(Password);
		driver.findElement(By.id("submitButton")).click();
		Thread.sleep(2000);
		
		// click on the contact tab in homepage 
		driver.findElement(By.linkText("Leads")).click();
		
		// click on the +image to create organization
	     driver.findElement(By.xpath("//img[@title='Create Lead...']")).click();
	     
	  // Enter the ORganization name and click on the save Button
			driver.findElement(By.name("lastname")).sendKeys(leadsname);
			driver.findElement(By.name("company")).sendKeys(companysname);
		    driver.findElement(By.xpath("//input[@title='Save [Alt+S]']")).click();
		    
		 // verify the organization //
		 WebElement header = driver.findElement(By.xpath("//span[contains(text(),'Lead Information')]"));
		    if (header.getText().contains(leadsname))
		    {
		    	System.out.println("Test : pass");
		    }
		    else 
		    {
		    	System.out.println("Test : fail");
		    }
		    
			// click on the contact tab in homepage 
			driver.findElement(By.linkText("Contacts")).click();
			
			// delete the created contact
	       driver.findElement
	         (By.xpath("//a[text()='"+leadsname+"']/ancestor::tr[@bgcolor='white']/descendant::a[text()='del']"))
	         .click();
		    
		    // handle the popup 
	           driver.switchTo().alert().accept();
	   
	          // Mousing Hovering on the admin icon
	   
	         WebElement admin = driver.findElement(By.xpath("//span[text()=' Administrator']/../../descendant::img"));
		    
		    Actions act = new Actions(driver);
		    act.moveToElement(admin).perform();
		    
			// Logout of the Application
			driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
			// close the browser
			driver.quit();
			
		
		
		
		
		
		
	}
	
	

}
