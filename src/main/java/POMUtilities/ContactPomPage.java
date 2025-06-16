package POMUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactPomPage {

	//Declare
	@FindBy(linkText = "Contacts")
     private WebElement header;
	
	@FindBy(xpath = "//img[@title='Create Contact...']")
    private WebElement conplusicon;

	
	//initialize
	WebDriver driver;
	public ContactPomPage(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}



	// Utilization
	
	public String getHeader() {
		return header.getText();
	}


	public void getConplusicon() {
		 conplusicon.click();
	}
	
	
	
	
	
		
		
		
	
	
	
}
