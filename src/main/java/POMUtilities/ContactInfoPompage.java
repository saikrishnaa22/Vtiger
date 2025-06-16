package POMUtilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ContactInfoPompage {
	
	// declare 
	
     @ FindBy(xpath = "//span[contains(text(),' Contact Information')]") private WebElement  header;
     @ FindBy(id = "dtlview_Last Name") private WebElement  verifylastnametf;
     @ FindBy(id = "mouseArea_Organization Name") private WebElement verifyorgname;
     @ FindBy(id = "dtlview_Support Start Date") private WebElement  verifystartdate;
     @ FindBy(id = "dtlview_Support End Date") private WebElement  verifyrnddate;
     
     
     
  // initilize
     WebDriver driver;
 	public ContactInfoPompage(WebDriver driver) {
 		this.driver=driver;
 		PageFactory.initElements(driver, this);
 	}

	
	// utilize
	public String getHeader() {
		return header.getText();
	}
	public String getVerifylastnametf() {
		 return verifylastnametf.getText();
	}
	public String getOrgname() {
		return verifyorgname.getText();
	}
	public String getVerifystartdate() {
		return verifystartdate.getText();
	}
	public String getVerifyrnddate() {
		return verifyrnddate.getText();
	}
    
    

}
