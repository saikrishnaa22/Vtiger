package POMUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.devtools.v129.v129CdpInfo;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePomPage {
	
	// declare
     @FindBy(partialLinkText = "Home")
     private  WebElement header;
     
     @FindBy(linkText = "Organizations")
     private  WebElement organizationtab;
	
     @FindBy(linkText = "Contacts")
     private  WebElement contacttab;

     @FindBy(xpath = "//img[contains(@src,'user.PNG')]")
     private  WebElement adminicon; 
     
     
     @FindBy(linkText = "Sign Out")
     private  WebElement signout;

 	//Initialization
     
     WebDriver driver;
 	public HomePomPage(WebDriver driver) {
 		this.driver=driver;
 		PageFactory.initElements(driver, this);
 	}


	
	// Utilization
	public String getHeader() {
		return header.getText();
	}

	public void getOrganizationtab() {
		organizationtab.click();
	}

	public void getContacttab() {
		 contacttab.click();
	}

	public WebElement getAdminicon() {
		return adminicon;
	}

	public void getSignout() {
		 signout.click();
	}

	
	
	
	




}
